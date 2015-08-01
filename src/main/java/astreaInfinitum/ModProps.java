package astreaInfinitum;

public class ModProps {

	public static final String modid = "astreainfinitum", MODID = modid;
	public static final String version = "1.0.0", VERSION = version;
	public static final String name = "Astrea Infinitum", NAME = name;

	public static int getRedFromEco(String eco) {
		if (eco.equalsIgnoreCase("red") || eco.equalsIgnoreCase("yellow")) {
			return 255;
		} else
			return 0;
	}
}
