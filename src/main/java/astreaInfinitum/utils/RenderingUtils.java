package astreaInfinitum.utils;

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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderingUtils {

	public static void render3DItem(EntityItem item, float angle) {

		GL11.glPushMatrix();
		GL11.glDepthMask(true);
		item.hoverStart = 0.0F;
		GL11.glRotated(180, 0, 0, 1);
		GL11.glPushMatrix();
		GL11.glRotatef(angle, 0, 1, 0);
		GL11.glEnable(GL11.GL_LIGHTING);
		RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		if (!Minecraft.isFancyGraphicsEnabled()) {
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		}
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	private static void setupGlTranslucent() {
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_ALPHA_TEST);
		glDisable(GL_CULL_FACE);
		glDepthMask(false);

		RenderHelper.disableStandardItemLighting();

		OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
	}

	private static void renderAllTranslucent(Tessellator tessellator, float rot) {
		glPushMatrix();
		glTranslatef(0, 0.14f, 0);
		glRotatef(45, 0, 1, 0);
		glRotatef(45, 1, 0, 0);

		// for (int i = 0; i < 4; i++) {
		glRotatef(45, 0, -1, 1);
		glPushMatrix();
		glRotatef(rot, 0, 1, 0);
		for (int j = 0; j < 4; j++) {
			drawPowerTranslucent(tessellator);
			glRotatef(-90, 0, 1, 0);
		}
		glPopMatrix();
		// }

		glPopMatrix();
	}

	private static void drawPowerTranslucent(Tessellator tessellator) {
		double width = 0.08875;
		double startPt = -0.785;
		double endPt = -0.4;

		tessellator.startDrawingQuads();
		tessellator.setColorRGBA(40, 20, 255, 100);
		tessellator.addVertex(-width, startPt, -width);
		tessellator.addVertex(width, startPt, -width);
		tessellator.addVertex(width, endPt + 5, -width);
		tessellator.addVertex(-width, endPt + 5, -width);
		tessellator.draw();
	}
	
	public static void renderBeamAt(Entity entity, double startX, double startY, double startZ, double endX,double endY,double endZ, float partialTickTime) {
		glPushMatrix();
		glTranslated(endX, endY, endZ);

		glPushAttrib(GL_ALL_ATTRIB_BITS);

		setupGlTranslucent();

		glTranslatef(0.5f, 0.5f, 0.5f);

		Tessellator tessellator = Tessellator.instance;

		float rot = partialTickTime;
		
//		glRotatef(180, 1, 0, 0);
		renderAllTranslucent(tessellator, rot);



		glPopAttrib();

		glPopMatrix();
	}
	
	
	 public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3)
	  {
	    float var4 = 1.0F;
	    float var5 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * var4;
	    
	    float var6 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * var4;
	    
	    double var7 = entity.prevPosX + (entity.posX - entity.prevPosX) * var4;
	    
	    double var9 = entity.prevPosY + (entity.posY - entity.prevPosY) * var4 + 1.62D - entity.yOffset;
	    
	    double var11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * var4;
	    
	    Vec3 var13 = Vec3.createVectorHelper(var7, var9, var11);
	    float var14 = MathHelper.cos(-var6 * 0.01745329F - 3.141593F);
	    float var15 = MathHelper.sin(-var6 * 0.01745329F - 3.141593F);
	    float var16 = -MathHelper.cos(-var5 * 0.01745329F);
	    float var17 = MathHelper.sin(-var5 * 0.01745329F);
	    float var18 = var15 * var16;
	    float var20 = var14 * var16;
	    double var21 = 10.0D;
	    Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
	    
	    return world.func_147447_a(var13, var23, par3, !par3, false);
	  }
}
