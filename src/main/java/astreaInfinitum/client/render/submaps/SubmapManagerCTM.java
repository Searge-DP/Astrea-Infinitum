package astreaInfinitum.client.render.submaps;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.cricketcraft.ctmlib.RenderBlocksCTM;
import com.cricketcraft.ctmlib.TextureSubmap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SubmapManagerCTM extends SubmapManagerBase {

	@SideOnly(Side.CLIENT)
	private static RenderBlocksCTM rb;

	private TextureSubmap submap, submapSmall;
	private String textureName;

	public SubmapManagerCTM(String textureName) {
		this.textureName = textureName;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return submapSmall.getBaseIcon();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(String modName, Block block, IIconRegister register) {
		submap = new TextureSubmap(register.registerIcon(modName + ":" + textureName + "-ctm"), 4, 4);
		submapSmall = new TextureSubmap(register.registerIcon(modName + ":" + textureName), 2, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public RenderBlocks createRenderContext(RenderBlocks rendererOld, Block block, IBlockAccess world) {
		if (rb == null) {
			rb = new RenderBlocksCTM();
		}
		rb.setRenderBoundsFromBlock(block);
		rb.submap = submap;
		rb.submapSmall = submapSmall;
		return rb;
	}
}