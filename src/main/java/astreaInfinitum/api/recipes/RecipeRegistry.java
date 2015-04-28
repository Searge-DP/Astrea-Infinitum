package astreaInfinitum.api.recipes;

import java.util.ArrayList;
import java.util.List;

import astreaInfinitum.api.EnumMana;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeEventFactory;

public class RecipeRegistry {

	private static List<RecipeManaAltar> recipeManaAltar = new ArrayList<RecipeManaAltar>();

	public static void registerManaAltarRecipe(RecipeManaAltar altar) {
		recipeManaAltar.add(altar);
	}

	public static List<RecipeManaAltar> getManaAltarRecipes() {
		return recipeManaAltar;
	}

	public static List<RecipeManaAltar> getRecipesForItem(ItemStack item) {
		List<RecipeManaAltar> returnList = new ArrayList<RecipeManaAltar>();
		for (RecipeManaAltar r : recipeManaAltar) {
			if (r.getBaseItem().isItemEqual(item)) {
				returnList.add(r);
			}
		}
		return null;
	}

	public static RecipeManaAltar getRecipeForItems(ItemStack base, ItemStack north, ItemStack south, ItemStack east, ItemStack west, EnumMana mana) {
		for (RecipeManaAltar r : recipeManaAltar) {
			if (r.getMana() == mana)
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
