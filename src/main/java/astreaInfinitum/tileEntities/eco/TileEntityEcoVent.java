package astreaInfinitum.tileEntities.eco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.api.eco.EnumEco;
import astreaInfinitum.api.eco.IEcoBlock;
import astreaInfinitum.blocks.AIBlocks;
import astreaInfinitum.blocks.eco.BlockEcoBlock;
import astreaInfinitum.blocks.eco.world.BlockEcoOre;
import astreaInfinitum.network.MessageEcoBlockSync;
import astreaInfinitum.network.MessageEcoVentSync;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.network.particles.MessageSpawnBeamFX;
import astreaInfinitum.network.particles.MessageSpawnEcoFX;
import astreaInfinitum.utils.BlockPos;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityEcoVent extends TileEntity {

	public BlockPos currentBreakPos = null;
	public int defaultBreakTime = 40;
	public int breakTime = 0;
	public boolean converted = false;
	public List<BlockPos> blockToBreak = new ArrayList<BlockPos>();
	public EnumEco currentEco;
	public double currentEcoCount = 0;
	public boolean activated;

	public int[][] coords = new int[][] { { +1, 0, 0 }, { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 1, 0, 1 }, { 1, 0, -1 }, { -1, 0, -1 }, { -1, 0, 1 }, { 0, -1, 0 }, { 0, -2, 0 }, { 0, -3, 0 } };

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (isActive(worldObj, xCoord, yCoord, zCoord)) {
				changeVisibility(worldObj, xCoord, yCoord, zCoord, false);
				currentEco = getCurrentEco(worldObj, xCoord, yCoord, zCoord);
				if (blockToBreak.isEmpty() && currentEco != null) {
					getBlocksToBreak(worldObj, xCoord, yCoord, zCoord);
					Collections.shuffle(blockToBreak);
				}

				for (BlockPos pos : blockToBreak) {
					if (worldObj.getBlock(pos.x, pos.y, pos.z) instanceof IEcoBlock)
						if (!converted) {
							if (currentBreakPos == null) {
								currentBreakPos = pos;
								converted = true;
							}

						}
				}
				if (currentBreakPos == null) {
					blockToBreak.clear();
				}
				if (breakTime >= defaultBreakTime && currentBreakPos != null) {
					breakTime = 0;
					worldObj.setBlockToAir(currentBreakPos.x, currentBreakPos.y, currentBreakPos.z);
					currentEcoCount += 16;
					currentBreakPos = null;
					blockToBreak.remove(currentBreakPos);
					converted = false;
				}
				if (currentBreakPos != null && (worldObj.isAirBlock(currentBreakPos.x, currentBreakPos.y, currentBreakPos.z) || !(worldObj.getBlock(currentBreakPos.x, currentBreakPos.y, currentBreakPos.z) instanceof IEcoBlock))) {
					blockToBreak.remove(currentBreakPos);
					currentBreakPos = null;
					converted = false;
					breakTime = 0;
				}
				if (currentBreakPos != null) {
					IEcoBlock eco = (IEcoBlock) worldObj.getBlock(currentBreakPos.x, currentBreakPos.y, currentBreakPos.z);
					switch (eco.getEco(worldObj, currentBreakPos.x, currentBreakPos.y, currentBreakPos.z)) {
					case red:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnBeamFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, currentBreakPos.x + 0.5, currentBreakPos.y + 0.5, currentBreakPos.z + 0.5, 10, 1f, 0f, 0f), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case green:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnBeamFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, currentBreakPos.x + 0.5, currentBreakPos.y + 0.5, currentBreakPos.z + 0.5, 10, 0f, 1f, 0f), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case blue:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnBeamFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, currentBreakPos.x + 0.5, currentBreakPos.y + 0.5, currentBreakPos.z + 0.5, 10, 0f, 0f, 1f), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case yellow:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnBeamFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, currentBreakPos.x + 0.5, currentBreakPos.y + 0.5, currentBreakPos.z + 0.5, 10, 1f, 1f, 0f), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case dark:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnBeamFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, currentBreakPos.x + 0.5, currentBreakPos.y + 0.5, currentBreakPos.z + 0.5, 10, 0.8f, 0.58f, 0.27f), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case light:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnBeamFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, currentBreakPos.x + 0.5, currentBreakPos.y + 0.5, currentBreakPos.z + 0.5, 10, 0.19f, 0.91f, 0.97f), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					default:
						break;
					}
					breakTime++;
				}
				if (currentEcoCount > 0)
					switch (currentEco) {
					case red:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnEcoFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 1f, 0f, 0f, 0.15), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case green:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnEcoFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 0f, 1f, 0f, 0.15), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case blue:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnEcoFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 0f, 0f, 1f, 0.15), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case yellow:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnEcoFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 1f, 1f, 0f, 0.15), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case dark:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnEcoFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 0.8f, 0.58f, 0.27f, 0.15), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					case light:
						PacketHandler.INSTANCE.sendToAllAround(new MessageSpawnEcoFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 0.19f, 0.91f, 0.97f, 0.15), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
						break;
					default:
						break;
					}
				PacketHandler.INSTANCE.sendToAllAround(new MessageEcoVentSync(this), new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 128D));
			}else{
				changeVisibility(worldObj, xCoord, yCoord, zCoord, true);
			}
			if (!blockToBreak.isEmpty())
				blockToBreak.clear();

		}
	}

	public void getBlocksToBreak(World world, int x, int y, int z) {
		for (int X = x - 8; X <= x + 8; X++) {
			for (int Y = 0; Y < y; Y++) {
				for (int Z = z - 8; Z <= z + 8; Z++) {
					Block block = world.getBlock(X, Y, Z);
					if (block != null)
						if (block instanceof BlockEcoOre) {
							if (((IEcoBlock) block).getEco(world, X, Y, Z) == currentEco)
								blockToBreak.add(new BlockPos(X, Y, Z));
						}
				}
			}
		}
	}

	public Block getCurrentBlock(World world, int x, int y, int z) {
		BlockPos pos = getNextBlock(worldObj, xCoord, yCoord, zCoord);
		if (pos != null)
			if (world.getBlock(pos.x, pos.y, pos.z) != null)
				return world.getBlock(pos.x, pos.y, pos.z);
		return null;
	}

	public BlockPos getNextBlock(World world, int x, int y, int z) {
		for (int newX = x - 8; newX <= x + 8; newX++) {
			for (int newY = 0; newY < y; newY++) {
				for (int newZ = z - 8; newZ <= z + 8; newZ++) {
					if (world.getBlock(newX, newY, newZ) == AIBlocks.ecoBlock)
						return new BlockPos(newX, newY, newZ);
				}
			}
		}

		return null;
	}

	public void changeVisibility(World world, int x, int y, int z, boolean visible) {
			for (int[] i : coords) {
				int X = i[0];
				int Y = i[1];
				int Z = i[2];
				if (!world.isAirBlock(x+X, y+Y, z+Z) && world.getBlock(x + X, y + Y, z + Z) instanceof BlockEcoBlock) {
					TileEntityEcoBlock tile = (TileEntityEcoBlock) world.getTileEntity(x + X, y + Y, z + Z);
					tile.setVisible(visible);
					PacketHandler.INSTANCE.sendToAllAround(new MessageEcoBlockSync(tile), new TargetPoint(world.provider.dimensionId, x + X, y + Y, z + Z, 128D));
					world.getBlock(x + X, y + Y, z + Z).onNeighborBlockChange(world, x + X, y + Y, z + Z, world.getBlock(x + X, y + Y, z + Z));
				}
			}
	}

	@Override
	public void invalidate() {
		changeVisibility(worldObj, xCoord, yCoord, zCoord, true);
	}

	public boolean isActive(World world, int x, int y, int z) {
		EnumEco currentEco = getCurrentEco(world, x, y, z);
		boolean allow = true;
		for (int[] i : coords) {
			int X = x + i[0];
			int Y = y + i[1];
			int Z = z + i[2];
			if (world.getBlock(X, Y, Z) != null) {
				if (world.getBlock(X, Y, Z) instanceof BlockEcoBlock) {
					if (((BlockEcoBlock) world.getBlock(X, Y, Z)).getEco(worldObj, X, Y, Z) == currentEco)
						continue;
				} else {
					allow = false;
				}
			} else {
				allow = false;
			}
		}
		this.currentEco = currentEco;
		this.activated = allow;
		return allow;
	}

	public EnumEco getCurrentEco(World world, int x, int y, int z) {
		EnumEco currentEco = null;
		if (world.getBlock(x + 1, y, z) != null && world.getBlock(x + 1, y, z) instanceof IEcoBlock) {
			currentEco = ((IEcoBlock) world.getBlock(x + 1, y, z)).getEco(worldObj, x + 1, y, z);
			if (world.getBlock(x - 1, y, z) != null && world.getBlock(x - 1, y, z) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x - 1, y, z)).getEco(worldObj, x - 1, y, z) == currentEco) {
				if (world.getBlock(x, y, z + 1) != null && world.getBlock(x, y, z + 1) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x, y, z + 1)).getEco(worldObj, x, y, z + 1) == currentEco) {
					if (world.getBlock(x, y, z - 1) != null && world.getBlock(x, y, z - 1) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x, y, z - 1)).getEco(worldObj, x, y, z - 1) == currentEco) {
						if (world.getBlock(x + 1, y, z + 1) != null && world.getBlock(x + 1, y, z + 1) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x + 1, y, z + 1)).getEco(worldObj, x + 1, y, z + 1) == currentEco) {
							if (world.getBlock(x + 1, y, z - 1) != null && world.getBlock(x + 1, y, z - 1) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x + 1, y, z - 1)).getEco(worldObj, x + 1, y, z - 1) == currentEco) {
								if (world.getBlock(x - 1, y, z - 1) != null && world.getBlock(x - 1, y, z - 1) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x - 1, y, z - 1)).getEco(worldObj, x - 1, y, z - 1) == currentEco) {
									if (world.getBlock(x - 1, y, z + 1) != null && world.getBlock(x - 1, y, z + 1) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x - 1, y, z + 1)).getEco(worldObj, x - 1, y, z + 1) == currentEco) {
										if (world.getBlock(x, y - 1, z) != null && world.getBlock(x, y - 1, z) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x, y - 1, z)).getEco(worldObj, x, y - 1, z) == currentEco) {
											if (world.getBlock(x, y - 2, z) != null && world.getBlock(x, y - 2, z) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x, y - 2, z)).getEco(worldObj, x, y - 2, z) == currentEco) {
												if (world.getBlock(x, y - 3, z) != null && world.getBlock(x, y - 3, z) instanceof IEcoBlock && ((IEcoBlock) world.getBlock(x, y - 3, z)).getEco(worldObj, x, y - 3, z) == currentEco) {
													return currentEco;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

}
