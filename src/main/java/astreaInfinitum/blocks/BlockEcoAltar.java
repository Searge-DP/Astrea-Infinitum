package astreaInfinitum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.api.IEcoAltarBlock;
import astreaInfinitum.client.particle.EntityEcoFX;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.tileEntities.TileEntityEcoAltar;

public class BlockEcoAltar extends Block implements ITileEntityProvider {

	protected BlockEcoAltar() {
		super(Material.rock);

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {

		if (player.getCurrentEquippedItem() != null) {
			if (player.getCurrentEquippedItem().getItem() == AIItems.bookBasic) {
				TileEntityEcoAltar tile = (TileEntityEcoAltar) world.getTileEntity(x, y, z);
				if (!world.isRemote && canActivate(world, x, y, z)) {
					tile.shouldConvert = true;
					tile.markDirty();
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
					if (world.getBlock(X, y, Z) == null || !(world.getBlock(X, y, Z) == Blocks.nether_brick || (world.getBlock(X, y, Z) == Blocks.quartz_block || world.getBlock(X, y, Z) instanceof IEcoAltarBlock))) {
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