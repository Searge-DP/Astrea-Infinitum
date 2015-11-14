package astreaInfinitum.client.gui.runeCarver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import fluxedCore.client.gui.slots.SlotWhitelist;

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
		
		addSlotToContainer(new Slot(tile, 2, 16, 16));
		addSlotToContainer(new Slot(tile, 1, 191, 66));
		addSlotToContainer(new SlotWhitelist(tile, 0, 16, 110, new ItemStack[]{new ItemStack(AIItems.rune)}));
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
