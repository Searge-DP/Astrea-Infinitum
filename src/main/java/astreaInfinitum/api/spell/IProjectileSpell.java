package astreaInfinitum.api.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumMana;

public interface IProjectileSpell {

	public boolean onHit(World world, MovingObjectPosition mop, double x, double y, double z);

	public EnumMana getManaType();
}
