package astreaInfinitum.utils;

import java.util.Random;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

public class RenderingUtils {

	public static void render3DItem(EntityItem item, float angle) {

		GL11.glPushMatrix();
		GL11.glDepthMask(true);

		item.hoverStart = 0.0F;
		GL11.glRotatef(180, 0, 0, 180);
		GL11.glEnable(GL11.GL_LIGHTING);
		RenderManager.instance.func_147939_a(item, 0, -0.3, 0, 0, angle, true);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
