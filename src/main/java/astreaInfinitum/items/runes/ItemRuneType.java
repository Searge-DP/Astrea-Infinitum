package astreaInfinitum.items.runes;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.runes.EnumSpellType;

public class ItemRuneType extends Item {

	public ItemRuneType() {
		setHasSubtypes(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < EnumSpellType.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		switch(stack.getItemDamage()){
		case 0:
			return "Self Type Rune";
		case 1:
			return "Touch Type Rune";
		case 2:
			return "Projectile Type Rune";
		default:
			return "Rune Function";
		}
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.itemIcon = icons.registerIcon(ModProps.modid + ":runes/basicRuneType");
	}
	

}
