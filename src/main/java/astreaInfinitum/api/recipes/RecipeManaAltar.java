package astreaInfinitum.api.recipes;

import astreaInfinitum.api.EnumMana;
import net.minecraft.item.ItemStack;

public class RecipeManaAltar {

	private ItemStack baseItem;
	private ItemStack output;
	private ItemStack northItem;
	private ItemStack southItem;
	private ItemStack eastItem;
	private ItemStack westItem;
	private EnumMana mana;
	private int manaDustNeeded;

	public RecipeManaAltar(ItemStack output, ItemStack baseItem, ItemStack northItem, ItemStack southItem, ItemStack eastItem, ItemStack westItem, EnumMana mana, int manaDustNeeded) {
		this.baseItem = baseItem;
		this.output = output;
		this.northItem = northItem;
		this.southItem = southItem;
		this.eastItem = eastItem;
		this.westItem = westItem;
		this.mana = mana;
		this.manaDustNeeded = manaDustNeeded;
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

	public EnumMana getMana() {
		return mana;
	}

	public int getManaDustNeeded() {
		return manaDustNeeded;
	}

}
