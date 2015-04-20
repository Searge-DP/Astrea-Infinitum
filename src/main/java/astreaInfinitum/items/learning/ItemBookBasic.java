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
		if (!world.isRemote && AIUtils.getPlayerKnowledge(player)) {
			if (player != null && !(player instanceof FakePlayer)) {
				AstreaInfinitum.proxy.readBookBasic();
				if (AIUtils.getPlayerLevel(player) < 1) {
					AIUtils.setPlayerLevel(player, 1);
					AIUtils.setPlayerXP(player, 0);
					AIUtils.setPlayerMaxXP(player, 10);

				}
			}
		}
		return stack;
	}
}
