package astreaInfinitum.spells.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumEco;
import astreaInfinitum.api.spell.IProjectileSpell;
import astreaInfinitum.entities.EntitySpell;

public class SpellProjDig implements IProjectileSpell {

	@Override
	public boolean onHit(World world,EntityPlayer caster,  MovingObjectPosition mop,  double x, double y, double z) {
		if(mop.typeOfHit == MovingObjectType.BLOCK){
			world.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
		}
		return false;
	}

	@Override
	public EnumEco getEcoType() {
		return EnumEco.light;
	}


	@Override
	public void update(World world, EntityPlayer caster, EntitySpell spell, double x, double y, double z){
		// TODO Auto-generated method stub
		
	}

}
