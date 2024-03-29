package astreaInfinitum.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.blocks.BlockCtm;

import com.cricketcraft.ctmlib.Drawing;
import com.cricketcraft.ctmlib.ISubmapManager;
import com.cricketcraft.ctmlib.RenderBlocksCTM;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderEcoAltarBlock implements ISimpleBlockRenderingHandler {

	public RenderBlocksCTM rendererCTM = new RenderBlocksCTM();

	public RenderEcoAltarBlock() {
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		RenderBlocks rb = getContext(renderer, block, Minecraft.getMinecraft().theWorld, ((BlockCtm) block).getSubMap(), metadata);
		Drawing.drawBlock(block, metadata, rb);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		rb.unlockBlockBounds();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks rendererOld) {
		int meta = world.getBlockMetadata(x, y, z);
		RenderBlocks rb = getContext(rendererOld, block, world, ((BlockCtm) block).getSubMap(), meta);
		boolean ret = rb.renderStandardBlock(block, x, y, z);
		rb.unlockBlockBounds();
		return ret;
	}

	private RenderBlocks getContext(RenderBlocks rendererOld, Block block, IBlockAccess world, ISubmapManager submap, int meta) {
		if (!rendererOld.hasOverrideBlockTexture()) {
			RenderBlocks rb = submap.createRenderContext(rendererOld, block, world);
			if (rb != null && rb != rendererOld) {
				rb.blockAccess = world;
				if (rendererOld.lockBlockBounds) {
					rb.overrideBlockBounds(rendererOld.renderMinX, rendererOld.renderMinY, rendererOld.renderMinZ, rendererOld.renderMaxX, rendererOld.renderMaxY, rendererOld.renderMaxZ);
				}
				if (rb instanceof RenderBlocksCTM) {
					RenderBlocksCTM rbctm = (RenderBlocksCTM) rb;
					rbctm.manager = rbctm.manager == null ? submap : rbctm.manager;
					rbctm.rendererOld = rbctm.rendererOld == null ? rendererOld : rbctm.rendererOld;
				}
				return rb;
			}
		}
		return rendererOld;
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