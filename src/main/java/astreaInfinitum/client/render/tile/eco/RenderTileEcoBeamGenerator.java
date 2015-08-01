package astreaInfinitum.client.render.tile.eco;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import io.netty.util.internal.SystemPropertyUtil;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;
import astreaInfinitum.client.render.model.EcoBeamGenerator;
import astreaInfinitum.client.render.model.ModelPedestal;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;
import astreaInfinitum.utils.ClientHandler;
import astreaInfinitum.utils.RenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

public class RenderTileEcoBeamGenerator extends TileEntitySpecialRenderer {
	private EcoBeamGenerator model = new EcoBeamGenerator();
	private Random random = new Random();
	private RenderBlocks renderBlock = new RenderBlocks();
	private Minecraft mc = Minecraft.getMinecraft();
	private final float size = 0.0625f;
	private float angle = 0;

	private void renderTileAt(TileEntityEcoBeamGenerator tile, double x, double y, double z, float partialTickTime) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.50f, (float) z + 0.5f);
		GL11.glRotatef(180f, 1f, 0f, 0f);
		mc.renderEngine.bindTexture(new ResourceLocation(ModProps.modid + ":textures/models/ecoBeamGenerator.png"));
		model.render(size);
		GL11.glPopMatrix();
		glPushMatrix();
		glTranslated(x, y, z);

		glPushAttrib(GL_ALL_ATTRIB_BITS);

		setupGlTranslucent();

		glTranslatef(0.5f, 0.5f, 0.5f);

		Tessellator tessellator = Tessellator.instance;

		float rot = ((float) tile.getWorldObj().getTotalWorldTime() * 20);

		renderAllTranslucent(tile, tessellator, rot, tile.type);

		// glRotatef(180, 1, 0, 0);

		// renderAllTranslucent(tessellator, rot);

		glPopAttrib();

		glPopMatrix();

	}

	private void setupGlTranslucent() {
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_ALPHA_TEST);
		glDisable(GL_CULL_FACE);
		glDepthMask(false);

		RenderHelper.disableStandardItemLighting();

		OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
	}

	private void renderAllTranslucent(TileEntityEcoBeamGenerator tile, Tessellator tessellator, float rot, int dir) {
		glPushMatrix();
		glTranslatef(0f, 0f, 0f);
		switch (dir) {

		case 0:
			glTranslatef(0.45f, 1f, 0.45f);
			break;
		case 1:
			glTranslatef(-0.45f, 1f, 0.45f);
			break;
		case 2:
			glTranslatef(-0.45f, 1f, -0.45f);
			break;
		case 3:
			glTranslatef(0.45f, 1f, -0.45f);
			break;

		}

		glRotatef(45, 0, 1, 0);
		glRotatef(45, 1, 0, 0);

		// for (int i = 0; i < 1; i++) {
		glPushMatrix();
		glRotatef(90 * dir, 0, -1, 1);
		glPushMatrix();
		glRotatef(rot, 0, 1, 0);
		for (int j = 0; j < 4; j++) {
			drawPowerTranslucent(tile, tessellator, dir);
			glRotatef(-90, 0, 1f, 0);
		}
		glPopMatrix();
		// }
		glPopMatrix();
		glPopMatrix();
	}

	private void drawPowerTranslucent(TileEntityEcoBeamGenerator tile, Tessellator tessellator, int type) {
		double width = 0.01875;
		double startPt = -0.785;
		double endPt = 4.5;
		// int blockOffset = 1;
		// while (blockOffset < 100 && tile.getWorldObj().getBlock(tile.xCoord +
		// blockOffset, tile.yCoord, tile.zCoord).isAir(tile.getWorldObj(),
		// tile.xCoord + blockOffset, tile.yCoord, tile.zCoord)) {
		// endPt++;
		//
		// blockOffset++;
		// }
		tessellator.startDrawingQuads();
		switch (type) {
		case 0:
			tessellator.setColorRGBA(255 - new Random().nextInt(15), 0, 0, 100);
			break;
		case 1:
			tessellator.setColorRGBA(0, 255 - new Random().nextInt(15), 0, 100);
			break;
		case 2:
			tessellator.setColorRGBA(0, 0, 255 - new Random().nextInt(15), 100);
			break;
		case 3:
			tessellator.setColorRGBA(255 - new Random().nextInt(15), 246 - new Random().nextInt(15), 0, 100);
			break;
		}
		tessellator.addVertex(-width, startPt, -width);
		tessellator.addVertex(width, startPt, -width);
		tessellator.addVertex(width, endPt, -width);
		tessellator.addVertex(-width, endPt, -width);
		tessellator.draw();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		if (tile instanceof TileEntityEcoBeamGenerator)
			renderTileAt((TileEntityEcoBeamGenerator) tile, x, y, z, partialTickTime);
	}
}