package astreaInfinitum.config;

import java.io.File;

import astreaInfinitum.reference.SpellReference;

public class Configuration {

	public static void preInit(File file) {
		net.minecraftforge.common.config.Configuration config = new net.minecraftforge.common.config.Configuration(file);
		config.load();
		SpellReference.digCastTime = config.getInt("Dig Cast Time", "SPELLS", SpellReference.digCastTime, 0, Integer.MAX_VALUE, "How long does it take to cast, in ticks. (seconds*20)");
		SpellReference.digEcoUsage = config.getInt("Dig Eco Usage", "SPELLS", SpellReference.digEcoUsage, 0, Integer.MAX_VALUE, "How much eco does it take to cast?");

		config.save();
	}
}
