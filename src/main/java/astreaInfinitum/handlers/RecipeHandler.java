package astreaInfinitum.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import astreaInfinitum.api.EnumEco;
import astreaInfinitum.api.recipes.RecipeEcoAltar;
import astreaInfinitum.api.recipes.RecipeRegistry;

public class RecipeHandler {

	public static void postInit() {
		RecipeRegistry.registerEcoAltarRecipe(new RecipeEcoAltar(new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), EnumEco.light, 12));
		RecipeRegistry.registerEcoAltarRecipe(new RecipeEcoAltar(new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), EnumEco.dark, 12));

	}

}
