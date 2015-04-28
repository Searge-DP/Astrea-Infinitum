package astreaInfinitum.tileEntities;

import java.util.ArrayList;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.api.IManaDust;
import astreaInfinitum.api.recipes.RecipeManaAltar;
import astreaInfinitum.api.recipes.RecipeRegistry;

public class TileEntityPedestal extends TileEntity implements IInventory {

	public ItemStack[] items = new ItemStack[1];
	public float angle;

	@Override
	public void updateEntity() {
		angle++;
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
			System.out.println(pedestalItems.size());
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
				System.out.println("light" + manaLight);
				System.out.println("dark" + manaDark);

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

}
