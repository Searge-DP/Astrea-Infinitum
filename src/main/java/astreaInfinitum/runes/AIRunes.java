package astreaInfinitum.runes;

import astreaInfinitum.api.runes.RuneAction;

public class AIRunes {

	public static RuneAction dig;
	public static RuneAction heal;
	public static RuneAction levitate;
	public static RuneAction lightning;


	public static void preInit() {
		dig = new RuneActionDig();
		heal = new RuneActionHeal();
		levitate = new RuneActionLevitate();

		lightning = new RuneActionLightning();


	}
}
