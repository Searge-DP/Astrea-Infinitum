package astreaInfinitum.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.IEcoAltarBlock;
import astreaInfinitum.client.render.submaps.SubmapManagerCTM;

import com.cricketcraft.ctmlib.ISubmapManager;

public class BlockEcoAltarBlock extends BlockAltarBlock implements IEcoAltarBlock {

	private SubmapManagerCTM ecoger;

	protected BlockEcoAltarBlock() {
		super(Material.rock);
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

}