package astreaInfinitum.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.api.utils.TileEntityClientServer;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.client.gui.GuiKnowledgeTablet;
import astreaInfinitum.client.gui.information.GuiInformation;
import astreaInfinitum.client.render.RenderCarving;
import astreaInfinitum.client.render.RenderEcoAltarBlock;
import astreaInfinitum.client.render.RenderEcoVent;
import astreaInfinitum.client.render.RenderPedestal;
import astreaInfinitum.client.render.RenderSpell;
import astreaInfinitum.client.render.items.RenderItemPedestal;
import astreaInfinitum.client.render.items.RenderItemRuneCrafter;
import astreaInfinitum.client.render.items.RenderItemWand;
import astreaInfinitum.client.render.tile.eco.RenderTileEcoBeamGenerator;
import astreaInfinitum.client.render.tile.eco.RenderTileEcoInfuser;
import astreaInfinitum.client.render.tile.eco.RenderTileEntityEcoCutter;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.tileEntities.TileEntityPedestal;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;
import astreaInfinitum.tileEntities.eco.TileEntityEcoCutter;
import astreaInfinitum.tileEntities.eco.TileEntityEcoInfuser;
import astreaInfinitum.tileEntities.eco.TileEntityEcoVent;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
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
		Minecraft.getMinecraft().displayGuiScreen(new GuiInformation());
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
		ClientRegistry.registerTileEntity(TileEntityEcoVent.class, "ecoVentRender", new RenderEcoVent());
		ClientRegistry.registerTileEntity(TileEntityRuneCarver.class, "runeCarverRender", new RenderCarving());
		ClientRegistry.registerTileEntity(TileEntityEcoCutter.class, "ecoCutterRender", new RenderTileEntityEcoCutter());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AIBlocks.pedestal), new RenderItemPedestal());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AIBlocks.runeCrafter), new RenderItemRuneCrafter());

		AstreaInfinitum.ctmID = RenderingRegistry.getNextAvailableRenderId();
		AstreaInfinitum.oreID = RenderingRegistry.getNextAvailableRenderId();
		AstreaInfinitum.carvingID = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new RenderEcoAltarBlock());
		MinecraftForgeClient.registerItemRenderer(AIItems.wand, new RenderItemWand());

	}

	@Override
	public void renderBeam() {

	}

	@Override
	public void updateTile(TileEntityClientServer tile) {
		tile.updateClient();
	}

}
