package astreaInfinitum.config;

import astreaInfinitum.reference.PotionReference;
import astreaInfinitum.reference.SpellReference;

import java.io.File;

public class Configuration {

	public static net.minecraftforge.common.config.Configuration config = null;

	public static void preInit(File file) {
		config = new net.minecraftforge.common.config.Configuration(file);
		config.load();
		SpellReference.digCastTime = config.getInt("Dig Cast Time", "SPELLS", SpellReference.digCastTime, 0, Integer.MAX_VALUE, "How long does it take to cast, in ticks. (seconds*20)");
		SpellReference.digEcoUsage = config.getInt("Dig Eco Usage", "SPELLS", SpellReference.digEcoUsage, 0, Integer.MAX_VALUE, "How much eco does it take to cast?");


		PotionReference.potionIDLevitation = getPotionID("Levitation", 32);
		config.save();
	}

	private static int getPotionID(String name, int defaultID) {
		return config.getInt(name + "ID", "POTIONS", defaultID, 0, 127, "PotionID for: " + name);
	}
}
