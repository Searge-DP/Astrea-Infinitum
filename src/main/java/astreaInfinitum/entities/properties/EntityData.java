package astreaInfinitum.entities.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumEco;

public class EntityData implements IExtendedEntityProperties {

	private EntityLivingBase entity;
	private boolean knowledge;
	private int level;
	private int ecoLight;
	private int ecoDark;
	private int ecoMaxLight;
	private int ecoMaxDark;
	private int xp;
	private int maxXP;

	public static final String tagKnowledge = "AIKnowledge";
	public static final String taglevel = "AILevel";
	public static final String tagEcoLight = EnumEco.light.getNBTName();
	public static final String tagEcoDark = EnumEco.dark.getNBTName();
	public static final String tagEcoMaxLight = EnumEco.light.getNBTName() + "Max";
	public static final String tagEcoMaxDark = EnumEco.dark.getNBTName() + "Max";
	public static final String tagXP = "AIXP";
	public static final String tagXPMax = "AIXPMax";

	@Override
	public void saveNBTData(NBTTagCompound tag) {
		tag.setBoolean(tagKnowledge, knowledge);
		tag.setInteger(taglevel, level);
		tag.setInteger(tagEcoLight, ecoLight);
		tag.setInteger(tagEcoDark, ecoDark);
		tag.setInteger(tagEcoMaxLight, ecoMaxLight);
		tag.setInteger(tagEcoMaxDark, ecoMaxDark);
		tag.setInteger(tagXP, xp);
		tag.setInteger(tagXPMax, maxXP);
	}

	@Override
	public void loadNBTData(NBTTagCompound tag) {
		knowledge = tag.getBoolean(tagKnowledge);
		level = tag.getInteger(taglevel);
		ecoLight = tag.getInteger(tagEcoLight);
		ecoDark = tag.getInteger(tagEcoDark);
		ecoMaxLight = tag.getInteger(tagEcoMaxLight);
		ecoMaxDark = tag.getInteger(tagEcoMaxDark);
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

	public int getEcoLight() {
		return ecoLight;
	}

	public int getEcoDark() {
		return ecoDark;
	}

	public int getEcoMaxLight() {
		return ecoMaxLight;
	}

	public int getEcoMaxDark() {
		return ecoMaxDark;
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

	public void setEcoLight(int ecoLight) {
		this.ecoLight = ecoLight;
	}

	public void setEcoDark(int ecoDark) {
		this.ecoDark = ecoDark;
	}

	public void setEcoMaxLight(int ecoMaxLight) {
		this.ecoMaxLight = ecoMaxLight;
	}

	public void setEcoMaxDark(int ecoMaxDark) {
		this.ecoMaxDark = ecoMaxDark;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void setMaxXP(int maxXP) {
		this.maxXP = maxXP;
	}

}
