package astreaInfinitum.runes;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.runes.EnumSpellType;
import astreaInfinitum.api.runes.RuneAction;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.potions.PotionEffectBase;
import astreaInfinitum.reference.PotionReference;
import astreaInfinitum.utils.AIUtils;

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
		if (entity instanceof EntityLivingBase){
			EntityLivingBase ent = (EntityLivingBase)entity;
			ent.addPotionEffect(new PotionEffectBase(PotionReference.potionIDLevitation, 30 * (spellLevel + 1), 1));
			return getSpellUsage(spellLevel);
		}
		return 0;
	}

	@Override
	public int onHitBlock(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block) {

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
		caster.addPotionEffect(new PotionEffectBase(PotionReference.potionIDLevitation, 30 * (spellLevel + 1), 1));
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
