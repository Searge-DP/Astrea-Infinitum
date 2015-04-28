package astreaInfinitum.spells.projectile;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.api.spell.IProjectileSpell;

public class SpellProjTrans implements IProjectileSpell {

	@Override
	public boolean onHit(World world, EntityPlayer caster, MovingObjectPosition mop, double x, double y, double z) {
		if (!world.isRemote)
			if (mop.typeOfHit == MovingObjectType.ENTITY) {
				if (mop.entityHit.getEntityData().getBoolean("trans")) {
					return false;
				}
				if (mop.entityHit.isEntityEqual(new EntitySheep(world))) {
					return false;
				}
				if (mop.entityHit instanceof EntityLiving && !EntityList.getEntityString(mop.entityHit).isEmpty()) {
					EntitySheep sheep = new EntitySheep(world);
					sheep.setPosition(mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ);
					sheep.getEntityData().setBoolean("trans", true);
					sheep.getEntityData().setInteger("transTime", 100);
					NBTTagCompound tag = new NBTTagCompound();
					mop.entityHit.writeToNBT(tag);
					sheep.getEntityData().setTag("prevEnt", tag);
					sheep.getEntityData().setString("prevEntName", EntityList.getEntityString(mop.entityHit));
					world.spawnEntityInWorld(sheep);
					mop.entityHit.setDead();
					return true;
				}
			}
		return false;
	}

	@Override
	public EnumMana getManaType() {
		return EnumMana.dark;
	}

}
