package astreaInfinitum;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import astreaInfinitum.api.EnumEco;
import astreaInfinitum.api.recipes.RecipeEcoAltar;
import astreaInfinitum.api.recipes.RecipeRegistry;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.client.render.RenderSpell;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.handlers.RecipeHandler;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.proxy.ClientProxy;
import astreaInfinitum.proxy.CommonProxy;
import astreaInfinitum.utils.Lang;
import astreaInfinitum.utils.PlayerTickHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = ModProps.modid, name = ModProps.name, version = ModProps.version)
public class AstreaInfinitum {

	public static int ctmID;

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
		proxy.registerClientHandler();
		proxy.registerRenderers();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RecipeHandler.postInit();
	}

}
