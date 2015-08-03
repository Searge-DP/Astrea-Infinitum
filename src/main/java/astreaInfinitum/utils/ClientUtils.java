package astreaInfinitum.utils;

import net.minecraft.entity.player.EntityPlayer;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.network.MessageItemSync;
import astreaInfinitum.network.MessagePlayerSync;

public class ClientUtils {

	public static void updatePlayerInformation(MessagePlayerSync message) {
		EntityPlayer player = AstreaInfinitum.proxy.getClientPlayer();
		AIUtils.setPlayerKnowledge(player, message.knowledge);
		AIUtils.setPlayerLevel(player, message.level);
		AIUtils.setPlayerEco(player, EnumPlayerEco.light, message.ecoLight);
		AIUtils.setPlayerEco(player, EnumPlayerEco.dark, message.ecoDark);
		AIUtils.setPlayerMaxEco(player, EnumPlayerEco.light, message.maxEcoLight);
		AIUtils.setPlayerMaxEco(player, EnumPlayerEco.dark, message.maxEcoDark);
		
		AIUtils.setPlayerMaxXP(player, message.maxXP);
		AIUtils.setPlayerXP(player, message.xp);
	}
	public static void updateItemInformation(MessageItemSync message) {
		EntityPlayer player = AstreaInfinitum.proxy.getClientPlayer();
		AIUtils.levelUpSpell(player.inventory.getCurrentItem());
	}

}
