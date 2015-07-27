package astreaInfinitum.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumEco;
import astreaInfinitum.api.spell.ISpell;
import astreaInfinitum.utils.RenderingUtils;

public class SpellDay implements ISpell {

	@Override
	public boolean onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z) {
		world.setWorldTime(0);
		
		return true;
	}

	@Override
	public EnumEco getEcoType() {
		return EnumEco.light;
	}

}
