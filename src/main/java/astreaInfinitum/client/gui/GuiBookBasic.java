package astreaInfinitum.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;

public class GuiBookBasic extends GuiScreen {
	int guiWidth = 146;
	int guiHeight = 180;
	int left, top;
	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/bookBasic.png");

	public void drawScreen(int mouseX, int mouseY, float p_73863_3_) {

		if (mc != null && mc.renderEngine != null) {

			mc.renderEngine.bindTexture(texture);
		}
		drawTexturedModalRect(left, top, 20, 1, guiWidth, guiHeight);
		if (fontRendererObj != null) {
			fontRendererObj.drawSplitString(AstreaInfinitum.lang.localize("gui.book.basic.text", true), left + 14, top + 8, 120, 0x493D26);
		}
	}

	@Override
	public void initGui() {
		super.initGui();

		this.left = (this.width / 2) - (guiWidth / 2);
		this.top = (this.height / 2) - (guiHeight / 2);

	}
}
