package astreaInfinitum.api.utils;

import net.minecraft.tileentity.TileEntity;

/**
 * Created by Jared on 8/26/2015.
 */
public abstract class TileEntityClientServer extends TileEntity {

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) {
			updateClient();
		} else if (!worldObj.isRemote) {
			updateServer();
		}
	}

	public abstract void updateClient();

	public abstract void updateServer();


}
