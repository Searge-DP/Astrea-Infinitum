package astreaInfinitum.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.client.gui.GuiBookBasic;
import astreaInfinitum.client.gui.GuiKnowledgeTablet;
import astreaInfinitum.client.render.RenderEcoAltarBlock;
import astreaInfinitum.client.render.RenderPedestal;
import astreaInfinitum.client.render.RenderSpell;
import astreaInfinitum.client.render.block.RenderEcoOre;
import astreaInfinitum.client.render.items.RenderItemPedestal;
import astreaInfinitum.client.render.items.RenderItemWand;
import astreaInfinitum.client.render.tile.eco.RenderTileEcoBeamGenerator;
import astreaInfinitum.client.render.tile.eco.RenderTileEcoInfuser;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.tileEntities.TileEntityPedestal;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;
import astreaInfinitum.tileEntities.eco.TileEntityEcoInfuser;
import astreaInfinitum.utils.ClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void readBookBasic() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiBookBasic());
	}

	@Override
	public void readTablet() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiKnowledgeTablet());
	}

	@Override
	public void renderSpell() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySpell.class, new RenderSpell());
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}

	@Override
	public void registerClientHandler() {
		MinecraftForge.EVENT_BUS.register(new ClientHandler());
	}

	@Override
	public void registerRenderers() {
		ClientRegistry.registerTileEntity(TileEntityPedestal.class, "pedestalRender", new RenderPedestal());
		ClientRegistry.registerTileEntity(TileEntityEcoBeamGenerator.class, "ecoBeamGeneratorRender", new RenderTileEcoBeamGenerator());
		ClientRegistry.registerTileEntity(TileEntityEcoInfuser.class, "ecoInfuserRender", new RenderTileEcoInfuser());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AIBlocks.pedestal), new RenderItemPedestal());
		AstreaInfinitum.ctmID = RenderingRegistry.getNextAvailableRenderId();
		AstreaInfinitum.oreID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new RenderEcoAltarBlock());
		MinecraftForgeClient.registerItemRenderer(AIItems.wand, new RenderItemWand());

		RenderingRegistry.registerBlockHandler(new RenderEcoOre());
	}
}
