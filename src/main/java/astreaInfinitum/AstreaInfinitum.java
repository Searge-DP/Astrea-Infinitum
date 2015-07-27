package astreaInfinitum;

import net.minecraftforge.common.MinecraftForge;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.client.gui.GuiHandler;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.handlers.RecipeHandler;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.items.runes.ItemRune;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.proxy.CommonProxy;
import astreaInfinitum.utils.ClientHandler;
import astreaInfinitum.utils.Lang;
import astreaInfinitum.utils.PlayerTickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = ModProps.modid, name = ModProps.name, version = ModProps.version)
public class AstreaInfinitum {

	public static int ctmID, oreID;

	@Instance(ModProps.modid)
	public static AstreaInfinitum INSTANCE;

	@SidedProxy(clientSide = "astreaInfinitum.proxy.ClientProxy", serverSide = "astreaInfinitum.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Lang lang = new Lang(ModProps.modid);

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
		PacketHandler.init();
		AIItems.init();
		AIBlocks.init();
		EntityRegistry.registerModEntity(EntitySpell.class, "spell", 0, INSTANCE, 30, 30, true);
		proxy.renderSpell();
		// proxy.registerClientHandler();
		MinecraftForge.EVENT_BUS.register(new ClientHandler());
		proxy.registerRenderers();
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RecipeHandler.postInit();
	}

	
}
