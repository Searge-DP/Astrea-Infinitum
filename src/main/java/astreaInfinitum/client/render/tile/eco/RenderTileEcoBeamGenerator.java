package astreaInfinitum.client.render.tile.eco;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_ZERO;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import astreaInfinitum.client.render.model.ModelAIPylonModel;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;
import astreaInfinitum.tileEntities.eco.TileEntityEcoCutter;
import fluxedCore.util.RenderUtils;

@SuppressWarnings("unused")
public class RenderTileEcoBeamGenerator extends TileEntitySpecialRenderer {
	private ModelAIPylonModel model = new ModelAIPylonModel();

	private Random random = new Random();
	private RenderBlocks renderBlock = new RenderBlocks();
	private Minecraft mc = Minecraft.getMinecraft();
	private final float size = 0.0625f;
	private float angle = 0;

	private float red = 1;
	private float green = 0;
	private float blue = 0;

	private void renderTileAt(TileEntityEcoBeamGenerator tile, double x, double y, double z, float partialTickTime) {
		updateColours(tile);
		World world = tile.getWorldObj();
		glPushMatrix();
		float lineWidth = 1;
		double loopOff = 0;
		double offset = 0.4;
		RenderUtils.drawLine(x + offset, y + 1.4 + loopOff, z + offset, x + 1 - offset, y + 1.4 + loopOff, z + offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + 1 - offset, y + 1.4 + loopOff, z + offset, x + 1 - offset, y + 1.4 + loopOff, z + 1 - offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + 1 - offset, y + 1.4 + loopOff, z + 1 - offset, x + offset, y + 1.4 + loopOff, z + 1 - offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + offset, y + 1.4 + loopOff, z + 1 - offset, x + offset, y + 1.4 + loopOff, z + offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + offset, y + 1.6 - loopOff, z + offset, x + 1 - offset, y + 1.6 - loopOff, z + offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + 1 - offset, y + 1.6 - loopOff, z + offset, x + 1 - offset, y + 1.6 - loopOff, z + 1 - offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + 1 - offset, y + 1.6 - loopOff, z + 1 - offset, x + offset, y + 1.6 - loopOff, z + 1 - offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + offset, y + 1.6 - loopOff, z + 1 - offset, x + offset, y + 1.6 - loopOff, z + offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + offset, y + 1.4 + loopOff, z + offset, x + offset, y + 1.6 - loopOff, z + offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + 1 - offset, y + 1.4 + loopOff, z + offset, x + 1 - offset, y + 1.6 - loopOff, z + offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + 1 - offset, y + 1.4 + loopOff, z + 1 - offset, x + 1 - offset, y + 1.6 - loopOff, z + 1 - offset, tile.red, tile.green, tile.blue, lineWidth);
		RenderUtils.drawLine(x + offset, y + 1.4 + loopOff, z + 1 - offset, x + offset, y + 1.6 - loopOff, z + 1 - offset, tile.red, tile.green, tile.blue, lineWidth);
		
		RenderUtils.drawLine(x+ 0.5, y + 1.5, z + 0.5, 0.5+x+2, 0.5+y-1, 0.5+z+2, tile.red, tile.green, tile.blue, lineWidth);
		glPopMatrix();
		GL11.glPushMatrix();
	
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.50f, (float) z + 0.5f);
		GL11.glRotatef(180f, 1f, 0f, 0f);
		mc.renderEngine.bindTexture(new ResourceLocation(ModProps.modid + ":textures/models/ai_pylonmodel.png"));
		model.render(size);
		GL11.glPopMatrix();
		

		Tessellator tessellator = Tessellator.instance;
	
}
	public boolean isCutter(World world, int x, int y, int z) {
		if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityEcoCutter) {
			return true;
		}
		return false;
	}

	public void updateColours(TileEntityEcoBeamGenerator tile) {
		float oldRed = tile.red;
		float oldGreen = tile.green;
		float oldBlue = tile.blue;
		if (oldRed == 1 && oldBlue < 1 && oldGreen == 0) {
			tile.blue += 0.02f;
			if (tile.blue > 1) {
				tile.blue = 1;
			}
		} else if (oldBlue == 1 && oldRed > 0 && oldGreen == 0) {
			tile.red -= 0.02f;
			if (tile.red < 0) {
				tile.red = 0;
			}
		} else if (oldBlue == 1 && oldRed == 0 && oldGreen < 1) {
			tile.green += 0.02f;
			if (tile.green > 1) {
				tile.green = 1;
			}
		} else if (oldGreen == 1 && oldRed == 0 && oldBlue > 0) {
			tile.blue -= 0.02f;
			if (tile.blue < 0) {
				tile.blue = 0;
			}
		} else if (oldGreen == 1 && oldBlue == 0 && oldRed < 1) {
			tile.red += 0.02f;
			if (tile.red > 1) {
				tile.red = 1;
			}
		} else if (oldRed == 1 && oldGreen > 0 && oldBlue == 0) {
			tile.green -= 0.02f;
			if (tile.green < 0) {
				tile.green = 0;
			}
		}

	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		if (tile instanceof TileEntityEcoBeamGenerator)
			renderTileAt((TileEntityEcoBeamGenerator) tile, x, y, z, partialTickTime);
	}
}