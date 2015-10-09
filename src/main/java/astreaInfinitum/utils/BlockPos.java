package astreaInfinitum.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;

public class BlockPos {

	public int x;
	public int y;
	public int z;

	public BlockPos(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public BlockPos(MovingObjectPosition mop) {
		this.x = mop.blockX;
		this.y = mop.blockY;
		this.z = mop.blockZ;
	}
	

	public NBTTagCompound writeToNbt(NBTTagCompound tag) {
		tag.setInteger("x", x);
		tag.setInteger("y", y);
		tag.setInteger("z", z);

		return tag;
	}

	public void readFromNbt(NBTTagCompound tag) {
		x = tag.getInteger("x");
		y = tag.getInteger("y");
		z = tag.getInteger("z");
	}

	public static BlockPos getBlockFromNbt(NBTTagCompound tag) {
		return new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
	}
}
