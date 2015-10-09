package astreaInfinitum.client.slot;

import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRuneResult extends Slot {

	public SlotRuneResult(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
		carv(stack);
		super.onPickupFromSlot(player, stack);

	}

	public void carv(ItemStack stack) {
		if (stack != null) {
			TileEntityRuneCarver tile = (TileEntityRuneCarver)inventory;
		}
	}

	@Override
	public boolean isItemValid(ItemStack p_75214_1_) {
		return false;
	}

}
