package astreaInfinitum.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiButtonItemStack extends GuiButton {

	private ItemStack stack;

	public GuiButtonItemStack(int index, int x, int y, ItemStack stack) {
		super(index, x, y, 20, 20, "");
		this.stack = stack;
	}

	@Override
	public void drawButton(Minecraft mc, int x, int y) {

		drawLine(this.xPosition, this.yPosition, this.xPosition + width, this.yPosition, 0f, 0.5f, 0.8f, 2);
		drawLine(this.xPosition + width, this.yPosition, this.xPosition + width, this.yPosition + height, 0f, 0.5f, 0.8f, 2);
		drawLine(this.xPosition, this.yPosition, this.xPosition, this.yPosition + height, 0f, 0.5f, 0.8f, 2);
		drawLine(this.xPosition, this.yPosition + height, this.xPosition + width, this.yPosition + height, 0f, 0.5f, 0.8f, 2);
		RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, stack, this.xPosition + 2, this.yPosition + 2);
		GL11.glColor4d(1, 1, 1, 1);

	}

	public void drawLine(double x, double y, double x2, double y2, float red, float green, float blue, float lineWidth) {
		int count = FMLClientHandler.instance().getClient().thePlayer.ticksExisted;
		float alpha = 0.3F + MathHelper.sin((float) (count + x)) * 0.3F + 0.3F;

		Tessellator var12 = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glLineWidth(0.3f);
		GL11.glDisable(3553);

		GL11.glBlendFunc(770, 1);
		var12.startDrawing(3);

		var12.setColorRGBA_F(0.0F, 0.6F, 0.8F, alpha);
		var12.addVertex(x, y, 0.0D);
		var12.addVertex(x2, y2, 0.0D);

		var12.draw();
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(32826);
		GL11.glEnable(3553);
		GL11.glPopMatrix();
	}
}
