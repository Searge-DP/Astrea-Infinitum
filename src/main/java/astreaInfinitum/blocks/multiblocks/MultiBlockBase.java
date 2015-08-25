package astreaInfinitum.blocks.multiblocks;

import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class MultiBlockBase {

	public abstract boolean canForm(IBlockAccess world, int x, int y, int z);

	public abstract void form(World world, int x, int y, int z);
	
	public abstract void breakMultiblock(World world, int x, int y, int z);
	
	public abstract ItemStack[][][] getMultiBlockBlocks();
		
	

}
