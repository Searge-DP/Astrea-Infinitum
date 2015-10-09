package astreaInfinitum.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.IEcoAltarBlock;
import astreaInfinitum.client.render.submaps.SubmapManagerCTM;
import astreaInfinitum.tileEntities.eco.TileEntityEcoAltarBlock;

import com.cricketcraft.ctmlib.ISubmapManager;

public class BlockEcoAltarBlock extends BlockAltarBlock implements IEcoAltarBlock, ITileEntityProvider {

	private SubmapManagerCTM ecoger;

	protected BlockEcoAltarBlock() {
		super(Material.rock);
		setHardness(0.5f);
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		ecoger = new SubmapManagerCTM("ecoAltarBlock");
		ecoger.registerIcons(ModProps.MODID, this, icon);
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return ecoger.getIcon(world, x, y, z, side);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return ecoger.getIcon(side, meta);
	}

	@Override
	public int getRenderType() {
		return AstreaInfinitum.ctmID;
	}

	@Override
	public ISubmapManager getSubMap() {
		return ecoger;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEcoAltarBlock();
	}

}