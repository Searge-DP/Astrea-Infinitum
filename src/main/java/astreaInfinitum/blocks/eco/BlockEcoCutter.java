package astreaInfinitum.blocks.eco;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.ModProps;
import astreaInfinitum.tileEntities.eco.TileEntityEcoCutter;

public class BlockEcoCutter extends BlockContainer {

	public BlockEcoCutter() {
		super(Material.iron);
		setBlockTextureName(ModProps.MODID + ":ecoCutter");
		setLightOpacity(1);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEcoCutter();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

}
