package astreaInfinitum;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import astreaInfinitum.items.AIItems;

public class AITab extends CreativeTabs {

	public AITab() {
		super("Astrea Infinitum");
	}

	@Override
	public Item getTabIconItem() {
		return AIItems.bookBasic;
	}

}
