package astreaInfinitum.tileEntities;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityHidden extends TileEntity {

	private Block block = null;

	@Override
	public void writeToNBT(NBTTagCompound tag) {
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
	}

	public void convertBlock(World world, int x, int y, int z) {

	}
}
