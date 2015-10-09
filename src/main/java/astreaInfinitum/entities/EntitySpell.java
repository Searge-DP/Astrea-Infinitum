package astreaInfinitum.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.spell.Spell;
import astreaInfinitum.client.particle.EntityEcoFX;
import astreaInfinitum.utils.AIUtils;

public class EntitySpell extends EntityThrowable {

	public Spell spell;
	public EntityPlayer caster;
	public int spellLevel;

	public EntitySpell(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntitySpell(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntitySpell(World world) {
		super(world);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (worldObj.isRemote) {
			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityEcoFX(worldObj, posX, posY, posZ, 0));
		}

		if (spell != null) {
			spell.function.action.onProjectileUpdate(worldObj, caster, this, spellLevel, posX, posY, posZ);
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!worldObj.isRemote && worldObj != null && mop != null && spell != null) {
			if (mop.typeOfHit == MovingObjectType.BLOCK) {
				spell.function.action.onHitBlock(worldObj, caster, spellLevel, mop.blockX, mop.blockY, mop.blockZ, worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ));
			}
			if (mop.typeOfHit == MovingObjectType.ENTITY) {
				spell.function.action.onHitEntity(worldObj, caster, spellLevel, mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ, mop.entityHit);
			}
			caster.addChatComponentMessage(new ChatComponentText("" + AIUtils.getPlayerEco(caster, EnumPlayerEco.light)));
			caster.addChatComponentMessage(new ChatComponentText("" + AIUtils.getPlayerEco(caster, EnumPlayerEco.dark)));

			this.setDead();
		}
	}

	public Spell getSpell() {
		return spell;
	}

	public void setSpell(Spell spell) {
		this.spell = spell;
	}

}
