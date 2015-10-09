package astreaInfinitum.buffs;

import fluxedCore.buffs.Buff;
import fluxedCore.buffs.BuffHelper;
import fluxedCore.util.ResourceInformation;

public abstract class AIBuffBase extends Buff {

	public AIBuffBase(String unlocalizedName, boolean isDebuff, ResourceInformation resource) {
		super(unlocalizedName, isDebuff, resource);
		BuffHelper.registerBuff(getUnlocalizedName(), this);
	}

}
