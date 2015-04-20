package astreaInfinitum.utils;

import net.minecraft.entity.player.EntityPlayer;
import astreaInfinitum.api.EnumMana;

public class AIUtils {

	public static boolean getPlayerKnowledge(EntityPlayer player) {
		return player.getEntityData().getBoolean("AIKnowledge");
	}

	public static int getPlayerMana(EntityPlayer player, EnumMana mana) {
		return player.getEntityData().getInteger(mana.getNBTName());
	}

	public static int getPlayerManaMax(EntityPlayer player, EnumMana mana) {
		if (player.getEntityData().getInteger(mana.getNBTName() + "Max") == 0) {
			return 10;
		}
		return player.getEntityData().getInteger(mana.getNBTName() + "Max");
	}

	public static int getPlayerMaxXP(EntityPlayer player) {
		return player.getEntityData().getInteger("AIPlayerXPMax");
	}

	public static int getPlayerXP(EntityPlayer player) {
		return player.getEntityData().getInteger("AIPlayerXP");
	}

	public static int getPlayerLevel(EntityPlayer player) {
		return player.getEntityData().getInteger("AIPlayerLevel");
	}

	public static void setPlayerKnowledge(EntityPlayer player, boolean bool) {
		player.getEntityData().setBoolean("AIKnowledge", bool);
	}

	public static void setPlayerMana(EntityPlayer player, EnumMana mana, int amount) {
		player.getEntityData().setInteger(mana.getNBTName(), amount);
	}

	public static void setPlayerMaxXP(EntityPlayer player, int max) {
		player.getEntityData().setInteger("AIPlayerXPMax", max);
	}

	public static void setPlayerXP(EntityPlayer player, int xp) {
		player.getEntityData().setInteger("AIPlayerXP", xp);
	}

	public static void setPlayerLevel(EntityPlayer player, int level) {
		player.getEntityData().setInteger("AIPlayerLevel", level);
	}

	public static void setPlayerMaxMana(EntityPlayer player, EnumMana mana, int max) {
		player.getEntityData().setInteger(mana.getNBTName() + "Max", max);
	}

	public static void removeMana(EntityPlayer player, EnumMana mana, int amount) {
		int manaA = getPlayerMana(player, mana);
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
		xp += amount;
		setPlayerXP(player, xp);
	}

}
