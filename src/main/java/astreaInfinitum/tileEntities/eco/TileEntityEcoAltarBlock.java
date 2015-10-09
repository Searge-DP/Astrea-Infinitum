package astreaInfinitum.tileEntities.eco;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.network.MessageEcoAltarBlockSync;
import astreaInfinitum.network.PacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityEcoAltarBlock extends TileEntity {

	public boolean shouldTick = true;

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public void updateEntity() {
	}

	@Override
	public void markDirty() {
		super.markDirty();
		PacketHandler.INSTANCE.sendToAllAround(new MessageEcoAltarBlockSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
	}

	public void sendClientPacket() {
		PacketHandler.INSTANCE.sendToServer(new MessageEcoAltarBlockSync(this));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("tick", shouldTick);
		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		shouldTick = tag.getBoolean("tick");
		markDirty();
	}
}
