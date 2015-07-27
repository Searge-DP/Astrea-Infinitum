package astreaInfinitum.items.wands;

import astreaInfinitum.api.wands.IWand;
import astreaInfinitum.utils.RenderingUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemWand extends Item implements IWand {

	public ItemWand() {
		setFull3D();
	}

}
