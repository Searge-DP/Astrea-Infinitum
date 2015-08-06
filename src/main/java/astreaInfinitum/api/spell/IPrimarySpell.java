package astreaInfinitum.api.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IPrimarySpell {

	public String getName();

	public int getEcoUsage();

	public void onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z);

}