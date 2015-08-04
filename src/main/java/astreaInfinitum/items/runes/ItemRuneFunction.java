package astreaInfinitum.items.runes;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import astreaInfinitum.ModProps;

public class ItemRuneFunction extends Item {

	public ItemRuneFunction() {
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.itemIcon = icons.registerIcon(ModProps.modid + ":runes/basicRuneFunction");
	}



}
