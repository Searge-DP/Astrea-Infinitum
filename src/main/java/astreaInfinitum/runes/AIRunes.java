package astreaInfinitum.runes;

import astreaInfinitum.api.runes.RuneAction;

public class AIRunes {

	public static RuneAction dig;
	public static RuneAction heal;
	

	public static void preInit() {
		dig = new RuneActionDig();
		heal = new RuneActionHeal();
		
	}
}
