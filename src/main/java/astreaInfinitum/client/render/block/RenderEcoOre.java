package astreaInfinitum.client.render.block;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.blocks.eco.world.BlockEcoOre;
import astreaInfinitum.client.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderEcoOre implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		// // renderer.setOverrideBlockTexture(block.getIcon(0, metadata));
		// renderer.setOverrideBlockTexture(((BlockEcoOre) block).underlay);
		// renderer.renderBlockAsItem(Blocks.stone, 0, 1.0f);
		// renderer.unlockBlockBounds();
		// Tessellator tessellator = Tessellator.instance;
		// tessellator.setBrightness(0xFFFFFF);
		// switch (metadata) {
		// case 0:
		// int off = new Random().nextInt(15);
		// tessellator.setColorRGBA(255 - off, 0, 0, 100);
		// break;
		// case 1:
		// int offf = new Random().nextInt(15);
		// tessellator.setColorRGBA(0, 255 - offf, 0, 100);
		// break;
		// case 2:
		// int offff = new Random().nextInt(15);
		// tessellator.setColorRGBA(0, 0, 255 - offff, 100);
		// break;
		// case 3:
		// int offfff = new Random().nextInt(15);
		// tessellator.setColorRGBA(255 - offfff, 246 - offfff, 0, 100);
		// break;
		// }
		// renderer.setOverrideBlockTexture(((BlockEcoOre) block).overlay);
		// renderer.renderBlockAsItem(Blocks.stone, 0, 1.0f);
		// renderer.clearOverrideBlockTexture();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		Color col = null;
		int off = 0;
		switch (metadata) {
		case 0:
			off = new Random().nextInt(15);
			col = new Color(255 - off, 0, 0);
			RenderUtils.setGLColorFromInt(col.getRGB());
			break;
		case 1:
			off = new Random().nextInt(15);
			col = new Color(0, 255 - off, 0);
			RenderUtils.setGLColorFromInt(col.getRGB());
			break;
		case 2:
			off = new Random().nextInt(15);
			col = new Color(0, 0, 255 - off);
			RenderUtils.setGLColorFromInt(col.getRGB());
			break;
		case 3:
			off = new Random().nextInt(15);
			col = new Color(255 - off, 246 - off, 0);
			RenderUtils.setGLColorFromInt(col.getRGB());
			break;
		case 4:
			off = new Random().nextInt(15);
			col = new Color(255 - off, 255 - off, 255 - off);
			RenderUtils.setGLColorFromInt(col.getRGB());
			break;
		case 5:
			off = new Random().nextInt(15);
			col = new Color(119 + off, 0 + off, 188 + off);
			RenderUtils.setGLColorFromInt(col.getRGB());
			break;
		default:
			break;
		}

		GL11.glDisable(GL11.GL_LIGHTING);
		RenderUtils.drawBlock(block, ((BlockEcoOre) block).overlay, renderer);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glColor3f(1, 1, 1);
		RenderUtils.drawBlock(block, metadata, renderer);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.setOverrideBlockTexture(((BlockEcoOre) block).underlay);
		renderer.renderStandardBlock(Blocks.stone, x, y, z);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(0xFFFFFF);
		int red = 0, green = 0, blue = 0;
		int off = 0;
		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
			off = new Random().nextInt(50);
			tessellator.setColorRGBA(255 - off, 0, 0, 100);
			red = 255 - off;
			break;
		case 1:
			off = new Random().nextInt(50);
			tessellator.setColorRGBA(0, 255 - off, 0, 100);
			green = 255 - off;
			break;
		case 2:
			off = new Random().nextInt(50);
			tessellator.setColorRGBA(0, 0, 255 - off, 100);
			blue = 255 - off;
			break;
		case 3:
			off = new Random().nextInt(50);
			tessellator.setColorRGBA(255 - off, 246 - off, 0, 100);
			red = 255 - off;
			green = 246 - off;
			break;
		case 4:
			off = new Random().nextInt(50);
			tessellator.setColorRGBA(255 - off, 255 - off, 255 - off, 100);
			red = 255 - off;
			green = 255 - off;
			blue = 255 - off;
			break;
		case 5:
			off = new Random().nextInt(15);
			tessellator.setColorRGBA(119 + off, 0 + off, 188 + off, 100);
			red = 119 + off;
			green = 0 + off;
			blue = 188 + off;
			break;

		default:
			red = 0;
			green = 0;
			blue = 0;
		}
		renderer.setOverrideBlockTexture(((BlockEcoOre) block).overlay);
		renderer.renderStandardBlockWithColorMultiplier(Blocks.stone, x, y, z, (float) red, (float) green, (float) blue);
		renderer.clearOverrideBlockTexture();
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return AstreaInfinitum.oreID;
	}

	public void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}
}
