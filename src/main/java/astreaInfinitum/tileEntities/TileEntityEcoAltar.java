package astreaInfinitum.tileEntities;

import astreaInfinitum.api.IEcoAltarBlock;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.network.MessageAltarSync;
import astreaInfinitum.network.MessageParticles;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.utils.BlockPos;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileEntityEcoAltar extends TileEntity {

	public boolean activated;
	public boolean shouldConvert;
	public int convertTime;
	public boolean converted = false;
	public boolean shouldSendParticle;
	public List<BlockPos> blocksToConvert = new ArrayList<BlockPos>();

	public TileEntityEcoAltar() {
	}

	public boolean isAltarActivated(World world, int x, int y, int z) {
		for (int X = x - 3; X <= x + 3; X++) {
			for (int Z = z - 3; Z <= z + 3; Z++) {
				if (X != x && Z != z) if (!(world.getBlock(X, y, Z) instanceof IEcoAltarBlock)) {
					return false;
				}
			}
		}
		return true;
	}


	@Override
	public void updateEntity() {
		//		if (worldObj.isRemote) {
		//
		//			EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 600);
		//			// ParticleBeamFX fx = new ParticleBeamFX(worldObj, player, xCoord,
		//			// yCoord, zCoord, 0, 0.2f, 0.5f, 10);
		//			EntityEcoBeamFX f1 = new EntityEcoBeamFX(worldObj, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, player.posX, player.posY, player.posZ, new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), 10);
		//				Minecraft.getMinecraft().effectRenderer.addEffect(f1);
		//		}

		if (!worldObj.isRemote) {
			if (isAltarActivated(worldObj, xCoord, yCoord, zCoord)) {
				activated = true;
				markDirty();
			}
			if (!shouldActivate(worldObj, xCoord, yCoord, zCoord) && shouldConvert) {
				shouldConvert = false;
				blocksToConvert.clear();
				markDirty();
			}
			if (shouldConvert) {
				if (blocksToConvert.isEmpty()) {
					getBlocksToConvert(worldObj, xCoord, yCoord, zCoord);
					Collections.shuffle(blocksToConvert);
				}

				for (BlockPos pos : blocksToConvert) {
					int X = pos.x;
					int Z = pos.z;
					if (!converted) {
						if (worldObj.getBlock(X, yCoord, Z) == Blocks.nether_brick) {
							PacketHandler.INSTANCE.sendToAllAround(new MessageParticles("blockdust_" + Block.getIdFromBlock(worldObj.getBlock(X, yCoord, Z)) + "_0", X, yCoord, Z, 0, 0.8, 0, 16), new TargetPoint(worldObj.provider.dimensionId, X, yCoord, Z, 128D));
							worldObj.setBlock(X, yCoord, Z, AIBlocks.ecoAltarBlock);
							converted = true;
							markDirty();
						}

						if (worldObj.getBlock(X, yCoord, Z) == Blocks.quartz_block) {
							PacketHandler.INSTANCE.sendToAllAround(new MessageParticles("blockdust_" + Block.getIdFromBlock(worldObj.getBlock(X, yCoord, Z)) + "_0", X, yCoord, Z, 0, 0.8, 0, 16), new TargetPoint(worldObj.provider.dimensionId, X, yCoord, Z, 128D));
							worldObj.setBlock(X, yCoord, Z, AIBlocks.ecoRitualBlock);
							converted = true;
							markDirty();
						}

					}
				}
			}
			convertTime -= 1;
			if (convertTime <= 0) {
				converted = false;
				convertTime = 5;
			}
		}

	}

	public void getBlocksToConvert(World world, int x, int y, int z) {
		for (int X = x - 3; X <= x + 3; X++) {
			for (int Z = z - 3; Z <= z + 3; Z++) {
				Block block = world.getBlock(X, y, Z);
				if (block == Blocks.nether_brick || block == Blocks.quartz_block) {
					blocksToConvert.add(new BlockPos(X, y, Z));
				}
			}
		}
	}

	@Override
	public void markDirty() {
		super.markDirty();
		PacketHandler.INSTANCE.sendToAllAround(new MessageAltarSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
	}

	public void activate(World world, int x, int y, int z) {
		for (int X = x - 3; X <= x + 3; X++) {
			for (int Z = z - 3; Z <= z + 3; Z++) {
				if (world.getBlock(X, y, Z) == Blocks.nether_brick) {
					world.setBlock(X, y, Z, AIBlocks.ecoAltarBlock);
				}
				if (world.getBlock(X, y, Z) == Blocks.quartz_block) {
					world.setBlock(X, y, Z, AIBlocks.ecoRitualBlock);
				}

			}
		}
	}

	public boolean shouldActivate(World world, int x, int y, int z) {
		for (int X = x - 3; X <= x + 3; X++) {
			for (int Z = z - 3; Z <= z + 3; Z++) {
				if (world.getBlock(X, y, Z) == Blocks.nether_brick) {
					return true;
				}
				if (world.getBlock(X, y, Z) == Blocks.quartz_block) {
					return true;
				}

			}
		}
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("activated", activated);
		tag.setBoolean("shouldConvert", shouldConvert);
		tag.setInteger("convertTime", convertTime);
		writeBlocksToNbt(tag);
	}

	public void writeBlocksToNbt(NBTTagCompound tags) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int iter = 0; iter < blocksToConvert.size(); iter++) {
			if (blocksToConvert.get(iter) != null) {
				NBTTagCompound tagList = new NBTTagCompound();
				tagList.setInteger("position", iter);
				blocksToConvert.get(iter).writeToNbt(tagList);
				nbttaglist.appendTag(tagList);
			}
		}

		tags.setTag("Blocks", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		activated = tag.getBoolean("activated");
		shouldConvert = tag.getBoolean("shouldConvert");
		convertTime = tag.getInteger("convertTime");
		readBlocksFromNbt(tag);
	}

	public void readBlocksFromNbt(NBTTagCompound tags) {
		NBTTagList nbttaglist = tags.getTagList("Blocks", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = nbttaglist.getCompoundTagAt(iter);
			int position = tagList.getInteger("position");
			if (position >= 0 && position < blocksToConvert.size()) {
				blocksToConvert.set(position, BlockPos.getBlockFromNbt(tagList));
			}
		}
	}

	public boolean isActivated() {
		return activated;
	}

	public boolean isShouldConvert() {
		return shouldConvert;
	}

	public int getConvertTime() {
		return convertTime;
	}

	public boolean isConverted() {
		return converted;
	}

	public boolean isShouldSendParticle() {
		return shouldSendParticle;
	}

	public List<BlockPos> getBlocksToConvert() {
		return blocksToConvert;
	}

}
