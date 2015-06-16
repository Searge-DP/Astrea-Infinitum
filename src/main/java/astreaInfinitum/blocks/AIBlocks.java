package astreaInfinitum.blocks;

import net.minecraft.block.Block;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.tileEntities.TileEntityPedestal;
import cpw.mods.fml.common.registry.GameRegistry;

public class AIBlocks {

	public static void init() {
		registerBlocks();
		registerTileEntities();
	}

	public static Block pedestal = new BlockPedestal();
	public static Block manaAltarBlock = new BlockManaAltarBlock();
	public static Block manaAltar = new BlockManaAltar();
	public static Block manaExtractor = new BlockManaExtractor();
	public static Block manaDustLight = new BlockManaDust(EnumMana.light, 0x0B72B0);
	public static Block manaDustDark = new BlockManaDust(EnumMana.dark, 0xC20031);
	

	private static void registerBlocks() {
		registerBlock(manaAltarBlock, "manaAltarBlock", "manaAltarBlock");
		registerBlock(manaAltar, "manaAltar", "manaAltar");
		registerBlock(pedestal, "pedestal", "pedestal");
		registerBlock(manaExtractor, "extractor", "extractor");
		registerBlock(manaDustLight, "manaDustLight", "manaDustLight", "manaDust");
		registerBlock(manaDustDark, "manaDustDark", "manaDustDark", "manaDust");
		
	}

	private static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedestal");
	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + key);
		GameRegistry.registerBlock(block, key);
	}
	private static void registerBlock(Block block, String name, String key, String texture) {
		block.setBlockName(name).setBlockTextureName(ModProps.modid + ":" + texture);
		GameRegistry.registerBlock(block, key);
	}
}
