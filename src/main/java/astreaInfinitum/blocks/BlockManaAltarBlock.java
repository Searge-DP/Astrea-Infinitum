package astreaInfinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import astreaInfinitum.AstreaInfinitum;

public class BlockManaAltarBlock extends Block {

	public IIcon submap, submapSmall;

	protected BlockManaAltarBlock() {
		super(Material.rock);
	}

	@Override
	public int getRenderType() {
		return AstreaInfinitum.ctmID;
	}

	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return submapSmall;
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {

		submap = icon.registerIcon(textureName + "-ctm");
		submapSmall = icon.registerIcon(textureName);
		this.blockIcon = submapSmall;
	}

}
