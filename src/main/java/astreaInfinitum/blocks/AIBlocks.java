package astreaInfinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import astreaInfinitum.ModProps;
import astreaInfinitum.blocks.dusts.BlockArcaneDust;
import astreaInfinitum.blocks.dusts.BlockEcoDust;
import astreaInfinitum.blocks.eco.BlockEcoBeamGenerator;
import astreaInfinitum.blocks.eco.BlockEcoBlock;
import astreaInfinitum.blocks.eco.BlockEcoInfuser;
import astreaInfinitum.blocks.eco.BlockEcoVent;
import astreaInfinitum.blocks.eco.ItemBlockEcoBlock;
import astreaInfinitum.blocks.eco.world.BlockEcoOre;
import astreaInfinitum.blocks.eco.world.ItemBlockEcoOre;
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

	public static Block ecoRitualBlock = new BlockEcoAltarWhite();

	public static Block ecoBeamGenerator = new BlockEcoBeamGenerator();
	public static Block ecoInfuser = new BlockEcoInfuser();
	public static Block ecoOre = new BlockEcoOre();

	public static Block ecoDust = new BlockEcoDust();
	public static Block arcaneDust = new BlockArcaneDust();

	public static Block ecoBlock = new BlockEcoBlock();
	public static Block ecoVent= new BlockEcoVent();
	

	private static void registerBlocks() {
		registerBlock(ecoAltarBlock, "ecoAltarBlock", "ecoAltarBlock");
		registerBlock(ecoAltar, "ecoAltar", "ecoAltar");
		registerBlock(pedestal, "pedestal", "pedestal", "ecoAltarBlock");
		registerBlock(ecoDust, "ecoDust", "ecoDustBlock", "dust", null);
		registerBlock(arcaneDust, "arcaneDust", "arcaneDustBlock", "dust", null);

		registerBlock(ecoRitualBlock, "ecoAltarBlockWhite", "ecoRitualBlock");
		registerBlock(ecoBeamGenerator, "ecoBeamGenerator", "ecoBeamGenerator");
		registerBlock(ecoInfuser, "ecoInfuser", "ecoInfuser");
		registerItemBlockNoTexture(ecoOre, "ecoOre", "ecoOre", ItemBlockEcoOre.class);
		registerItemBlockNoTexture(ecoBlock, "ecoBlock", "ecoBlock", ItemBlockEcoBlock.class);
		registerBlock(ecoVent, "ecoVent", "ecoVent");

	}

	private static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedestal");
		GameRegistry.registerTileEntity(TileEntityEcoAltar.class, "ecoAltar");

	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(AIItems.tab);
		GameRegistry.registerBlock(block, key);
	}

	@SuppressWarnings("unused")
	private static void registerBlockNoTexture(Block block, String name, String key) {
		block.setBlockName(name).setCreativeTab(AIItems.tab);
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

	@SuppressWarnings("unused")
	private static void registerBlock(Block block, String name, String key, CreativeTabs tab) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerBlock(block, key);
	}

	private static void registerItemBlockNoTexture(Block block, String name, String key, Class<? extends ItemBlock> clazz) {
		block.setBlockName(name).setCreativeTab(AIItems.tab);
		GameRegistry.registerBlock(block, clazz, key);
	}
}
