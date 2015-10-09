package astreaInfinitum.client.render;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import astreaInfinitum.client.render.model.EcoVent;
import astreaInfinitum.tileEntities.eco.TileEntityEcoVent;

@SuppressWarnings("unused")
public class RenderEcoVent extends TileEntitySpecialRenderer {
	private EcoVent model = new EcoVent();
	private Random random = new Random();
	private RenderBlocks renderBlock = new RenderBlocks();
	private Minecraft mc = Minecraft.getMinecraft();
	private final float size = 0.0625f;
	private EntityItem item = null;

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_) {
		if (tile instanceof TileEntityEcoVent)
			render((TileEntityEcoVent) tile, x, y, z);
	}

	public void render(TileEntityEcoVent tile, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float) x + 0.5f, (float) y + -1.5f, (float) z + 0.5f);
		GL11.glRotatef(180f, 1f, 0f, 0f);
		mc.renderEngine.bindTexture(new ResourceLocation(ModProps.modid + ":textures/models/ecoVent.png"));
		model.render(size);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}