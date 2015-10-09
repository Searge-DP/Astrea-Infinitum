package astreaInfinitum.reference;

import astreaInfinitum.buffs.BuffLevitation;
import fluxedCore.buffs.Buff;

public class BuffReference {

	public static Buff levitation;

	public static void init() {
		levitation = new BuffLevitation();
	}

}
