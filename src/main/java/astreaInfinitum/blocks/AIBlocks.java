package astreaInfinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumEco;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.tileEntities.TileEntityEcoAltar;
import astreaInfinitum.tileEntities.TileEntityPedestal;
import cpw.mods.fml.common.registry.GameRegistry;

public class AIBlocks {

	public static void init() {
		registerBlocks();
		registerTileEntities();
	}

	public static Block pedestal = new BlockPedestal();
	public static Block ecoAltarBlock = new BlockEcoAltarBlock();
	public static Block ecoAltar = new BlockEcoAltar();
	public static Block ecoExtractor = new BlockEcoExtractor();
	public static Block ecoDustLight = new BlockEcoDust(EnumEco.light, 0x0B72B0);
	public static Block ecoDustDark = new BlockEcoDust(EnumEco.dark, 0xC20031);

	public static Block ecoRitualBlock = new BlockEcoAltarWhite();

	private static void registerBlocks() {
		registerBlock(ecoAltarBlock, "ecoAltarBlock", "ecoAltarBlock");
		registerBlock(ecoAltar, "ecoAltar", "ecoAltar");
		registerBlock(pedestal, "pedestal", "pedestal", "ecoAltarBlock");
		registerBlock(ecoExtractor, "extractor", "extractor");
		registerBlock(ecoDustLight, "ecoDustLight", "ecoDustLight", "ecoDust", null);
		registerBlock(ecoDustDark, "ecoDustDark", "ecoDustDark", "ecoDust", null);

		registerBlock(ecoRitualBlock, "ecoAltarBlockWhite", "ecoRitualBlock");

	}

	private static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedestal");
		GameRegistry.registerTileEntity(TileEntityEcoAltar.class, "ecoAltar");

	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(AIItems.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, String texture) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + texture).setCreativeTab(AIItems.tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, String texture, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + texture).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerBlock(Block block, String name, String key, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}
}
