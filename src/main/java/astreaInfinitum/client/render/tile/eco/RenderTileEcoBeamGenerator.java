package astreaInfinitum.client.render.tile.eco;

import astreaInfinitum.ModProps;
import astreaInfinitum.client.render.model.EcoBeamGenerator;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;
import astreaInfinitum.utils.Coordinate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

@SuppressWarnings("unused")
public class RenderTileEcoBeamGenerator extends TileEntitySpecialRenderer {
	private final float size = 0.0625f;
	private EcoBeamGenerator model = new EcoBeamGenerator();
	private Random random = new Random();
	private RenderBlocks renderBlock = new RenderBlocks();
	private Minecraft mc = Minecraft.getMinecraft();
	private float angle = 0;

	private static Vector Cross(Vector a, Vector b) {
		float x = a.y * b.z - a.z * b.y;
		float y = a.z * b.x - a.x * b.z;
		float z = a.x * b.y - a.y * b.x;
		return new Vector(x, y, z);
	}

	// private void setupGlTranslucent() {
	// glDisable(GL_TEXTURE_2D);
	// glEnable(GL_BLEND);
	// glShadeModel(GL_SMOOTH);
	// glDisable(GL_ALPHA_TEST);
	// glDisable(GL_CULL_FACE);
	// glDepthMask(false);
	//
	// RenderHelper.disableStandardItemLighting();
	//
	// OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE,
	// GL_ZERO);
	// }

	// private void renderAllTranslucent(TileEntityEcoBeamGenerator tile,
	// Tessellator tessellator, float rot, int dir, float partialTime) {
	// glPushMatrix();
	// glTranslatef(0f, 0f, 0f);
	// switch (dir) {
	//
	// case 0:
	// glTranslatef(0.45f, 1f, 0.45f);
	// break;
	// case 1:
	// glTranslatef(-0.45f, 1f, 0.45f);
	// break;
	// case 2:
	// glTranslatef(-0.45f, 1f, -0.45f);
	// break;
	// case 3:
	// glTranslatef(0.45f, 1f, -0.45f);
	// break;
	//
	// }
	//
	// glRotatef(45, 0, 1, 0);
	// glRotatef(45, 1, 0, 0);
	//
	// // for (int i = 0; i < 1; i++) {
	// glPushMatrix();
	// glRotatef(90 * tile.rotation, 0, -1, 1);
	// glPushMatrix();
	// glRotatef(rot, 0, 1, 0);
	//
	// for (int j = 0; j < 4; j++) {
	// drawPowerTranslucent(tile, tessellator, dir);
	// glRotatef(-90, 0, 1f, 0);
	//
	// }
	//
	// glPopMatrix();
	// // }
	// glPopMatrix();
	// glPopMatrix();
	// }

	// private void drawPowerTranslucent(TileEntityEcoBeamGenerator tile,
	// Tessellator tessellator, int type) {
	// double width = 0.01875;
	// double startPt = -0.785;
	// double endPt = 4.5;
	// // int blockOffset = 1;
	// // while (blockOffset < 100 && tile.getWorldObj().getBlock(tile.xCoord +
	// // blockOffset, tile.yCoord, tile.zCoord).isAir(tile.getWorldObj(),
	// // tile.xCoord + blockOffset, tile.yCoord, tile.zCoord)) {
	// // endPt++;
	// //
	// // blockOffset++;
	// // }
	//
	// tessellator.startDrawingQuads();
	// Color col = new Color(tile.color);
	// tessellator.setColorRGBA(col.getRed() - new Random().nextInt(15),
	// col.getGreen() - new Random().nextInt(15), col.getBlue() - new
	// Random().nextInt(15), 100);
	// mc.renderEngine.bindTexture(new ResourceLocation(ModProps.modid +
	// ":textures/particles/beam.png"));
	// tessellator.addVertexWithUV(-width, startPt, -width, 0, 0);
	// tessellator.addVertexWithUV(width, startPt, -width, 1, 0);
	// tessellator.addVertexWithUV(width, endPt, -width, 1, 1);
	// tessellator.addVertexWithUV(-width, endPt, -width, 0, 1);
	// tessellator.draw();
	// }

	private static Vector Sub(Vector a, Vector b) {
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	private static Vector Add(Vector a, Vector b) {
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	private static Vector Mul(Vector a, float f) {
		return new Vector(a.x * f, a.y * f, a.z * f);
	}

	private void renderTileAt(TileEntityEcoBeamGenerator tile, double x, double y, double z, float partialTickTime) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.50f, (float) z + 0.5f);
		GL11.glRotatef(180f, 1f, 0f, 0f);
		mc.renderEngine.bindTexture(new ResourceLocation(ModProps.modid + ":textures/models/ecoBeamGenerator.png"));
		model.render(size);
		GL11.glPopMatrix();

		glPushMatrix();
		// glTranslated(x, y, z);

		// glPushAttrib(GL_ALL_ATTRIB_BITS);

		// setupGlTranslucent();

		glTranslatef(0.5f, 0.5f, 0.5f);

		Tessellator tessellator = Tessellator.instance;

		float rot = ((float) tile.getWorldObj().getTotalWorldTime() * 20);
		boolean blending = GL11.glIsEnabled(GL11.GL_BLEND);
		// if (tile.shouldSendEco)
		// renderAllTranslucent(tile, tessellator, rot, tile.rotation,
		// partialTickTime);
		Minecraft mc = Minecraft.getMinecraft();
		EntityClientPlayerMP p = mc.thePlayer;
		double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX) * partialTickTime;
		double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY) * partialTickTime;
		double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * partialTickTime;

		// GL11.glEnable(GL11.GL_BLEND);
		// GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

		GL11.glPushMatrix();
		GL11.glTranslated(-doubleX, -doubleY, -doubleZ);

		tessellator.startDrawingQuads();


		Coordinate coord = new Coordinate(tile.xCoord, tile.yCoord, tile.zCoord);
		Coordinate coordNew = new Coordinate(tile.xCoord - 2, tile.yCoord + 3, tile.zCoord - 2);

		// drawLine(coord, coordNew, doubleX, doubleY, doubleZ);
		// glRotatef(180, 1, 0, 0);
		// renderAllTranslucent(tessellator, rot);
		tessellator.draw();
		glPopMatrix();
		if (!blending) {
			GL11.glDisable(GL11.GL_BLEND);
		}
		glPopMatrix();

	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		if (tile instanceof TileEntityEcoBeamGenerator)
			renderTileAt((TileEntityEcoBeamGenerator) tile, x, y, z, partialTickTime);
	}

	private void drawLine(Coordinate c1, Coordinate c2, double playerX, double playerY, double playerZ) {
		// Calculate the start point of the laser
		float mx1 = c1.getX() + .2f;
		float my1 = c1.getY() + .2f + .3f;
		float mz1 = c1.getZ() + .2f;
		Vector S = new Vector(mx1, my1, mz1);

		// Calculate the end point of the laser
		float mx2 = c2.getX() + .5f;
		float my2 = c2.getY() + .5f;
		float mz2 = c2.getZ() + .5f;
		Vector E = new Vector(mx2, my2, mz2);

		// Player position.
		Vector P = new Vector((float) playerX, (float) playerY, (float) playerZ);

		Vector PS = Sub(S, P);
		Vector SE = Sub(E, S);

		Vector normal = Cross(PS, SE);
		normal = normal.normalize();

		Vector half = Mul(normal, .2f);
		Vector p1 = Add(S, half);
		Vector p2 = Sub(S, half);
		Vector p3 = Add(E, half);
		Vector p4 = Sub(E, half);
		drawQuad(Tessellator.instance, p1, p3, p4, p2);
	}

	private void drawQuad(Tessellator tessellator, Vector p1, Vector p2, Vector p3, Vector p4) {
		tessellator.addVertexWithUV(p1.getX(), p1.getY(), p1.getZ(), 0, 0);
		tessellator.addVertexWithUV(p2.getX(), p2.getY(), p2.getZ(), 1, 0);
		tessellator.addVertexWithUV(p3.getX(), p3.getY(), p3.getZ(), 1, 1);
		tessellator.addVertexWithUV(p4.getX(), p4.getY(), p4.getZ(), 0, 1);
	}

	private static class Vector {
		private final float x;
		private final float y;
		private final float z;

		private Vector(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getZ() {
			return z;
		}

		public float norm() {
			return (float) Math.sqrt(x * x + y * y + z * z);
		}

		public Vector normalize() {
			float n = norm();
			return new Vector(x / n, y / n, z / n);
		}
	}

}