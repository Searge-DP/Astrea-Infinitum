package astreaInfinitum.blocks.multiblocks;

import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import astreaInfinitum.blocks.AIBlocks;

public class MultiBlockEcoVent extends MultiBlockBase {

	@SuppressWarnings("unused")
	@Override
	public boolean canForm(IBlockAccess world, int x, int y, int z) {
		boolean allow = false;
		ItemStack[] width = new ItemStack[] {};
		return allow;
	}

	@Override
	public void form(World world, int x, int y, int z) {

	}

	@Override
	public ItemStack[][][] getMultiBlockBlocks() {
		int width = 3;
		int height = 4;
		int length = 3;
		ItemStack[][][] stacks = new ItemStack[width][length][height];

		for (int w = 0; w < width; w++) {
			for (int l = 0; l < length; l++) {
				for (int h = 0; h < height; h++) {
					stacks[w][l][h] = new ItemStack(AIBlocks.ecoBlock);
				}
			}
		}
		return null;
	}

	@Override
	public void breakMultiblock(World world, int x, int y, int z) {

	}

}
