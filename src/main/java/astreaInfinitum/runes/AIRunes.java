package astreaInfinitum.runes;

import astreaInfinitum.api.runes.RuneAction;

public class AIRunes {

	public static RuneAction dig;

	public static void preInit() {
		dig = new RuneActionDig();
	}
}
