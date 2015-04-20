package astreaInfinitum.items.tablets;

import astreaInfinitum.api.EnumMana;
import astreaInfinitum.utils.AIUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ItemResetTablet extends Item {

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (!world.isRemote) {
			if (player != null && !(player instanceof FakePlayer)) {
				AIUtils.setPlayerKnowledge(player, false);
				player.addChatComponentMessage(new ChatComponentText("you have Successfully forgotten this tablet"));
				AIUtils.setPlayerMana(player, EnumMana.light, 0);
				AIUtils.setPlayerMana(player, EnumMana.dark, 0);
				AIUtils.setPlayerLevel(player, 0);
			}
		}
		return false;
	}
}
