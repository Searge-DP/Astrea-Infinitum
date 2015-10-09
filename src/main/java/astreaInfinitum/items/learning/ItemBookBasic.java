package astreaInfinitum.items.learning;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.utils.AIUtils;

public class ItemBookBasic extends Item {

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
			if (player != null && !(player instanceof FakePlayer)) {
				AIUtils.initPlayer(player);
				if (world.isRemote) {
				AstreaInfinitum.proxy.readBookBasic();
			}
		}
		return stack;
	}
}
