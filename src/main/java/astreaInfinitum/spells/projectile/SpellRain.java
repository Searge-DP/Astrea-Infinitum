package astreaInfinitum.spells.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.spell.IProjectileSpell;
import astreaInfinitum.entities.EntitySpell;

public class SpellRain implements IProjectileSpell {

	@Override
	public boolean onHit(World world, EntityPlayer caster, MovingObjectPosition mop, double x, double y, double z) {
		return false;
	}

	@Override
	public EnumPlayerEco getEcoType() {
		return EnumPlayerEco.light;
	}

	@Override
	public void update(World world, EntityPlayer caster, EntitySpell spell, double x, double y, double z) {
		if (caster.posY + y > caster.posY + 8) {
			if (!world.isRaining()) {
				world.getWorldInfo().setRaining(true);
			}
			spell.setDead();
		}

	}
}
