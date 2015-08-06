package astreaInfinitum.world;

import java.util.Random;

import astreaInfinitum.blocks.AIBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class GenerationHandler implements IWorldGenerator {
	public boolean gen = true;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}

	private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {
	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		for (int k = 0; k < 6; k++) {
			int oreXCoord = chunkX + rand.nextInt(16);
			int oreYCoord = rand.nextInt(64);
			int oreZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(AIBlocks.ecoOre, 0, 5, Blocks.stone)).generate(world, rand, oreXCoord, oreYCoord, oreZCoord);
		}
		for (int k = 0; k < 6; k++) {
			int oreXCoord = chunkX + rand.nextInt(16);
			int oreYCoord = rand.nextInt(64);
			int oreZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(AIBlocks.ecoOre, 1, 5, Blocks.stone)).generate(world, rand, oreXCoord, oreYCoord, oreZCoord);
		}
		for (int k = 0; k < 6; k++) {
			int oreXCoord = chunkX + rand.nextInt(16);
			int oreYCoord = rand.nextInt(64);
			int oreZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(AIBlocks.ecoOre, 2, 5, Blocks.stone)).generate(world, rand, oreXCoord, oreYCoord, oreZCoord);
		}
		for (int k = 0; k < 6; k++) {
			int oreXCoord = chunkX + rand.nextInt(16);
			int oreYCoord = rand.nextInt(64);
			int oreZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(AIBlocks.ecoOre, 3, 5, Blocks.stone)).generate(world, rand, oreXCoord, oreYCoord, oreZCoord);
		}
		for (int k = 0; k < 6; k++) {
			int oreXCoord = chunkX + rand.nextInt(16);
			int oreYCoord = rand.nextInt(64);
			int oreZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(AIBlocks.ecoOre, 4, 5, Blocks.stone)).generate(world, rand, oreXCoord, oreYCoord, oreZCoord);
		}
		for (int k = 0; k < 6; k++) {
			int oreXCoord = chunkX + rand.nextInt(16);
			int oreYCoord = rand.nextInt(64);
			int oreZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(AIBlocks.ecoOre, 5, 5, Blocks.stone)).generate(world, rand, oreXCoord, oreYCoord, oreZCoord);
		}
	}

	private void generateNether(World world, Random rand, int chunkX, int chunkZ) {
	}
}