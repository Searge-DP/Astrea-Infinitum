package astreaInfinitum.blocks.eco;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.tileEntities.eco.TileEntityEcoVent;

/**
 * Created by Jared on 8/15/2015.
 */
public class BlockEcoVent extends Block implements ITileEntityProvider {

	public BlockEcoVent() {
		super(Material.iron);
		
		setLightLevel(1);
		setLightOpacity(1);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEcoVent();
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	

	@Override
	public int getRenderType() {
		return -1;
	}
}
