package astreaInfinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.tileEntities.eco.TileEntityEcoAltar;

public class BlockEcoAltar extends Block implements ITileEntityProvider {

	public BlockEcoAltar() {
		super(Material.rock);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {

		if (player.getCurrentEquippedItem() != null) {
			if (player.getCurrentEquippedItem().getItem() == AIItems.bookBasic) {
				TileEntityEcoAltar tile = (TileEntityEcoAltar) world.getTileEntity(x, y, z);
				if (canActivate(world, x, y, z)) {
					for (int X = x - 3; X <= x + 3; X++) {
						for (int Z = z - 3; Z <= z + 3; Z++) {
							Block block = world.getBlock(X, y, Z);
							if (block != null) {
								if (world.getBlock(X, y, Z) == Blocks.nether_brick) {
									world.setBlock(X, y, Z, AIBlocks.ecoAltarBlock);
									if (world.isRemote) {
										for (int i = 0; i < 16; i++)
											world.spawnParticle("blockdust_" + Block.getIdFromBlock(world.getBlock(X, y, Z)) + "_0", X + world.rand.nextDouble(), y, Z + world.rand.nextDouble(), 0, 0.8, 0);
									}
								}
								if (world.getBlock(X, y, Z) == Blocks.quartz_block) {
									world.setBlock(X, y, Z, AIBlocks.ecoRitualBlock);
									if (world.isRemote) {
										for (int i = 0; i < 16; i++)
											world.spawnParticle("blockdust_" + Block.getIdFromBlock(world.getBlock(X, y, Z)) + "_0", X + world.rand.nextDouble(), y, Z + world.rand.nextDouble(), 0, 0.8, 0);
									}
								}
							}
						}
					}
					tile.activated = true;
				}
				return true;
			}
		}
		return false;
	}

	public boolean canActivate(World world, int x, int y, int z) {
		for (int X = x - 3; X <= x + 3; X++) {
			for (int Z = z - 3; Z <= z + 3; Z++) {
				if (X != x && Z != z)
					if (world.getBlock(X, y, Z) == Blocks.air || !(world.getBlock(X, y, Z) == Blocks.nether_brick || (world.getBlock(X, y, Z) == Blocks.quartz_block))) {
						return false;
					}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEcoAltar();
	}

}