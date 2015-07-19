package astreaInfinitum.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEcoDrop extends Item {

	public int color;
	public Block block;

	public ItemEcoDrop(int color, Block block) {
		this.color = color;
		this.block = block;
	}

	@Override
	public int getColorFromItemStack(ItemStack p_82790_1_, int p_82790_2_) {
		return color;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if (world.getBlock(x, y + 1, z) != null && world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z) && block.canPlaceBlockAt(world, x, y + 1, z)) {
			world.setBlock(x, y + 1, z, block);
			stack.stackSize--;
		}

		return true;
	}

}
