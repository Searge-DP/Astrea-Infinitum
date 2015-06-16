package astreaInfinitum.tileEntities;

import java.util.ArrayList;

import net.minecraft.client.renderer.entity.RenderPainting;
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
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.api.IManaDust;
import astreaInfinitum.api.recipes.RecipeManaAltar;
import astreaInfinitum.api.recipes.RecipeRegistry;
import astreaInfinitum.client.RenderParticles;

public class TileEntityPedestal extends TileEntity implements IInventory {

	public ItemStack[] items = new ItemStack[1];
	public float angle;

	@Override
	public void updateEntity() {
		angle++;
		if (!worldObj.isRemote && worldObj.rand.nextInt(20) == 0)
			RenderParticles.spawnParticle("sphere", xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, 0, 0, 0);
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
			int manaLight = 0;
			int manaDark = 0;
			if (pedestalItems.size() == 4) {
				for (int posX = -3; posX < 3; posX++) {
					for (int posZ = -3; posZ < 3; posZ++) {
						if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IManaDust) {
							IManaDust dust = (IManaDust) world.getBlock(x + posX, y, z + posZ);
							if (dust.getManaType() == EnumMana.light) {
								manaLight++;
							}
							if (dust.getManaType() == EnumMana.dark) {
								manaDark++;
							}
						}
					}
				}

				if (manaDark > 0 && manaLight > 0) {
					return;
				}
				if (manaLight > 0) {
					RecipeManaAltar recipe = RecipeRegistry.getRecipeForItems(getStackInSlot(0), pedestalItems.get(0), pedestalItems.get(1), pedestalItems.get(2), pedestalItems.get(3), EnumMana.light);
					if (recipe != null && manaLight >= recipe.getManaDustNeeded()) {
						manaLight = recipe.getManaDustNeeded();
						for (int posX = -2; posX < 3; posX++) {
							if (manaLight > 0)
								for (int posZ = -2; posZ < 3; posZ++) {
									if (manaLight > 0)
										if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IManaDust) {
											world.setBlockToAir(x + posX, y, z + posZ);
											manaLight--;
											world.spawnEntityInWorld(new EntityLightningBolt(world, x + posX, y, z + posZ));
											setInventorySlotContents(0, recipe.getOutput());
											for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
												setPedestalItem(world, x, y, z, dir, null);
											}
										}
								}
						}
					}

				}
				if (manaDark > 0) {
					RecipeManaAltar recipe = RecipeRegistry.getRecipeForItems(getStackInSlot(0), pedestalItems.get(0), pedestalItems.get(1), pedestalItems.get(2), pedestalItems.get(3), EnumMana.dark);
					if (recipe != null && manaDark >= recipe.getManaDustNeeded()) {
						manaDark = recipe.getManaDustNeeded();
						for (int posX = -2; posX < 3; posX++) {
							if (manaDark > 0)
								for (int posZ = -2; posZ < 3; posZ++) {
									if (manaDark > 0)
										if (world.getBlock(x + posX, y, z + posZ) != null && world.getBlock(x + posX, y, z + posZ) instanceof IManaDust) {
											world.setBlockToAir(x + posX, y, z + posZ);
											manaDark--;
											world.setBlock(x + posX, y, z + posZ, Blocks.fire);
											setInventorySlotContents(0, recipe.getOutput());
											for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
												setPedestalItem(world, x, y, z, dir, null);
											}
										}
								}
						}
					}

				}

			}
		}
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

	public void setPedestalItem(World world, int x, int y, int z, ForgeDirection dir, ItemStack stack) {
		if (!(dir == dir.DOWN) || !(dir == dir.UP))
			if (world.getBlock(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) != null) {
				if (world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) != null && world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3)) instanceof TileEntityPedestal) {
					TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(x + (dir.offsetX * 3), y + (dir.offsetY * 3), z + (dir.offsetZ * 3));
					tile.setInventorySlotContents(0, stack);
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
		return "Mana Pedestal";
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
