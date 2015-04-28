package astreaInfinitum.blocks;

import astreaInfinitum.tileEntities.TileEntityPedestal;
import astreaInfinitum.utils.AIUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

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
		if (player.inventory.getCurrentItem() != null && !(player.inventory.getCurrentItem().getItem() == Items.stick)) {
			// tile.items[0] = player.inventory.getCurrentItem().copy();
			tile.setInventorySlotContents(0, player.inventory.getCurrentItem().copy());
		}
		if (player.inventory.getCurrentItem().getItem() == Items.stick) {
			player.addChatComponentMessage(new ChatComponentText("hi"));
			tile.infuse(world, x, y, z);
		}
		if (tile.getStackInSlot(0) != null)
			AIUtils.addChatMessage(player, tile.items[0].getDisplayName());
		return true;
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
