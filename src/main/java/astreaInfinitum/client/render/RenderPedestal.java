package astreaInfinitum.client.render;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.client.render.model.ModelPedestal;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.tileEntities.TileEntityPedestal;
import astreaInfinitum.utils.RenderingUtils;

public class RenderPedestal extends TileEntitySpecialRenderer {
	private ModelPedestal model = new ModelPedestal();
	private Random random = new Random();
	private RenderBlocks renderBlock = new RenderBlocks();
	private Minecraft mc = Minecraft.getMinecraft();
	private final float size = 0.0625f;
	private float angle = 0;
	private EntityItem item = null;

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_) {
		if (tile instanceof TileEntityPedestal)
			render((TileEntityPedestal) tile, x, y, z);
	}

	public void render(TileEntityPedestal tile, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.50f, (float) z + 0.5f);
		GL11.glRotatef(180f, 1f, 0f, 0f);
		mc.renderEngine.bindTexture(new ResourceLocation(ModProps.modid + ":textures/models/pedestal.png"));
		model.render(size);
		if (tile.getStackInSlot(0) != null) {
			RenderingUtils.render3DItem(new EntityItem(tile.getWorldObj(), x + 0.5, y + 1.5, z + 0.5, tile.getStackInSlot(0)), tile.angle);
			angle++;

		}
		GL11.glPopMatrix();
	}
}