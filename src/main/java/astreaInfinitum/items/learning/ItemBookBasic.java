package astreaInfinitum.items.learning;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.utils.AIUtils;

public class ItemBookBasic extends Item {

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote && AIUtils.getPlayerKnowledge(player)) {
			if (player != null && !(player instanceof FakePlayer)) {
				AstreaInfinitum.proxy.readBookBasic();
				AIUtils.initPlayer(player);
			}
		}
		return stack;
	}
}
