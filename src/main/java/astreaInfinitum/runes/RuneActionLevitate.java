package astreaInfinitum.runes;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.runes.EnumSpellType;
import astreaInfinitum.api.runes.RuneAction;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.reference.BuffReference;
import astreaInfinitum.utils.AIUtils;
import fluxedCore.buffs.BuffEffect;
import fluxedCore.buffs.BuffHelper;

public class RuneActionLevitate extends RuneAction {

	public RuneActionLevitate() {
		super();
	}

	@Override
	public boolean canCast(World world, EntityPlayer caster, int spellLevel, int x, int y, int z) {
		if (AIUtils.getPlayerEco(caster, getEcoUsed()) >= getSpellUsage(spellLevel)) {
			return true;
		}
		return false;
	}

	@Override
	public int onHitEntity(World world, EntityPlayer caster, int spellLevel, double x, double y, double z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase ent = (EntityLivingBase) entity;
			BuffHelper.applyToEntity(world, ent, new BuffEffect(BuffReference.levitation, 30 * (spellLevel + 1), 1));
			return getSpellUsage(spellLevel);
		}
		return 0;
	}

	@Override
	public int onHitBlock(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block) {
		EntityFallingBlock fall = new EntityFallingBlock(world, x + 0.5, y + 0.5, z + 0.5, block, world.getBlockMetadata(x, y, z));
		fall.motionY = 0.5;
		world.spawnEntityInWorld(fall);

		return 0;
	}

	@Override
	public EnumPlayerEco getEcoUsed() {
		return EnumPlayerEco.light;
	}

	@Override
	public int getSpellUsage(int spellLevel) {
		return 10 * (spellLevel + 1);
	}

	@Override
	public int onHitSelf(World world, EntityPlayer caster, int spellLevel, int x, int y, int z) {
		// caster.addPotionEffect(new
		// PotionEffectBase(PotionReference.potionIDLevitation, 30 * (spellLevel
		// + 1), 1));
		if (caster.isRiding()) {
			Entity ent = caster.ridingEntity;
			if (ent instanceof EntityLivingBase) {
				BuffHelper.applyToEntity(world, (EntityLivingBase) ent, new BuffEffect(BuffReference.levitation, 1000 * (spellLevel + 1), 1));
			}
		} else
			BuffHelper.applyToEntity(world, caster, new BuffEffect(BuffReference.levitation, 30 * (spellLevel + 1), 1));
		return getSpellUsage(spellLevel);
	}

	@Override
	public int onHitTouch(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block) {
		return 0;
	}

	@Override
	public EnumSpellType[] getActionTypes() {
		return new EnumSpellType[] { EnumSpellType.projectile, EnumSpellType.self };
	}

	@Override
	public int onProjectileUpdate(World world, EntityPlayer caster, EntitySpell spell, int spellLevel, double x, double y, double z) {
		return 0;
	}

}
