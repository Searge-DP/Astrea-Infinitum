package astreaInfinitum.tileEntities.rune;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.Constants.NBT;

import org.apache.commons.lang3.tuple.MutablePair;

import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.network.sync.MessageItemStackNBTSync;
import astreaInfinitum.network.sync.MessageRuneCarverSync;
import astreaInfinitum.utils.NBTHelper;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import fluxedCore.util.CoordinatePair;

public class TileEntityRuneCarver extends TileEntity implements IInventory {

	private ItemStack[] items;

	public List<CoordinatePair> nodes = new ArrayList<CoordinatePair>();
	public List<MutablePair<CoordinatePair, CoordinatePair>> lines = new ArrayList<MutablePair<CoordinatePair, CoordinatePair>>();
	public CoordinatePair prevNode;
	public ItemStack prevStack = null;

	public TileEntityRuneCarver() {
		items = new ItemStack[3];
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (getStackInSlot(0) != null) {
				if (prevStack == null) {
//					PacketHandler.INSTANCE.sendToAllAround(new MessageRuneCarverSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
					prevStack = getStackInSlot(0).copy();
				}
				if (prevStack != null && !NBTHelper.isStackEqual(prevStack, getStackInSlot(0))) {
//					PacketHandler.INSTANCE.sendToAllAround(new MessageRuneCarverSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
					prevStack = getStackInSlot(0).copy();
					lines.clear();
				}
				ItemStack rune = getStackInSlot(0);
				if (lines.isEmpty()) {
					System.out.println("lines " + xCoord + ":" + yCoord + ":" + zCoord);
					for (int i = 0; i < NBTHelper.getTagList(rune, "linesLeft", NBT.TAG_COMPOUND).tagCount(); i++) {
						NBTTagCompound leftLine = NBTHelper.getTagList(rune, "linesLeft", NBT.TAG_COMPOUND).getCompoundTagAt(i);
						NBTTagCompound rightLine = NBTHelper.getTagList(rune, "linesRight", NBT.TAG_COMPOUND).getCompoundTagAt(i);
						CoordinatePair leftCoord = CoordinatePair.readFromNBT(leftLine);
						CoordinatePair rightCoord = CoordinatePair.readFromNBT(rightLine);
						lines.add(new MutablePair<CoordinatePair, CoordinatePair>(leftCoord, rightCoord));
					}
				}
				if (!lines.isEmpty()) {
					System.out.println("lines " + xCoord + ":" + yCoord + ":" + zCoord);
					
					NBTTagList left = new NBTTagList();
					NBTTagList right = new NBTTagList();
					for (MutablePair<CoordinatePair, CoordinatePair> pair : this.lines) {
						NBTTagCompound leftLine = new NBTTagCompound();
						NBTTagCompound rightLine = new NBTTagCompound();
						pair.left.writeToNBT(leftLine);
						pair.right.writeToNBT(rightLine);
						left.appendTag(leftLine);
						right.appendTag(rightLine);
					}
					NBTHelper.setTagList(rune, "linesLeft", left);
					NBTHelper.setTagList(rune, "linesRight", right);
				}
				// PacketHandler.INSTANCE.sendToAllAround(new
				// MessageRuneCarverSync(this), new
				// TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord,
				// zCoord, 128D));
			} else {
				if (prevStack != null) {
					prevStack = null;
					lines.clear();
					nodes.clear();
				}
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f) <= 64;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		System.out.println("start read NBT");
		super.readFromNBT(nbt);
		readInventoryFromNBT(nbt);
		System.out.println("stop read NBT");
	}

	public void readInventoryFromNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = tags.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = (NBTTagCompound) nbttaglist.getCompoundTagAt(iter);
			byte slotID = tagList.getByte("Slot");
			if (slotID >= 0 && slotID < items.length) {
				items[slotID] = ItemStack.loadItemStackFromNBT(tagList);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		System.out.println("start save NBT");
		super.writeToNBT(tag);
		writeInventoryToNBT(tag);

		System.out.println("stop save NBT");
	}

	public void writeInventoryToNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int iter = 0; iter < items.length; iter++) {
			if (items[iter] != null) {
				NBTTagCompound tagList = new NBTTagCompound();
				tagList.setByte("Slot", (byte) iter);
				items[iter].writeToNBT(tagList);
				nbttaglist.appendTag(tagList);
			}
		}

		tags.setTag("Items", nbttaglist);
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return items[slot];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemstack = itemstack.splitStack(count);

			}
		}

		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return items[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		items[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return "Rune Carver";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

}