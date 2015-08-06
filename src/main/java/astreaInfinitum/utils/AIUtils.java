package astreaInfinitum.utils;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.entities.properties.EntityData;
import astreaInfinitum.network.MessagePlayerSync;
import astreaInfinitum.network.PacketHandler;

public class AIUtils {

	public static void initPlayer(EntityPlayer player) {
		setPlayerKnowledge(player, true);
		setPlayerLevel(player, 1);
		setPlayerEco(player, EnumPlayerEco.light, 0);
		setPlayerEco(player, EnumPlayerEco.dark, 0);
		setPlayerMaxEco(player, EnumPlayerEco.light, 40);
		setPlayerMaxEco(player, EnumPlayerEco.dark, 40);
		setPlayerMaxXP(player, 10);
		setPlayerXP(player, 0);
		addChatMessage(player, "level:" + getPlayerLevel(player));
		addChatMessage(player, "maxLight:" + getPlayerEcoMax(player, EnumPlayerEco.light));
		addChatMessage(player, "maxDark:" + getPlayerEcoMax(player, EnumPlayerEco.dark));
		addChatMessage(player, "currentLight:" + getPlayerEco(player, EnumPlayerEco.light));
		addChatMessage(player, "currentDark:" + getPlayerEco(player, EnumPlayerEco.dark));
		addChatMessage(player, "maxXP:" + getPlayerMaxXP(player));
		addChatMessage(player, "xp:" + getPlayerXP(player));
		PacketHandler.INSTANCE.sendToAll(new MessagePlayerSync(getPlayerKnowledge(player), getPlayerEco(player, EnumPlayerEco.light), getPlayerEco(player, EnumPlayerEco.dark), getPlayerLevel(player), getPlayerXP(player), getPlayerMaxXP(player), getPlayerEcoMax(player, EnumPlayerEco.light), getPlayerEcoMax(player, EnumPlayerEco.dark)));

	}

	public static void addChatMessage(EntityPlayer player, String message) {
		player.addChatComponentMessage(new ChatComponentText(message));
	}

	public static EntityData getEntityData(EntityLivingBase entity) {
		return (EntityData) entity.getExtendedProperties(ModProps.modid);
	}

	public static boolean getPlayerKnowledge(EntityPlayer player) {
		return getEntityData(player).isKnowledge();
	}

	public static int getPlayerEco(EntityPlayer player, EnumPlayerEco eco) {
		if (eco == EnumPlayerEco.light) {
			return getEntityData(player).getEcoLight();
		}
		if (eco == EnumPlayerEco.dark) {
			return getEntityData(player).getEcoDark();
		}
		return 0;
	}

	public static int getPlayerEcoMax(EntityPlayer player, EnumPlayerEco eco) {
		if (eco == EnumPlayerEco.light) {
			return getEntityData(player).getEcoMaxLight();
		}
		if (eco == EnumPlayerEco.dark) {
			return getEntityData(player).getEcoMaxDark();
		}
		return 0;
	}

	public static int getPlayerMaxXP(EntityPlayer player) {
		return getEntityData(player).getMaxXP();
	}

	public static int getPlayerXP(EntityPlayer player) {
		return getEntityData(player).getXp();
	}

	public static int getPlayerLevel(EntityPlayer player) {
		return getEntityData(player).getLevel();
	}

	public static void setPlayerKnowledge(EntityPlayer player, boolean bool) {
		getEntityData(player).setKnowledge(bool);
	}

	public static void setPlayerEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		if (eco == EnumPlayerEco.light) {
			getEntityData(player).setEcoLight(amount);
		}
		if (eco == EnumPlayerEco.dark) {
			getEntityData(player).setEcoDark(amount);
		}
	}

	public static void setPlayerMaxXP(EntityPlayer player, int max) {
		getEntityData(player).setMaxXP(max);
	}

	public static void setPlayerXP(EntityPlayer player, int xp) {
		getEntityData(player).setXp(xp);
	}

	public static void setPlayerLevel(EntityPlayer player, int level) {
		getEntityData(player).setLevel(level);
	}

	public static void setPlayerMaxEco(EntityPlayer player, EnumPlayerEco eco, int max) {
		if (eco == EnumPlayerEco.light) {
			getEntityData(player).setEcoMaxLight(max);
		}
		if (eco == EnumPlayerEco.dark) {
			getEntityData(player).setEcoMaxDark(max);
		}
	}

	public static void removeEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		int ecoA = 0;
		if (eco == EnumPlayerEco.light) {
			ecoA = getEntityData(player).getEcoLight();
		}
		if (eco == EnumPlayerEco.dark) {
			ecoA = getEntityData(player).getEcoDark();
		}
		ecoA -= amount;
		setPlayerEco(player, eco, ecoA);
	}

	public static void levelUp(EntityPlayer player) {
		if (getPlayerXP(player) >= getPlayerMaxXP(player)) {
			setPlayerXP(player, 0);
			setPlayerMaxXP(player, getPlayerMaxXP(player) + (getPlayerMaxXP(player) / 2));
			setPlayerMaxEco(player, EnumPlayerEco.light, (getPlayerEcoMax(player, EnumPlayerEco.light) / 2) + getPlayerEcoMax(player, EnumPlayerEco.light));
			setPlayerMaxEco(player, EnumPlayerEco.dark, (getPlayerEcoMax(player, EnumPlayerEco.dark) / 2) + getPlayerEcoMax(player, EnumPlayerEco.dark));
			setPlayerLevel(player, getPlayerLevel(player) + 1);
		}
	}

	public static void addEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		int ecoA = getPlayerEco(player, eco);
		ecoA += amount;
		setPlayerEco(player, eco, ecoA);
	}

	public static void addXP(EntityPlayer player, int amount) {
		int xp = getPlayerXP(player);
		xp += amount * getPlayerLevel(player);
		setPlayerXP(player, xp);
	}

	public static boolean drainEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		int ecoA = getPlayerEco(player, eco);
		if (ecoA >= amount) {
			ecoA -= amount;
			setPlayerEco(player, eco, ecoA);
			return true;
		}
		return false;
	}

	public static void initSpell(ItemStack stack) {
		NBTHelper.setInteger(stack, "AIItemXP", 0);
		NBTHelper.setInteger(stack, "AIItemXPMax", 10);
		NBTHelper.setInteger(stack, "AIItemLevel", 1);
	}

	public static void levelUpSpell(ItemStack stack) {
		if (stack != null) {
			int xp = NBTHelper.getInt(stack, "AIItemXP");
			int xpMax = NBTHelper.getInt(stack, "AIItemXPMax");
			int level = NBTHelper.getInt(stack, "AIItemLevel");
			if (xp >= xpMax && level < 5) {
				level++;
				xp = 0;
				xpMax = (xpMax + (xpMax / 2));
				NBTHelper.setInteger(stack, "AIItemLevel", level);
				NBTHelper.setInteger(stack, "AIItemXP", xp);
				NBTHelper.setInteger(stack, "AIItemXPMax", xpMax);
			}else if(level >=5){
				xp = 0;
				xpMax = 0;
			}
		}

	}

	public static void addSpellXP(ItemStack stack, int amount) {
		int xp = NBTHelper.getInt(stack, "AIItemXP");
		xp += new Random().nextInt(amount * (NBTHelper.getInt(stack, "AIItemLevel")+1));
		NBTHelper.setInteger(stack, "AIItemXP", xp);
	}

}
