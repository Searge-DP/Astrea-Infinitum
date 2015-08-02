package astreaInfinitum.items;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import astreaInfinitum.AITab;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.ItemProjectileSpell;
import astreaInfinitum.api.ItemSpell;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.items.dusts.ItemArcaneDust;
import astreaInfinitum.items.dusts.ItemEcoDust;
import astreaInfinitum.items.eco.ItemEcoOrb;
import astreaInfinitum.items.learning.ItemBookBasic;
import astreaInfinitum.items.runes.ItemRune;
import astreaInfinitum.items.tablets.ItemKnowledgeTablet;
import astreaInfinitum.items.wands.ItemWand;
import astreaInfinitum.spells.SpellAreaRegen;
import astreaInfinitum.spells.SpellDay;
import astreaInfinitum.spells.projectile.SpellProjDig;
import astreaInfinitum.spells.projectile.SpellProjTrans;
import astreaInfinitum.spells.projectile.SpellRain;
import cpw.mods.fml.common.registry.GameRegistry;

public class AIItems {

	public static ArrayList<Item> spells = new ArrayList<Item>();

	public static CreativeTabs tab;;

	public static void init() {
		registerItems();
		registerRecipes();
	}

	public static Item spellDay = new ItemSpell(60, "day", 5, new SpellDay());
	public static Item spellAreaRegen = new ItemSpell(40, "aRegen", 30, new SpellAreaRegen());

	public static Item spellProjDig = new ItemProjectileSpell(20, "Projectile Dig", 5, new SpellProjDig());
	public static Item spellProjTrans = new ItemProjectileSpell(20, "Trans", 5, new SpellProjTrans());
	public static Item spellProjRain = new ItemProjectileSpell(20, "Rain", 5, new SpellRain());

	public static Item bookBasic = new ItemBookBasic();

	public static Item tabletKnowledge = new ItemKnowledgeTablet();
	public static Item wand = new ItemWand().setFull3D();

	public static Item rune = new ItemRune();
	public static Item ecoOrb = new ItemEcoOrb();

	public static Item arcaneDust = new ItemArcaneDust();
	public static Item ecoDust = new ItemEcoDust();

	private static void registerItems() {
		tab = new AITab();

		registerItem(tabletKnowledge, "Knowledge Tablet", "tablet");
		registerItem(bookBasic, "basicBook", "bookBasic");
		registerItem(wand, "wand", "wand", "wands/wand");
		registerSpell(spellDay, "day", "day");
		registerSpell(spellProjDig, "dig", "dig");
		registerSpell(spellAreaRegen, "areaRegen", "areaRegen");
		registerSpell(spellProjTrans, "projTrans", "projTrans");
		registerSpell(spellProjRain, "projRain", "projRain");
		registerItemNoTexture(rune, "basicRune", "rune");
		registerItem(ecoOrb, "ecoOrb", "ecoOrb");
		registerItem(arcaneDust, "arcaneDust", "arcaneDust", "dust/arcaneDust");
		registerItem(ecoDust, "ecoDust", "ecoDust", "dust/ecoDust");

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

}
