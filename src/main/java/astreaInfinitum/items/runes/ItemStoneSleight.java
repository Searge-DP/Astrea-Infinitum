package astreaInfinitum.items.runes;

import astreaInfinitum.utils.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStoneSleight extends Item {

	public ItemStoneSleight() {

	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean par5) {
		if (NBTHelper.getTag(stack) != null) {
		}
	}
}
