package astreaInfinitum.items.runes;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import astreaInfinitum.ModProps;

public class ItemRune extends Item {

	public ItemRune() {
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.itemIcon = icons.registerIcon(ModProps.modid + ":runes/basicRune");
	}



}
