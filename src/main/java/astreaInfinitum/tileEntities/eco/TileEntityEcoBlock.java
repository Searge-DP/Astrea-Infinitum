package astreaInfinitum.tileEntities.eco;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEcoBlock extends TileEntity {

	@Override
	public boolean canUpdate() {
		return false;
	}

	public boolean visible = true;

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("visible", visible);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		visible = tag.getBoolean("visible");
	}

}
