package astreaInfinitum.client.gui.runeCarver;

import org.apache.commons.lang3.tuple.MutablePair;

import fluxedCore.util.CoordinatePair;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.network.sync.MessageItemStackNBTSync;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import astreaInfinitum.utils.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SlotRune extends Slot {

	public SlotRune(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	@Override
	public boolean isItemValid(ItemStack p_75214_1_) {
		return false;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
		ItemStack rune = inventory.getStackInSlot(0);
		player.inventory.setItemStack(rune);
		inventory.setInventorySlotContents(0, null);
		super.onPickupFromSlot(player, stack);
	}

	@Override
	public boolean getHasStack() {
		return inventory.getStackInSlot(0) != null;
	}

	@Override
	public ItemStack getStack() {
		return inventory.getStackInSlot(0);
	}

}
