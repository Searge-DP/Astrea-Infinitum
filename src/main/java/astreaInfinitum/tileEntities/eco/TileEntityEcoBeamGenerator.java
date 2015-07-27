package astreaInfinitum.tileEntities.eco;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityEcoBeamGenerator extends TileEntity {

	public int type;

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

}
