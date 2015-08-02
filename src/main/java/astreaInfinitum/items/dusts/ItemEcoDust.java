package astreaInfinitum.items.dusts;

import astreaInfinitum.blocks.AIBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEcoDust extends Item{
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if (world.getBlock(x, y + 1, z) != null && world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z) && AIBlocks.ecoDust.canPlaceBlockAt(world, x, y + 1, z)) {
			world.setBlock(x, y + 1, z, AIBlocks.ecoDust);
			stack.stackSize--;
		}

		return true;
	}
}
