package astreaInfinitum.api.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.entities.EntitySpell;

public interface IProjectileSpell {

	public boolean onHit(World world, EntityPlayer caster, MovingObjectPosition mop, double x, double y, double z);

	public EnumPlayerEco getEcoType();
	
	public void update(World world, EntityPlayer caster, EntitySpell spell, double x, double y, double z);
}
