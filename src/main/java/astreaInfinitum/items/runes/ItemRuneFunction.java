package astreaInfinitum.items.runes;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.runes.EnumRuneFunction;

public class ItemRuneFunction extends Item {

	public IIcon[] icons = new IIcon[EnumRuneFunction.values().length];

	public ItemRuneFunction() {
		setHasSubtypes(true);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < EnumRuneFunction.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return EnumRuneFunction.values()[stack.getItemDamage()].name;
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getRenderPasses(int metadata) {
		return 2;
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
		if (pass == 0) {
			return this.itemIcon;
		} else {
			return icons[damage];
		}
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.itemIcon = icons.registerIcon(ModProps.modid + ":runes/basicRuneFunction");

		for (int i = 0; i < this.icons.length; i++) {
			this.icons[i] = registerIcon(icons, EnumRuneFunction.values()[i].name);
		}
	}

	private IIcon registerIcon(IIconRegister icons, String name) {
		IIcon icon = icons.registerIcon(ModProps.MODID + ":runes/icons/" + name);
		return icon;
	}

}
