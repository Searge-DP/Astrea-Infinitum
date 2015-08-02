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

public class BlockEcoAltarWhite extends BlockAltarBlock implements IEcoAltarBlock {

	private SubmapManagerCTM manager;

	protected BlockEcoAltarWhite() {
		super(Material.rock);
		setHardness(0.5f);
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		manager = new SubmapManagerCTM("ecoRitualBlock");
		manager.registerIcons(ModProps.MODID, this, icon);
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return manager.getIcon(world, x, y, z, side);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return manager.getIcon(side, meta);
	}

	@Override
	public int getRenderType() {
		return AstreaInfinitum.ctmID;
	}

	@Override
	public ISubmapManager getSubMap() {
		return manager;
	}

}