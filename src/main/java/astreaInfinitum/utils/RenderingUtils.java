package astreaInfinitum.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;

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
		 if (!Minecraft.isFancyGraphicsEnabled())
	      {
	        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	        RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
	      }
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
