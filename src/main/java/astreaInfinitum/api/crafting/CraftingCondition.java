package astreaInfinitum.api.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface CraftingCondition {

	public boolean canCraft(World world, EntityPlayer player, int x, int y, int z);
}
