package astreaInfinitum.entities;

import astreaInfinitum.api.spell.IProjectileSpell;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntitySpell extends EntityThrowable {

	public EntitySpell(World p_i1777_1_, EntityLivingBase p_i1777_2_) {
		super(p_i1777_1_, p_i1777_2_);
	}

	public EntitySpell(World p_i1778_1_, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_) {
		super(p_i1778_1_, p_i1778_2_, p_i1778_4_, p_i1778_6_);
	}

	public EntitySpell(World world) {
		super(world);
	}

	public IProjectileSpell spell;

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		worldObj.spawnParticle("reddust", posX, posY, posZ, 255, 0, 0);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (worldObj != null && mop != null && spell != null) {
			spell.onHit(worldObj, mop, this.posX, this.posY, this.posZ);
			this.setDead();
		}
	}

	public IProjectileSpell getSpell() {
		return spell;
	}

	public void setSpell(IProjectileSpell spell) {
		this.spell = spell;
	}

}
