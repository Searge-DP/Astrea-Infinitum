package astreaInfinitum.items.dusts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.utils.AIUtils;

public class ItemEcoDust extends ItemFood {
	public ItemEcoDust() {
		super(0, true);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if (world.getBlock(x, y + 1, z) != null && world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z) && AIBlocks.ecoDust.canPlaceBlockAt(world, x, y + 1, z)) {
			world.setBlock(x, y + 1, z, AIBlocks.ecoDust);
			stack.stackSize--;
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		ItemStack ret = stack.copy();
		if (!world.isRemote)
			if (player.worldObj.rand.nextBoolean()) {
				AIUtils.addEco(player, EnumPlayerEco.light, player.worldObj.rand.nextInt(10));
				ret.stackSize--;
			} else {
				AIUtils.addEco(player, EnumPlayerEco.dark, player.worldObj.rand.nextInt(10));
				ret.stackSize--;
			}
		return ret;

	}
}
