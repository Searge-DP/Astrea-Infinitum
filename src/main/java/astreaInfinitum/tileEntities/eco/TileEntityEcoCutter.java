package astreaInfinitum.tileEntities.eco;

import fluxedCore.tileEntities.TileEntityInventory;

public class TileEntityEcoCutter extends TileEntityInventory {

	public float red = 0;
	public float green = 0;
	public float blue = 0;

	public TileEntityEcoCutter() {
		super(1);
		red = 1;
		green = 1;
		blue = 1;
	}

	@Override
	public String getInventoryName() {
		return "Eco Cutter";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@Override
	public double getMaxRenderDistanceSquared() {
		return Double.MAX_VALUE;
	}

}
