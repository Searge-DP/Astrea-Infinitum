package astreaInfinitum.api.recipes;

import astreaInfinitum.api.EnumPlayerEco;
import net.minecraft.item.ItemStack;

public class RecipeEcoAltar {

	private ItemStack baseItem;
	private ItemStack output;
	private ItemStack northItem;
	private ItemStack southItem;
	private ItemStack eastItem;
	private ItemStack westItem;
	private EnumPlayerEco eco;
	private int ecoDustNeeded;

	public RecipeEcoAltar(ItemStack output, ItemStack baseItem, ItemStack northItem, ItemStack southItem, ItemStack eastItem, ItemStack westItem, EnumPlayerEco eco, int ecoDustNeeded) {
		this.baseItem = baseItem;
		this.output = output;
		this.northItem = northItem;
		this.southItem = southItem;
		this.eastItem = eastItem;
		this.westItem = westItem;
		this.eco = eco;
		this.ecoDustNeeded = ecoDustNeeded;
	}

	public ItemStack getBaseItem() {
		return baseItem;
	}

	public ItemStack getOutput() {
		return output;
	}

	public ItemStack getNorthItem() {
		return northItem;
	}

	public ItemStack getSouthItem() {
		return southItem;
	}

	public ItemStack getEastItem() {
		return eastItem;
	}

	public ItemStack getWestItem() {
		return westItem;
	}

	public EnumPlayerEco getEco() {
		return eco;
	}

	public int getEcoDustNeeded() {
		return ecoDustNeeded;
	}

}
