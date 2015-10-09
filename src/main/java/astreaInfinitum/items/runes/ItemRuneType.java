package astreaInfinitum.items.runes;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.runes.EnumSpellType;

public class ItemRuneType extends Item {

	public IIcon touch, projectile, self;

	public ItemRuneType() {
		setHasSubtypes(true);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < EnumSpellType.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		switch (stack.getItemDamage()) {
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
			switch (damage) {
				case 0:
					return self;
				case 1:
					return touch;
				case 2:
					return projectile;
				default:
					return this.itemIcon;
			}
		}
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.itemIcon = icons.registerIcon(ModProps.modid + ":runes/basicRuneType");

		touch = registerIcon(icons, "touch");
		projectile = registerIcon(icons, "projectile");
		self = registerIcon(icons, "self");

	}

	private IIcon registerIcon(IIconRegister icons, String name) {
		IIcon icon = icons.registerIcon(ModProps.MODID + ":runes/icons/" + name);
		return icon;
	}

}
