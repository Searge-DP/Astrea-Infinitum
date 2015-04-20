package astreaInfinitum.utils;

import net.minecraft.util.StatCollector;

public class Lang {
	private String locKey;

	public Lang(String modid) {
		locKey = modid;
	}

	public String localize(String unloc, boolean appendModid) {
		if (appendModid)
			return translateToLocal(locKey + "." + unloc);
		else
			return translateToLocal(unloc);
	}

	public String localize(String unloc) {
		return localize(unloc, true);
	}

	private String translateToLocal(String unloc) {
		return StatCollector.translateToLocal(unloc);
	}

	public String[] localizeList(String unloc) {
		return splitList(localize(unloc, true));
	}

	public String[] splitList(String list, String splitRegex) {
		return list.split(splitRegex);
	}

	public String[] splitList(String list) {
		return splitList(list, "\\|");
	}

	public String getPrefix() {
		return locKey;
	}
}
