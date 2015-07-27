package astreaInfinitum.client.gui.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerSpell extends Container {

	private InventoryPlayer playerInv;

	public ContainerSpell(InventoryPlayer player) {
		this.playerInv = player;

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 10 + 18 * x, 127 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(playerInv, x, 10 + 18 * x, 185));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
