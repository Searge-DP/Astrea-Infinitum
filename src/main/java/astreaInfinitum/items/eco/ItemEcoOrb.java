package astreaInfinitum.items.eco;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import astreaInfinitum.ModProps;

public class ItemEcoOrb extends Item {

	public String[] names = new String[] { "Red", "Green", "Blue", "Yellow", "Light", "Dark" };
	public IIcon[] icons = new IIcon[names.length];

	public ItemEcoOrb() {
		setHasSubtypes(true);
		setMaxStackSize(16);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		for (int i = 0; i < names.length; i++) {
			this.icons[i] = icons.registerIcon(ModProps.MODID + ":eco/ecoOrb" + names[i]);
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return names[stack.getItemDamage()] + " Eco Orb";
	}

}
