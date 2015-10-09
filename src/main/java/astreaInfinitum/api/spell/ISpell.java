package astreaInfinitum.api.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumPlayerEco;

public interface ISpell {
	public boolean onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z);

	public EnumPlayerEco getEcoType();
	
	
}