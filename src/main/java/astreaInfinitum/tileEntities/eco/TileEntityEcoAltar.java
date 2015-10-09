package astreaInfinitum.tileEntities.eco;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.api.IEcoAltarBlock;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.network.sync.MessageEcoAltarSync;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Created by Jared on 8/26/2015.
 */
public class TileEntityEcoAltar extends TileEntity {
	public boolean activated;

	public TileEntityEcoAltar() {
	}

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
		PacketHandler.INSTANCE.sendToAllAround(new MessageEcoAltarSync(this), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
	}

	public boolean isAltarActivated(World world, int x, int y, int z) {
		for (int X = x - 3; X <= x + 3; X++) {
			for (int Z = z - 3; Z <= z + 3; Z++) {
				if (X != x && Z != z) {
					if (!(world.getBlock(X, y, Z) instanceof IEcoAltarBlock)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("activated", activated);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		activated = tag.getBoolean("activated");
	}

	public boolean isActivated() {
		return activated;
	}

}
