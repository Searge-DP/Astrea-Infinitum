package astreaInfinitum.entities.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumMana;

public class EntityData implements IExtendedEntityProperties {

	private EntityLivingBase entity;
	private boolean knowledge;
	private int level;
	private int manaLight;
	private int manaDark;
	private int manaMaxLight;
	private int manaMaxDark;
	private int xp;
	private int maxXP;

	public static final String tagKnowledge = "AIKnowledge";
	public static final String taglevel = "AILevel";
	public static final String tagManaLight = EnumMana.light.getNBTName();
	public static final String tagManaDark = EnumMana.dark.getNBTName();
	public static final String tagManaMaxLight = EnumMana.light.getNBTName() + "Max";
	public static final String tagManaMaxDark = EnumMana.dark.getNBTName() + "Max";
	public static final String tagXP = "AIXP";
	public static final String tagXPMax = "AIXPMax";

	@Override
	public void saveNBTData(NBTTagCompound tag) {
		tag.setBoolean(tagKnowledge, knowledge);
		tag.setInteger(taglevel, level);
		tag.setInteger(tagManaLight, manaLight);
		tag.setInteger(tagManaDark, manaDark);
		tag.setInteger(tagManaMaxLight, manaMaxLight);
		tag.setInteger(tagManaMaxDark, manaMaxDark);
		tag.setInteger(tagXP, xp);
		tag.setInteger(tagXPMax, maxXP);
	}

	@Override
	public void loadNBTData(NBTTagCompound tag) {
		knowledge = tag.getBoolean(tagKnowledge);
		level = tag.getInteger(taglevel);
		manaLight = tag.getInteger(tagManaLight);
		manaDark = tag.getInteger(tagManaDark);
		manaMaxLight = tag.getInteger(tagManaMaxLight);
		manaMaxDark = tag.getInteger(tagManaMaxDark);
		xp = tag.getInteger(tagXP);
		maxXP = tag.getInteger(tagXPMax);
	}

	public static EntityData getInstance(EntityLivingBase entity) {
		return (EntityData) entity.getExtendedProperties(ModProps.modid);
	}

	@Override
	public void init(Entity entity, World world) {

		if (entity instanceof EntityLivingBase) {
			this.entity = (EntityLivingBase) entity;
		}
	}

	public boolean isKnowledge() {
		return knowledge;
	}

	public int getLevel() {
		return level;
	}

	public int getManaLight() {
		return manaLight;
	}

	public int getManaDark() {
		return manaDark;
	}

	public int getManaMaxLight() {
		return manaMaxLight;
	}

	public int getManaMaxDark() {
		return manaMaxDark;
	}

	public int getXp() {
		return xp;
	}

	public int getMaxXP() {
		return maxXP;
	}

	public void setKnowledge(boolean knowledge) {
		this.knowledge = knowledge;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setManaLight(int manaLight) {
		this.manaLight = manaLight;
	}

	public void setManaDark(int manaDark) {
		this.manaDark = manaDark;
	}

	public void setManaMaxLight(int manaMaxLight) {
		this.manaMaxLight = manaMaxLight;
	}

	public void setManaMaxDark(int manaMaxDark) {
		this.manaMaxDark = manaMaxDark;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void setMaxXP(int maxXP) {
		this.maxXP = maxXP;
	}

}
