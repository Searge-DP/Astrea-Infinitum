package astreaInfinitum.api.runes;

import astreaInfinitum.runes.AIRunes;

public enum EnumRuneFunction {
	dig("dig", AIRunes.dig), heal("heal", AIRunes.heal);

	public RuneAction action;
	public String name;

	private EnumRuneFunction(String name, RuneAction action) {
		this.name = name;
		this.action = action;
	}

}
