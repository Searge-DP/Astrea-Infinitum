package astreaInfinitum.api.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import astreaInfinitum.api.EnumPlayerEco;

public class RecipeRegistry {

	private static List<RecipeEcoAltar> recipeEcoAltar = new ArrayList<RecipeEcoAltar>();

	public static void registerEcoAltarRecipe(RecipeEcoAltar altar) {
		recipeEcoAltar.add(altar);
	}

	public static List<RecipeEcoAltar> getEcoAltarRecipes() {
		return recipeEcoAltar;
	}

	public static List<RecipeEcoAltar> getRecipesForItem(ItemStack item) {
		List<RecipeEcoAltar> returnList = new ArrayList<RecipeEcoAltar>();
		for (RecipeEcoAltar r : recipeEcoAltar) {
			if (r.getBaseItem().isItemEqual(item)) {
				returnList.add(r);
			}
		}
		return null;
	}

	public static RecipeEcoAltar getRecipeForItems(ItemStack base, ItemStack north, ItemStack south, ItemStack east, ItemStack west, EnumPlayerEco eco) {
		for (RecipeEcoAltar r : recipeEcoAltar) {
			if (r.getEco() == eco)
				if (r.getBaseItem().isItemEqual(base)) {
					if (r.getNorthItem().isItemEqual(north)) {
						if (r.getSouthItem().isItemEqual(south)) {
							if (r.getEastItem().isItemEqual(east)) {
								if (r.getWestItem().isItemEqual(west)) {
									return r;
								}
							}
						}
					}
				}
		}
		return null;
	}

}
