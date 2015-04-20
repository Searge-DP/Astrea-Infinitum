package astreaInfinitum.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;

public class GuiKnowledgeTablet extends GuiScreen {
	int guiWidth = 146;
	int guiHeight = 180;
	int left, top;
	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/tablet.png");

	public void drawScreen(int mouseX, int mouseY, float p_73863_3_) {
		if (mc != null && mc.renderEngine != null) {

			mc.renderEngine.bindTexture(texture);
		}
		if (fontRendererObj != null) {
			drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);
			fontRendererObj.FONT_HEIGHT = 12;
			fontRendererObj.drawSplitString(AstreaInfinitum.lang.localize("gui.knowledge.tablet.text", true), left + 10, top + 10, 130, 0x493D26);
			fontRendererObj.FONT_HEIGHT = 8;
		}
	}

	@Override
	public void initGui() {
		super.initGui();

		this.left = (this.width / 2) - (guiWidth / 2);
		this.top = (this.height / 2) - (guiHeight / 2);

	}
}
