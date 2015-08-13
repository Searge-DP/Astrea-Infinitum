package astreaInfinitum.runes;

import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.runes.EnumSpellType;
import astreaInfinitum.api.runes.RuneAction;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.utils.AIUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class RuneActionDig extends RuneAction {

	public RuneActionDig() {
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
		return 0;
	}

	@Override
	public int onHitBlock(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block) {
		if (block.getBlockHardness(world, x, y, z) <= 2 + (spellLevel * 0.5) && block.getBlockHardness(world, x, y, z) >= 0) {
			block.breakBlock(world, x, y, z, block, world.getBlockMetadata(x, y, z));
			block.harvestBlock(world, caster, x, y, z, world.getBlockMetadata(x, y, z));
			world.setBlockToAir(x, y, z);
			return getSpellUsage(spellLevel);
		}
		return 0;
	}

	@Override
	public EnumPlayerEco getEcoUsed() {
		return EnumPlayerEco.dark;
	}

	@Override
	public int getSpellUsage(int spellLevel) {
		return 5 * (spellLevel + 1);
	}

	@Override
	public int onHitSelf(World world, EntityPlayer caster, int spellLevel, int x, int y, int z) {
		return 0;
	}

	@Override
	public int onHitTouch(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block) {
		if (block.getBlockHardness(world, x, y, z) <= 2 + (spellLevel * 0.5) && block.getBlockHardness(world, x, y, z) >= 0) {
			block.breakBlock(world, x, y, z, block, world.getBlockMetadata(x, y, z));
			block.harvestBlock(world, caster, x, y, z, world.getBlockMetadata(x, y, z));
			world.setBlockToAir(x, y, z);
			return getSpellUsage(spellLevel);
		}
		return 0;
	}

	@Override
	public EnumSpellType[] getActionTypes() {
		return new EnumSpellType[]{EnumSpellType.projectile, EnumSpellType.touch};
	}

	@Override
	public int onProjectileUpdate(World world, EntityPlayer caster, EntitySpell spell, int spellLevel, double x, double y, double z) {
		return 0;
	}

}