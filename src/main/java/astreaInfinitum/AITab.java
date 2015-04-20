package astreaInfinitum;

import java.util.Random;

import astreaInfinitum.items.AIItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AITab extends CreativeTabs {

	public static Item currentItem = Items.glowstone_dust;

	public AITab() {
		super("Astrea Infinitum");
	}

	@Override
	public Item getTabIconItem() {
		return AIItems.bookBasic;
	}

}
