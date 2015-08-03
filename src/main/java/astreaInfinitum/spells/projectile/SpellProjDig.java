package astreaInfinitum.spells.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.spell.IProjectileSpell;
import astreaInfinitum.entities.EntitySpell;

public class SpellProjDig implements IProjectileSpell {

	@Override
	public boolean onHit(World world, EntityPlayer caster, MovingObjectPosition mop, double x, double y, double z) {
		if (mop.typeOfHit == MovingObjectType.BLOCK) {
			Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);

			block.breakBlock(world, mop.blockX, mop.blockY, mop.blockZ, block, world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ));
			block.harvestBlock(world, caster, mop.blockX, mop.blockY, mop.blockZ, world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ));
			world.setBlockToAir(mop.blockX, mop.blockY, mop.blockZ);
		}
		return false;
	}

	@Override
	public EnumPlayerEco getEcoType() {
		return EnumPlayerEco.light;
	}

	@Override
	public void update(World world, EntityPlayer caster, EntitySpell spell, double x, double y, double z) {

	}

}
