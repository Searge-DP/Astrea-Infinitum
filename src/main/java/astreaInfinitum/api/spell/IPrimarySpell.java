package astreaInfinitum.api.spell;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.MutablePair;

public interface IPrimarySpell {

	public String getName();

	public int getEcoUsage();

	public void onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z);

}