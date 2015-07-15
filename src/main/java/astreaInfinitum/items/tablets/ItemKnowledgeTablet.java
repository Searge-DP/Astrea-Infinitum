package astreaInfinitum.items.tablets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.utils.AIUtils;

public class ItemKnowledgeTablet extends Item {

	public ItemKnowledgeTablet() {}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
			if (player != null && !(player instanceof FakePlayer)) {
				player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerMana(player, EnumMana.light) + ""));
				player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerMana(player, EnumMana.dark) + ""));
				AstreaInfinitum.proxy.readTablet();
				AIUtils.getEntityData(player).setManaMaxDark(40);
			}
		return stack;
	}
}
