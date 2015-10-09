package astreaInfinitum.items;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import astreaInfinitum.AITab;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.runes.EnumRuneFunction;
import astreaInfinitum.api.spell.Spell;
import astreaInfinitum.items.dusts.ItemArcaneDust;
import astreaInfinitum.items.dusts.ItemEcoDust;
import astreaInfinitum.items.eco.ItemEcoOrb;
import astreaInfinitum.items.learning.ItemBookBasic;
import astreaInfinitum.items.lore.ItemLoreBook;
import astreaInfinitum.items.runes.ItemRune;
import astreaInfinitum.items.runes.ItemRuneFunction;
import astreaInfinitum.items.runes.ItemRuneIcon;
import astreaInfinitum.items.runes.ItemRuneType;
import astreaInfinitum.items.spells.ItemSpell;
import astreaInfinitum.items.tablets.ItemKnowledgeTablet;
import astreaInfinitum.items.wands.ItemWand;
import astreaInfinitum.reference.SpellReference;
import cpw.mods.fml.common.registry.GameRegistry;

public class AIItems {

	public static ArrayList<Item> spells = new ArrayList<Item>();

	public static CreativeTabs tab;
	;
	public static Item bookBasic = new ItemBookBasic();
	public static Item tabletKnowledge = new ItemKnowledgeTablet();
	public static Item wand = new ItemWand().setFull3D();
	public static Item rune = new ItemRune();
	public static Item runeIcon = new ItemRuneIcon();
	public static Item runeFunction = new ItemRuneFunction();
	public static Item runeType = new ItemRuneType();
	public static Item ecoOrb = new ItemEcoOrb();
	public static Item arcaneDust = new ItemArcaneDust();
	public static Item ecoDust = new ItemEcoDust();
	public static Item loreBook = new ItemLoreBook();
	public static Item spellParchement = new Item();

	public static void init() {
		registerItems();
		registerRecipes();
	}

	private static void registerItems() {
		tab = new AITab();

		registerItem(tabletKnowledge, "Knowledge Tablet", "tablet");
		registerItem(bookBasic, "basicBook", "bookBasic");
		registerItem(wand, "wand", "wand", "wands/wand");
		registerItemNoTexture(rune, "basicRune", "rune");
		// registerItemNoTexture(runeIcon, "basicRuneIcon", "runeIcon");
		registerItemNoTexture(runeFunction, "basicRuneFunction", "runeFunction");
		registerItemNoTexture(runeType, "basicRuneType", "runeType");

		registerItem(ecoOrb, "ecoOrb", "ecoOrb");
		registerItem(arcaneDust, "arcaneDust", "arcaneDust", "dust/arcaneDust");
		registerItem(ecoDust, "ecoDust", "ecoDust", "dust/ecoDust");

		registerItem(loreBook, "loreBook", "lorebook");

		AISpells.registerSpells();
	}

	private static void registerRecipes() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(bookBasic, new ItemStack(Items.book), new ItemStack(ecoDust), new ItemStack(ecoDust), new ItemStack(ecoDust), new ItemStack(ecoDust)));
	}

	public static void registerItem(Item item, String name, String key) {
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + key).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}

	public static void registerItem(Item item, String name, String key, String texture) {
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":" + texture).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}

	public static void registerItemNoTexture(Item item, String name, String key) {
		item.setUnlocalizedName(key).setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
	}

	public static void registerSpell(Item item, String name, String key) {
		item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":spells/" + "spell").setCreativeTab(tab);
		GameRegistry.registerItem(item, name);
		spells.add(item);
	}

	public static class AISpells {
		public static ItemSpell dig = new ItemSpell(SpellReference.digCastTime, SpellReference.digName, SpellReference.digEcoUsage, new Spell(SpellReference.digName, EnumRuneFunction.dig), "dig");
		public static ItemSpell heal = new ItemSpell(SpellReference.healCastTime, SpellReference.healName, SpellReference.healEcoUsage, new Spell(SpellReference.healName, EnumRuneFunction.heal), "heal");
		public static ItemSpell levitate = new ItemSpell(SpellReference.levitateCastTime, SpellReference.levitateName, SpellReference.levitateEcoUsage, new Spell(SpellReference.levitateName, EnumRuneFunction.levitate), "levitate");
		public static ItemSpell lightning = new ItemSpell(SpellReference.lightningCastTime, SpellReference.lightningName, SpellReference.lightningEcoUsage, new Spell(SpellReference.lightningName, EnumRuneFunction.lightning), "lightning");
		public static ArrayList<Item> spells = new ArrayList<Item>();

		public static void registerSpells() {
			registerSpell(dig, SpellReference.digName, "dig");
			registerSpell(heal, SpellReference.healName, "heal");
			registerSpell(levitate, SpellReference.levitateName, "levitate");
			registerSpell(lightning, SpellReference.lightningName, "lightning");

		}

		public static void registerSpell(ItemSpell item, String name, String key) {
			item.setUnlocalizedName(key).setTextureName(ModProps.modid + ":spells/" + "spell").setCreativeTab(tab);
			GameRegistry.registerItem(item, name);
			spells.add(item);
		}
	}

}
