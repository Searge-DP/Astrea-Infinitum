package astreaInfinitum.utils;

import net.minecraft.entity.player.EntityPlayer;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.network.MessageItemSync;
import astreaInfinitum.network.MessagePlayerSync;

public class ClientUtils {

	public static void updatePlayerInformation(MessagePlayerSync message) {
		EntityPlayer player = AstreaInfinitum.proxy.getClientPlayer();
		AIUtils.setPlayerKnowledge(player, message.knowledge);
		AIUtils.setPlayerLevel(player, message.level);
		AIUtils.setPlayerMana(player, EnumMana.light, message.manaLight);
		AIUtils.setPlayerMana(player, EnumMana.dark, message.manaDark);
		AIUtils.setPlayerMaxMana(player, EnumMana.light, message.maxManaLight);
		AIUtils.setPlayerMaxMana(player, EnumMana.dark, message.maxManaDark);
		
		AIUtils.setPlayerMaxXP(player, message.maxXP);
		AIUtils.setPlayerXP(player, message.xp);
	}
	public static void updateItemInformation(MessageItemSync message) {
		EntityPlayer player = AstreaInfinitum.proxy.getClientPlayer();
		AIUtils.levelUpSpell(player.inventory.getCurrentItem());
	}

}
