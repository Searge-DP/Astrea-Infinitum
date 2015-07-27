package astreaInfinitum.api.spell;

import astreaInfinitum.api.EnumEco;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ISpell {
	public boolean onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z);

	public EnumEco getEcoType();
}