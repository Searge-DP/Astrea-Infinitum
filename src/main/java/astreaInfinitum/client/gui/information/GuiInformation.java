package astreaInfinitum.client.gui.information;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import astreaInfinitum.ModProps;
import astreaInfinitum.client.gui.GuiButtonItemStack;
import fluxedCore.client.gui.GuiObject;
import fluxedCore.client.gui.objects.GuiObjectItemButton;
import fluxedCore.util.RenderUtils;

public class GuiInformation extends GuiScreen {
	int guiWidth = 256;
	int guiHeight = 256;
	int left, top;
	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/GUIInformation.png");

	private List<GuiObject> objects = new ArrayList<GuiObject>();

	public void drawScreen(int mouseX, int mouseY, float p_73863_3_) {

		if (mc != null && mc.renderEngine != null) {

			mc.renderEngine.bindTexture(texture);
		}
		drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);
		super.drawScreen(mouseX, mouseY, p_73863_3_);

		for (GuiObject o : objects) {
			o.renderBackground(this, mouseX, mouseY);
		}
		for (GuiObject o : objects) {
			o.renderForeground(this, mouseX, mouseY);
		}

	}

	@Override
	public void mouseClickMove(int mx, int my, int p_146273_3_, long p_146273_4_) {
	}

	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		int mx = Mouse.getX();
		int my = Mouse.getY()-(width/2);
		RenderUtils.drawSquare(mx, my, 160, 0, 0.8f, 0.8f, 3);
		RenderUtils.drawRect(mx, my, mx+16, my+16, 0, 0.8f, 0.8f, 5);
		for (GuiObject o : objects) {
			if (o.collidedWithMouse(mx, my)) {
				o.onCollided(this, mx, my);
			}
		}
	}

	@Override
	public void mouseClicked(int mx, int my, int button) {
		super.mouseClicked(mx, my, button);
		if (button == 0)
			for (GuiObject o : objects) {
				if (o.collidedWithMouse(mx, my)) {
					o.onClick(this, mx, my);
				}
			}
	}

	@Override
	public void initGui() {
		objects.clear();
		super.initGui();
		int count = 0;
		this.left = (this.width / 2) - (guiWidth / 2);
		this.top = (this.height / 2) - (guiHeight / 2);

		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 12; y++) {
				objects.add(new GuiObjectItemButton(count, this, 20, 20, left + 13 + (19 * x), top + 13 + (19 * y), new ItemStack(Item.getItemById(new Random().nextInt(20) + 1))));
			}
		}
	}
}
