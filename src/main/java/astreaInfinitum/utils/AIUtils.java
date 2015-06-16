package astreaInfinitum.utils;

import static astreaInfinitum.utils.AIUtils.getPlayerKnowledge;
import static astreaInfinitum.utils.AIUtils.getPlayerLevel;
import static astreaInfinitum.utils.AIUtils.getPlayerMana;
import static astreaInfinitum.utils.AIUtils.getPlayerManaMax;
import static astreaInfinitum.utils.AIUtils.getPlayerMaxXP;
import static astreaInfinitum.utils.AIUtils.getPlayerXP;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.entities.properties.EntityData;
import astreaInfinitum.network.MessagePlayerSync;
import astreaInfinitum.network.PacketHandler;

public class AIUtils {

	public static void initPlayer(EntityPlayer player) {
		setPlayerKnowledge(player, true);
		setPlayerLevel(player, 1);
		setPlayerMana(player, EnumMana.light, 0);
		setPlayerMana(player, EnumMana.dark, 0);
		setPlayerMaxMana(player, EnumMana.light, 40);
		setPlayerMaxMana(player, EnumMana.dark, 40);
		setPlayerMaxXP(player, 10);
		setPlayerXP(player, 0);
		addChatMessage(player, "level:" + getPlayerLevel(player));
		addChatMessage(player, "maxLight:" + getPlayerManaMax(player, EnumMana.light));
		addChatMessage(player, "maxDark:" + getPlayerManaMax(player, EnumMana.dark));
		addChatMessage(player, "currentLight:" + getPlayerMana(player, EnumMana.light));
		addChatMessage(player, "currentDark:" + getPlayerMana(player, EnumMana.dark));
		addChatMessage(player, "maxXP:" + getPlayerMaxXP(player));
		addChatMessage(player, "xp:" + getPlayerXP(player));
		PacketHandler.INSTANCE.sendToAll(new MessagePlayerSync(getPlayerKnowledge(player), getPlayerMana(player, EnumMana.light), getPlayerMana(player, EnumMana.dark), getPlayerLevel(player), getPlayerXP(player), getPlayerMaxXP(player), getPlayerManaMax(player, EnumMana.light), getPlayerManaMax(player, EnumMana.dark)));
		
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

	public static int getPlayerMana(EntityPlayer player, EnumMana mana) {
		if (mana == EnumMana.light) {
			return getEntityData(player).getManaLight();
		}
		if (mana == EnumMana.dark) {
			return getEntityData(player).getManaDark();
		}
		return 0;
	}

	public static int getPlayerManaMax(EntityPlayer player, EnumMana mana) {
		if (mana == EnumMana.light) {
			return getEntityData(player).getManaMaxLight();
		}
		if (mana == EnumMana.dark) {
			return getEntityData(player).getManaMaxDark();
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

	public static void setPlayerMana(EntityPlayer player, EnumMana mana, int amount) {
		if (mana == EnumMana.light) {
			getEntityData(player).setManaLight(amount);
		}
		if (mana == EnumMana.dark) {
			getEntityData(player).setManaDark(amount);
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

	public static void setPlayerMaxMana(EntityPlayer player, EnumMana mana, int max) {
		if (mana == EnumMana.light) {
			getEntityData(player).setManaMaxLight(max);
		}
		if (mana == EnumMana.dark) {
			getEntityData(player).setManaMaxDark(max);
		}
	}

	public static void removeMana(EntityPlayer player, EnumMana mana, int amount) {
		int manaA = 0;
		if (mana == EnumMana.light) {
			manaA = getEntityData(player).getManaLight();
		}
		if (mana == EnumMana.dark) {
			manaA = getEntityData(player).getManaDark();
		}
		manaA -= amount;
		setPlayerMana(player, mana, manaA);
	}

	public static void levelUp(EntityPlayer player) {
		if (getPlayerXP(player) >= getPlayerMaxXP(player)) {
			setPlayerXP(player, 0);
			setPlayerMaxXP(player, getPlayerMaxXP(player) + (getPlayerMaxXP(player) / 2));
			setPlayerMaxMana(player, EnumMana.light, (getPlayerManaMax(player, EnumMana.light) / 2) + getPlayerManaMax(player, EnumMana.light));
			setPlayerMaxMana(player, EnumMana.dark, (getPlayerManaMax(player, EnumMana.dark) / 2) + getPlayerManaMax(player, EnumMana.dark));
			setPlayerLevel(player, getPlayerLevel(player) + 1);
		}
	}

	public static void addMana(EntityPlayer player, EnumMana mana, int amount) {
		int manaA = getPlayerMana(player, mana);
		manaA += amount;
		setPlayerMana(player, mana, manaA);
	}

	public static void addXP(EntityPlayer player, int amount) {
		int xp = getPlayerXP(player);
		xp += amount * getPlayerLevel(player);
		setPlayerXP(player, xp);
	}

	public static boolean drainMana(EntityPlayer player, EnumMana mana, int amount) {
		int manaA = getPlayerMana(player, mana);
		if (manaA >= amount) {
			manaA -= amount;
			setPlayerMana(player, mana, manaA);
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
		}

	}

	public static void addSpellXP(ItemStack stack, int amount) {
		int xp = NBTHelper.getInt(stack, "AIItemXP");
		xp += new Random().nextInt(amount * NBTHelper.getInt(stack, "AIItemLevel"));
		NBTHelper.setInteger(stack, "AIItemXP", xp);
	}
}
