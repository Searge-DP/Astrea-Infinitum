package astreaInfinitum.api.runes;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.entities.EntitySpell;

public abstract class RuneAction {

	public abstract boolean canCast(World world, EntityPlayer caster, int spellLevel, int x, int y, int z);

	public abstract int onHitEntity(World world, EntityPlayer caster, int spellLevel, double x, double y, double z, Entity entity);

	public abstract int onHitBlock(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block);

	public abstract int onHitSelf(World world, EntityPlayer caster, int spellLevel, int x, int y, int z);

	public abstract int onHitTouch(World world, EntityPlayer caster, int spellLevel, int x, int y, int z, Block block);

	public abstract int onProjectileUpdate(World world, EntityPlayer caster, EntitySpell spell, int spellLevel, double x, double y, double z);

	public abstract EnumPlayerEco getEcoUsed();

	public abstract int getSpellUsage(int spellLevel);

	public abstract EnumSpellType[] getActionTypes();
}
