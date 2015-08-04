package astreaInfinitum.api.runes;

import astreaInfinitum.runes.AIRunes;
import astreaInfinitum.runes.RuneActionDig;

public enum EnumRuneFunction {
	dig("dig", AIRunes.dig);

	public RuneAction action;
	public String name;

	private EnumRuneFunction(String name, RuneAction action) {
		this.name = name;
		this.action = action;
	}

}
