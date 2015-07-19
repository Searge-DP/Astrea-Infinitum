package astreaInfinitum.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import astreaInfinitum.api.wands.IWand;
import astreaInfinitum.tileEntities.TileEntityPedestal;

public class BlockPedestal extends BlockContainer {

	protected BlockPedestal() {
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPedestal();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(x, y, z);
		if (player.inventory.getCurrentItem() != null && !(player.inventory.getCurrentItem().getItem() == Items.stick) && tile.getStackInSlot(0) == null) {
			ItemStack stack = player.inventory.getCurrentItem().copy();
			stack.stackSize = 1;
			tile.setInventorySlotContents(0, stack);
			player.inventory.decrStackSize(player.inventory.currentItem, 1);
			return true;
		}
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof IWand) {
			tile.infuse(world, x, y, z);
			return true;
		}
		if (tile.getStackInSlot(0) != null && player.inventory.getCurrentItem() == null) {
			if (!world.isRemote)
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 1.5, z + 0.5, tile.getStackInSlot(0)));
			tile.setInventorySlotContents(0, null);
			return true;
		}
		return false;
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}
}
