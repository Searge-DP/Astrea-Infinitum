package astreaInfinitum.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.api.spell.ISpell;

public class SpellDay implements ISpell {

	@Override
	public boolean onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z) {
		world.setWorldTime(0);
		return true;
	}

	@Override
	public EnumMana getManaType() {
		return EnumMana.light;
	}

}
