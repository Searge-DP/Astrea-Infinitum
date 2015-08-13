package astreaInfinitum.tileEntities;

import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.dust.EnumDust;
import astreaInfinitum.api.dust.IDust;
import astreaInfinitum.api.recipes.RecipeEcoAltar;
import astreaInfinitum.api.recipes.RecipeRegistry;
import astreaInfinitum.blocks.BlockEcoAltar;
import astreaInfinitum.network.MessagePedestalSync;
import astreaInfinitum.network.PacketHandler;
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

import java.util.ArrayList;

public class TileEntityPedestal extends TileEntity implements IInventory {

	public ItemStack[] items = new ItemStack[1];
	public float angle;

	@Override
	public void updateEntity() {
		angle += 6;
		if (!worldObj.isRemote) {
			PacketHandler.INSTANCE.sendToAllAround(new MessagePedestalSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
		}
	}

	public void infuse(World world, int x, int y, int z) {
		if (!world.isRemote) if (getStackInSlot(0) != null) {
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
			int dustArcane = 0;
			int dustEco = 0;
			if (pedestalItems.size() == 4 && isValidAltar(world, x, y, z)) {
				for (int posX = -4; posX < 4; posX++) {
					for (int posZ = -4; posZ < 4; posZ++) {
						if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IDust) {
							IDust dust = (IDust) world.getBlock(x + posX, y, z + posZ);
							if (dust.getDustType() == EnumDust.arcane) {
								dustArcane++;
							}
							if (dust.getDustType() == EnumDust.eco) {
								dustEco++;
							}
						}
					}
				}

				if (dustEco > 0 && dustArcane > 0) {
					return;
				}
				if (dustArcane > 0) {
					RecipeEcoAltar recipe = RecipeRegistry.getRecipeForItems(getStackInSlot(0), pedestalItems.get(0), pedestalItems.get(1), pedestalItems.get(2), pedestalItems.get(3), EnumPlayerEco.light);
					if (recipe != null && dustArcane >= recipe.getEcoDustNeeded()) {
						dustArcane = recipe.getEcoDustNeeded();
						for (int posX = -3; posX < 4; posX++) {
							if (dustArcane > 0) for (int posZ = -3; posZ < 4; posZ++) {
								if (dustArcane > 0)
									if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IDust) {
										world.setBlockToAir(x + posX, y, z + posZ);
										dustArcane--;
										world.addWeatherEffect(new EntityLightningBolt(world, x + posX, y, z + posZ));
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
				if (dustEco > 0) {
					RecipeEcoAltar recipe = RecipeRegistry.getRecipeForItems(getStackInSlot(0), pedestalItems.get(0), pedestalItems.get(1), pedestalItems.get(2), pedestalItems.get(3), EnumPlayerEco.dark);
					if (recipe != null && dustEco >= recipe.getEcoDustNeeded()) {
						dustEco = recipe.getEcoDustNeeded();
						for (int posX = -2; posX < 3; posX++) {
							if (dustEco > 0) for (int posZ = -2; posZ < 3; posZ++) {
								if (dustEco > 0)
									if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IDust) {
										world.setBlockToAir(x + posX, y, z + posZ);
										dustEco--;
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
		if (!(dir == ForgeDirection.DOWN) || !(dir == ForgeDirection.UP))
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
