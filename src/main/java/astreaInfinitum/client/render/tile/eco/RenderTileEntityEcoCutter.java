package astreaInfinitum.client.render.tile.eco;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import astreaInfinitum.items.AIItems;
import fluxedCore.util.RenderUtils;

public class RenderTileEntityEcoCutter extends TileEntitySpecialRenderer {
	public RenderBlocks renderBlocks = RenderBlocks.getInstance();


	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glDisable(GL11.GL_LIGHTING);
		float red = 0.8f;
		float green = 0.8f;
		float blue = 0.8f;
		float lineWidth = 3;
		
	
		RenderUtils.drawLine(x, y, z, x, y + 1, z, red, green, blue, lineWidth);
		RenderUtils.drawLine(x + 1, y, z, x + 1, y + 1, z, red, green, blue, lineWidth);
		RenderUtils.drawLine(x, y, z + 1, x, y + 1, z + 1, red, green, blue, lineWidth);
		RenderUtils.drawLine(x + 1, y, z + 1, x + 1, y + 1, z + 1, red, green, blue, lineWidth);

		RenderUtils.drawLine(x, y, z, x + 1, y, z, red, green, blue, lineWidth);
		RenderUtils.drawLine(x + 1, y, z, x + 1, y, z + 1, red, green, blue, lineWidth);
		RenderUtils.drawLine(x, y, z + 1, x, y, z, red, green, blue, lineWidth);
		RenderUtils.drawLine(x, y, z + 1, x + 1, y, z + 1, red, green, blue, lineWidth);

		RenderUtils.drawLine(x, y + 1, z, x + 1, y + 1, z, red, green, blue, lineWidth);
		RenderUtils.drawLine(x + 1, y + 1, z, x + 1, y + 1, z + 1, red, green, blue, lineWidth);
		RenderUtils.drawLine(x, y + 1, z + 1, x, y + 1, z, red, green, blue, lineWidth);
		RenderUtils.drawLine(x, y + 1, z + 1, x + 1, y + 1, z + 1, red, green, blue, lineWidth);
		
		GL11.glTranslated(x, y + 1, z);
		ResourceLocation res = new ResourceLocation(ModProps.modid + ":textures/items/runes/basicRuneStone.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(res);
		IIcon icon = AIItems.rune.getIconFromDamage(0);
		GL11.glRotated(90, 1, 0, 0);
		Tessellator tess = Tessellator.instance;
		ItemRenderer.renderItemIn2D(tess, 16, 16, 15, 15, icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}

}
