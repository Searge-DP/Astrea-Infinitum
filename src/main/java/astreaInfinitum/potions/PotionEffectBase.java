package astreaInfinitum.potions;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class PotionEffectBase extends PotionEffect {

	public PotionEffectBase(int id, int dutation, int amplifier) {
		super(id, dutation, amplifier, true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ItemStack> getCurativeItems() {
		List list = super.getCurativeItems();
		list.clear();
		return list;
	}

	@Override
	public boolean isCurativeItem(ItemStack stack) {
		return false;
	}
}
