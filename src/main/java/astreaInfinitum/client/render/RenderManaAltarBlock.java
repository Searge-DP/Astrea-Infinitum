package astreaInfinitum.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.blocks.BlockCtm;
import astreaInfinitum.blocks.BlockManaAltarBlock;

import com.cricketcraft.ctmlib.Drawing;
import com.cricketcraft.ctmlib.ISubmapManager;
import com.cricketcraft.ctmlib.RenderBlocksCTM;
import com.cricketcraft.ctmlib.TextureSubmap;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderManaAltarBlock implements ISimpleBlockRenderingHandler {

	public RenderBlocksCTM rendererCTM = new RenderBlocksCTM();

	public RenderManaAltarBlock() {
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		Drawing.drawBlock(block, metadata, rendererCTM);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		rendererCTM.unlockBlockBounds();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks rendererOld) {
		if (block != null) {
			rendererCTM.submap = new TextureSubmap(((BlockManaAltarBlock) block).submap, 4, 4);
			rendererCTM.submapSmall = new TextureSubmap(((BlockManaAltarBlock) block).submapSmall, 2, 2);
			boolean ret = rendererCTM.renderStandardBlock(block, x, y, z);
			rendererCTM.unlockBlockBounds();
			return ret;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int renderId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return AstreaInfinitum.ctmID;
	}
}