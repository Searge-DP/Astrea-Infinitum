package astreaInfinitum.spells.projectile;

import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.api.spell.IProjectileSpell;

public class SpellProjDig implements IProjectileSpell {

	@Override
	public boolean onHit(World world,  MovingObjectPosition mop,  double x, double y, double z) {
		if(mop.typeOfHit == MovingObjectType.BLOCK){
			world.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
		}
		return false;
	}

	@Override
	public EnumMana getManaType() {
		return EnumMana.light;
	}

}
