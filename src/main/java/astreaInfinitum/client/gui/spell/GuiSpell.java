package astreaInfinitum.client.gui.spell;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiSpell extends GuiContainer {
	public GuiSpell(InventoryPlayer player) {
		super(new ContainerSpell(player));
	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/spell.png");
	int guiWidth = 186;
	int guiHeight = 238;
	int left, top;

	public void drawScreen(int mouseX, int mouseY, float p_73863_3_) {
		super.drawScreen(mouseX, mouseY, p_73863_3_);

	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	public void initGui() {
		super.initGui();
		this.left = (this.width / 2) - (guiWidth / 2);
		this.top = (this.height / 2) - (guiHeight / 2);
//		buttonList.add(new GuiButtonItem(0, left + 33, top - 13, 18, 18, "Hello!", new ItemStack(FCItems.universalSeed)));
		
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		if (mc != null && mc.renderEngine != null) {

			mc.renderEngine.bindTexture(texture);
		}
		drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);
		// fontRendererObj.drawString("hello", left + 50, top + 20, 0xFF55FF);
		// itemRender.renderItemAndEffectIntoGUI(fontRendererObj,
		// mc.renderEngine, new ItemStack(FCItems.universalSeed), left + 45 -
		// 12, top + 9 - 16 - 6);
		// buttonList.add(new GuiButton(0,left+50,top+50,20,20,"hi"));

	}

}
