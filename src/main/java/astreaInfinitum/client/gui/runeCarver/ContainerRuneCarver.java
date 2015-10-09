package astreaInfinitum.client.gui.runeCarver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;

public class ContainerRuneCarver extends Container {

	public ContainerRuneCarver(InventoryPlayer player, TileEntityRuneCarver tile) {
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(player, x, 31 + 18 * x, 215));
		}
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(player, x + y * 9 + 9, 31 + 18 * x, 157 + y * 18));
			}
		}
		addSlotToContainer(new Slot(tile, 0, 17, 70));
		addSlotToContainer(new SlotRune(tile, 1, 188, 70));
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
		return null;
	}

}
