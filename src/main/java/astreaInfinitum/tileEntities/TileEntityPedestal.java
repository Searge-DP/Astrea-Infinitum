package astreaInfinitum.tileEntities;

import java.util.ArrayList;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import astreaInfinitum.api.EnumEco;
import astreaInfinitum.api.IEcoDust;
import astreaInfinitum.api.recipes.RecipeEcoAltar;
import astreaInfinitum.api.recipes.RecipeRegistry;
import astreaInfinitum.blocks.BlockEcoAltar;
import astreaInfinitum.network.MessageAltarSync;
import astreaInfinitum.network.MessagePedestalSync;
import astreaInfinitum.network.PacketHandler;

public class TileEntityPedestal extends TileEntity implements IInventory {

	public ItemStack[] items = new ItemStack[1];
	public float angle;

	@Override
	public void updateEntity() {
		angle += 6;
		if (!worldObj.isRemote) {
			if (getStackInSlot(0) != null)
				PacketHandler.INSTANCE.sendToAllAround(new MessagePedestalSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
		}
	}

	public void infuse(World world, int x, int y, int z) {
		if (getStackInSlot(0) != null) {
			ArrayList<ItemStack> pedestalItems = new ArrayList<ItemStack>();
			if (getPedestalItem(world, x, y, z, ForgeDirection.NORTH) != null) {
				pedestalItems.add(getPedestalItem(world, x, y, z, ForgeDirection.NORTH));
			}
			if (getPedestalItem(world, x, y, z, ForgeDirection.SOUTH) != null) {
				pedestalItems.add(getPedestalItem(world, x, y, z, ForgeDirection.SOUTH));
			}
			if (getPedestalItem(world, x, y, z, ForgeDirection.EAST) != null) {
				pedestalItems.add(getPedestalItem(world, x, y, z, ForgeDirection.EAST));
			}
			if (getPedestalItem(world, x, y, z, ForgeDirection.WEST) != null) {
				pedestalItems.add(getPedestalItem(world, x, y, z, ForgeDirection.WEST));
			}
			int ecoLight = 0;
			int ecoDark = 0;
			if (pedestalItems.size() == 4 && isValidAltar(world, x, y, z)) {
				for (int posX = -4; posX < 4; posX++) {
					for (int posZ = -4; posZ < 4; posZ++) {
						if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IEcoDust) {
							IEcoDust dust = (IEcoDust) world.getBlock(x + posX, y, z + posZ);
							if (dust.getEcoType() == EnumEco.light) {
								ecoLight++;
							}
							if (dust.getEcoType() == EnumEco.dark) {
								ecoDark++;
							}
						}
					}
				}

				if (ecoDark > 0 && ecoLight > 0) {
					return;
				}
				if (ecoLight > 0) {
					RecipeEcoAltar recipe = RecipeRegistry.getRecipeForItems(getStackInSlot(0), pedestalItems.get(0), pedestalItems.get(1), pedestalItems.get(2), pedestalItems.get(3), EnumEco.light);
					if (recipe != null && ecoLight >= recipe.getEcoDustNeeded()) {
						ecoLight = recipe.getEcoDustNeeded();
						for (int posX = -3; posX < 4; posX++) {
							if (ecoLight > 0)
								for (int posZ = -3; posZ < 4; posZ++) {
									if (ecoLight > 0)
										if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IEcoDust) {
											world.setBlockToAir(x + posX, y, z + posZ);
											ecoLight--;
											world.spawnEntityInWorld(new EntityLightningBolt(world, x + posX, y, z + posZ));
											setInventorySlotContents(0, recipe.getOutput().copy());
											for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
												setPedestalItem(world, x, y, z, dir, null);
											}
											markDirty();
										}
								}
						}
					}

				}
				if (ecoDark > 0) {
					RecipeEcoAltar recipe = RecipeRegistry.getRecipeForItems(getStackInSlot(0), pedestalItems.get(0), pedestalItems.get(1), pedestalItems.get(2), pedestalItems.get(3), EnumEco.dark);
					if (recipe != null && ecoDark >= recipe.getEcoDustNeeded()) {
						ecoDark = recipe.getEcoDustNeeded();
						for (int posX = -2; posX < 3; posX++) {
							if (ecoDark > 0)
								for (int posZ = -2; posZ < 3; posZ++) {
									if (ecoDark > 0)
										if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IEcoDust) {
											world.setBlockToAir(x + posX, y, z + posZ);
											ecoDark--;
											world.setBlock(x + posX, y, z + posZ, Blocks.fire);
											setInventorySlotContents(0, recipe.getOutput());
											for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
												setPedestalItem(world, x, y, z, dir, null);
											}
											markDirty();
										}
								}
						}
					}

				}

			}
		}

	}

	@Override
	public void markDirty() {
		super.markDirty();
		PacketHandler.INSTANCE.sendToAllAround(new MessagePedestalSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
	}

	public ItemStack getPedestalItem(World world, int x, int y, int z, ForgeDirection dir) {
		if (world.getBlock(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) != null) {
			if (world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) != null && world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) instanceof TileEntityPedestal) {
				TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3));
				if (tile.getStackInSlot(0) != null) {
					return tile.getStackInSlot(0);
				}
			}
		}
		return null;
	}

	public boolean isValidAltar(World world, int x, int y, int z) {
		return worldObj.getBlock(x, y - 1, z) != null && world.getBlock(x, y - 1, z) instanceof BlockEcoAltar && ((TileEntityEcoAltar) world.getTileEntity(x, y - 1, z)).isActivated();
	}

	public void setPedestalItem(World world, int x, int y, int z, ForgeDirection dir, ItemStack stack) {
		if (!(dir == dir.DOWN) || !(dir == dir.UP))
			if (world.getBlock(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) != null) {
				if (world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) != null && world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) instanceof TileEntityPedestal) {
					TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3));
					tile.setInventorySlotContents(0, stack);
					tile.markDirty();
				}
			}
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return items[slot];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemstack = itemstack.splitStack(count);

			}
		}

		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return items[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		items[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return "Eco Pedestal";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f) <= 64;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readInventoryFromNBT(nbt);

	}

	public void readInventoryFromNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = tags.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = (NBTTagCompound) nbttaglist.getCompoundTagAt(iter);
			byte slotID = tagList.getByte("Slot");
			if (slotID >= 0 && slotID < items.length) {
				items[slotID] = ItemStack.loadItemStackFromNBT(tagList);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		writeInventoryToNBT(tag);
	}

	public void writeInventoryToNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int iter = 0; iter < items.length; iter++) {
			if (items[iter] != null) {
				NBTTagCompound tagList = new NBTTagCompound();
				tagList.setByte("Slot", (byte) iter);
				items[iter].writeToNBT(tagList);
				nbttaglist.appendTag(tagList);
			}
		}

		tags.setTag("Items", nbttaglist);
	}
}
