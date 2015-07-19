package astreaInfinitum.client.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import astreaInfinitum.client.render.model.ModelWand;

public class RenderItemWand implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if (item != null) {
			return type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.INVENTORY || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == ItemRenderType.INVENTORY) {
			RenderBlocks renderBlocks = (RenderBlocks) data[0];
			renderToInventory(item, renderBlocks);
		} else if (type == ItemRenderType.EQUIPPED) {
			renderEquipped(item, (RenderBlocks) data[0]);
		} else if (type == ItemRenderType.ENTITY) {
			renderEntity(item, (RenderBlocks) data[0]);
		} else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			renderFirst(item, (RenderBlocks) data[0]);
		}
	}

	private void renderEntity(ItemStack item, RenderBlocks renderBlocks) {
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.2f, 0.5f);
		renderToInventory(item, renderBlocks);
		GL11.glPopMatrix();
	}

	private void renderEquipped(ItemStack item, RenderBlocks renderBlocks) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.6F, 0.7F, 0.6F);
		GL11.glRotated(45, 0, 1, 0);
		renderToInventory(item, renderBlocks);
		GL11.glPopMatrix();
	}

	private void renderFirst(ItemStack item, RenderBlocks renderBlocks) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.6F, 0.7F, 0.6F);
		GL11.glRotated(-45, 0, 1, 0);
		renderToInventory(item, renderBlocks);
		GL11.glPopMatrix();
	}

	private void renderToInventory(ItemStack item, RenderBlocks renderBlocks) {
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glTranslatef(0, -0.65f, 0.5f);
		GL11.glRotatef(180, 1, 0, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModProps.modid + ":textures/models/wand.png"));
		ModelWand ped = new ModelWand();
		ped.render(0.125f);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
	}

}
