package astreaInfinitum.runes;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.runes.EnumSpellType;
import astreaInfinitum.api.runes.RuneAction;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.utils.AIUtils;

public class RuneActionLightning extends RuneAction {

	public RuneActionLightning() {
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
		world.addWeatherEffect(new EntityLightningBolt(world, x, y, z));
		return getSpellUsage(spellLevel);
	}

	@Override
	public int onHitBlock(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block) {
		world.addWeatherEffect(new EntityLightningBolt(world, x, y, z));
		return 0;
	}

	@Override
	public EnumPlayerEco getEcoUsed() {
		return EnumPlayerEco.light;
	}

	@Override
	public int getSpellUsage(int spellLevel) {
		return 20 * (spellLevel + 1);
	}

	@Override
	public int onHitSelf(World world, EntityPlayer caster, int spellLevel, int x, int y, int z) {
		world.addWeatherEffect(new EntityLightningBolt(world, x, y, z));
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
