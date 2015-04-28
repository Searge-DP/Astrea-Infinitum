package astreaInfinitum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockManaAltarBlock extends Block implements ITileEntityProvider {

	protected BlockManaAltarBlock() {
		super(Material.rock);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote)
			if (world.getBlock(x, y + 1, z) == null) {
				if (world.getBlock(x + 1, y, z) == this && world.getBlock(x + 1, y + 1, z) == Blocks.fire) {
					world.setBlock(x, y + 1, z, Blocks.fire);
				}
				if (world.getBlock(x - 1, y, z) == this && world.getBlock(x - 1, y + 1, z) == Blocks.fire) {
					world.setBlock(x, y + 1, z, Blocks.fire);
				}
				if (world.getBlock(x, y, z + 1) == this && world.getBlock(x, y + 1, z + 1) == Blocks.fire) {
					world.setBlock(x, y + 1, z, Blocks.fire);
				}
				if (world.getBlock(x, y, z - 1) == this && world.getBlock(x, y + 1, z - 1) == Blocks.fire) {
					world.setBlock(x, y + 1, z, Blocks.fire);
				}
			}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntity() {
			@Override
			public void updateEntity() {
				worldObj.getBlock(xCoord, yCoord, zCoord).updateTick(worldObj, xCoord, yCoord, zCoord, worldObj.rand);
			}
		};
	}

}
