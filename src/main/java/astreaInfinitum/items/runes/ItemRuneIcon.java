package astreaInfinitum.items.runes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import scala.util.Random;
import astreaInfinitum.ModProps;

public class ItemRuneIcon extends Item {
	public List<String> iconStrings = new ArrayList<String>();
	public List<IIcon> icons = new ArrayList<IIcon>(iconStrings.size());
	public Map<String, IIcon> stringToIcon = new HashMap<String, IIcon>();

	public int color;

	public ItemRuneIcon() {
		setHasSubtypes(true);
		color = 0;
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.itemIcon = icons.registerIcon(ModProps.modid + ":runes/basicRuneIcon");
		registerIcon(icons, "horus");
		registerIcon(icons, "melon");
		registerIcon(icons, "scooter");
		registerIcon(icons, "shark");
		registerIcon(icons, "spear");
		registerIcon(icons, "tornado");
		registerIcon(icons, "wrench");
		registerIcon(icons, "aquarius");
		registerIcon(icons, "transform");
		registerIcon(icons, "eye");
		registerIcon(icons, "hex");
		registerIcon(icons, "bio");
		
	}

	private void registerIcon(IIconRegister icons, String name) {
		IIcon icon = icons.registerIcon(ModProps.MODID + ":runes/icons/" + name);
		if (!this.icons.contains(icon)) {
			this.icons.add(icon);
			this.iconStrings.add(name);
			this.stringToIcon.put(name, icon);
		}
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
			return stringToIcon.get(iconStrings.get(damage));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 12; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return iconStrings.get(stack.getItemDamage()) + " Rune";
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		if (pass != 0) {
			return new Random().nextInt()>>new Random().nextInt(256)/255;
		}
		return 0xFFFFFF;
	}

}
