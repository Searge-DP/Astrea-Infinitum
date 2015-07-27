package astreaInfinitum.client.render.tile.eco;

import static org.lwjgl.opengl.GL11.GL_ALL_ATTRIB_BITS;
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
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.eco.TileEntityEcoInfuser;

public class RenderTileEcoInfuser extends TileEntitySpecialRenderer {

	private void renderTileAt(TileEntityEcoInfuser tile, double x, double y, double z, float partialTickTime) {
		GL11.glPushMatrix();
		glTranslated(x, y, z);

		glPushAttrib(GL_ALL_ATTRIB_BITS);

		setupGlTranslucent();

		glTranslatef(0.5f, 0.5f, 0.5f);

		Tessellator tessellator = Tessellator.instance;

		float rot = ((float) tile.getWorldObj().getTotalWorldTime() * 20);

		renderAllTranslucent(tile, tessellator, rot);

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

		OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
	}

	private void renderAllTranslucent(TileEntityEcoInfuser tile, Tessellator tessellator, float rot) {
		glPushMatrix();
		glTranslatef(0f, 0f, 0f);

		glRotatef(rot, 0, 1, 0);

		glPushMatrix();
		for (int j = 0; j < 4; j++) {
			drawPowerTranslucent(tile, tessellator);
			glRotatef(-90, 0, 1f, 0);
		}
		glPopMatrix();
		glPopMatrix();
	}

	private void drawPowerTranslucent(TileEntityEcoInfuser tile, Tessellator tessellator) {
		double width = 0.01875;
		double startPt = 0;//-0.785;
		double endPt = -3.5;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA(255 - new Random().nextInt(15), 255 - new Random().nextInt(15), 255 - new Random().nextInt(15), 100);
		tessellator.addVertex(-width, startPt, -width);
		tessellator.addVertex(width, startPt, -width);
		tessellator.addVertex(width, endPt, -width);
		tessellator.addVertex(-width, endPt, -width);
		tessellator.draw();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		if (tile instanceof TileEntityEcoInfuser)
			renderTileAt((TileEntityEcoInfuser) tile, x, y, z, partialTickTime);
	}
}