package astreaInfinitum.tileEntities.eco;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import astreaInfinitum.items.eco.ItemEcoOrb;
import astreaInfinitum.network.MessageEcoBeamGeneratorSync;
import astreaInfinitum.network.PacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityEcoBeamGenerator extends TileEntity implements IInventory {

	public int rotation;
	public int color;
	public ItemStack[] items;
	public TileEntityEcoInfuser infuser;

	public boolean shouldSendEco = false;

	public TileEntityEcoBeamGenerator() {
		items = new ItemStack[1];
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;

	}

	public void setShouldSendEco(boolean shouldSendEco) {
		this.shouldSendEco = shouldSendEco;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {

			int x = xCoord, y = yCoord, z = zCoord;
			if (isInfuser(worldObj, x + 3, y + 4, z + 3)) {
				infuser = (TileEntityEcoInfuser) worldObj.getTileEntity(x + 3, y + 4, z + 3);
				setShouldSendEco(true);
				setRotation(0);
			} else if (isInfuser(worldObj, x + 3, y + 4, z - 3)) {
				infuser = (TileEntityEcoInfuser) worldObj.getTileEntity(x + 3, y + 4, z - 3);
				setRotation(3);
				setShouldSendEco(true);
			} else if (isInfuser(worldObj, x - 3, y + 4, z - 3)) {
				infuser = (TileEntityEcoInfuser) worldObj.getTileEntity(x - 3, y + 4, z - 3);
				setRotation(2);
				setShouldSendEco(true);
			} else if (isInfuser(worldObj, x - 3, y + 4, z + 3)) {
				infuser = (TileEntityEcoInfuser) worldObj.getTileEntity(x - 3, y + 4, z + 3);
				setRotation(1);
				setShouldSendEco(true);
			} else {
				setRotation(-1);
				setShouldSendEco(false);
			}

			if (getStackInSlot(0) != null) {
				ItemStack stack = getStackInSlot(0);
				switch (stack.getItemDamage()) {
				case 0:
					setColor(0xff0000);
					break;
				case 1:
					setColor(0x00ff00);
					break;
				case 2:
					setColor(0x0000ff);
					break;
				case 3:
					setColor(0xffff00);
					break;
				case 4:
					setColor(0x00ffff);
					break;
				case 5:
					setColor(0xae7038);
					break;
				default:
					setColor(0xFFFFFF);
					break;
				}
			} else {
				setShouldSendEco(false);
			}

			markDirty();
		}

	}

	@Override
	public void markDirty() {
		super.markDirty();
		if (infuser != null) {
			PacketHandler.INSTANCE.sendToAllAround(new MessageEcoBeamGeneratorSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
		} else {
			PacketHandler.INSTANCE.sendToAllAround(new MessageEcoBeamGeneratorSync(getStackInSlot(0), rotation, color, xCoord, yCoord, zCoord, 0, -1, 0, shouldSendEco), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
		}
	}

	public boolean isInfuser(World world, int x, int y, int z) {
		if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityEcoInfuser) {
			return true;
		}
		return false;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	@Override
	public int getSizeInventory() {
		return 16;
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
		return "Eco Beam Generator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 16;
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
		return stack.getItem() instanceof ItemEcoOrb;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readInventoryFromNBT(nbt);
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
		super.writeToNBT(tag);
		writeInventoryToNBT(tag);
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
}
