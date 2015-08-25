package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.api.eco.EnumEco;
import astreaInfinitum.tileEntities.eco.TileEntityEcoVent;
import astreaInfinitum.utils.BlockPos;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEcoVentSync implements IMessage, IMessageHandler<MessageEcoVentSync, IMessage> {

	public int x;
	public int y;
	public int z;
	public BlockPos currentBreakPos;
	public int defaultBreakTime;
	public int breakTime;
	public boolean converted;
	public List<BlockPos> blockToBreak = new ArrayList<BlockPos>();
	public EnumEco currentEco;
	public double currentEcoCount;

	public boolean activated;

	public int blockToBreakSize;

	public MessageEcoVentSync() {

	}

	public MessageEcoVentSync(int x, int y, int z, BlockPos currentBreakPos, int defaultBreakTime, int breakTime, boolean converted, List<BlockPos> blockToBreak, EnumEco currentEco, double currentEcoCount, boolean activated) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.currentBreakPos = currentBreakPos;
		this.defaultBreakTime = defaultBreakTime;
		this.breakTime = breakTime;
		this.converted = converted;
		this.blockToBreak = blockToBreak;
		this.currentEco = currentEco;
		this.currentEcoCount = currentEcoCount;
		this.activated = activated;
		this.blockToBreakSize = blockToBreak.size();
	}

	public MessageEcoVentSync(TileEntityEcoVent tile) {
		this(tile.xCoord, tile.yCoord, tile.zCoord, tile.currentBreakPos, tile.defaultBreakTime, tile.breakTime, tile.converted, tile.blockToBreak, tile.currentEco, tile.currentEcoCount, tile.activated);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		currentBreakPos = BlockPos.getBlockFromNbt(ByteBufUtils.readTag(buf));
		defaultBreakTime = buf.readInt();
		breakTime = buf.readInt();
		converted = buf.readBoolean();
		currentEco = null;
		String eco = ByteBufUtils.readUTF8String(buf);
		if (!eco.equalsIgnoreCase("none")) {
			currentEco = EnumEco.valueOf(eco);
		}
		currentEcoCount = buf.readDouble();
		activated = buf.readBoolean();
		blockToBreakSize = buf.readInt();
		for (int i = 0; i < blockToBreakSize; i++) {
			blockToBreak.add(BlockPos.getBlockFromNbt(ByteBufUtils.readTag(buf)));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		NBTTagCompound current = new NBTTagCompound();
		if (currentBreakPos != null) {
			currentBreakPos.writeToNbt(current);
		}
		ByteBufUtils.writeTag(buf, current);
		buf.writeInt(defaultBreakTime);
		buf.writeInt(breakTime);
		buf.writeBoolean(converted);

		if (currentEco != null) {
			ByteBufUtils.writeUTF8String(buf, currentEco.name());
		} else {
			ByteBufUtils.writeUTF8String(buf, "none");
		}
		buf.writeDouble(currentEcoCount);
		buf.writeBoolean(activated);
		buf.writeInt(blockToBreakSize);
		NBTTagCompound tag = new NBTTagCompound();
		for (int i = 0; i < blockToBreakSize; i++) {
			tag = new NBTTagCompound();
			blockToBreak.get(i).writeToNbt(tag);
			ByteBufUtils.writeTag(buf, tag);
		}
	}

	@Override
	public IMessage onMessage(MessageEcoVentSync message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityEcoVent) {
			TileEntityEcoVent tile = (TileEntityEcoVent) tileEntity;

			tile.currentBreakPos = message.currentBreakPos;
			tile.defaultBreakTime = message.defaultBreakTime;
			tile.breakTime = message.breakTime;
			tile.converted = message.converted;
			tile.blockToBreak = message.blockToBreak;
			tile.currentEco = message.currentEco;
			tile.currentEcoCount = message.currentEcoCount;
			tile.activated = message.activated;

		}

		return null;
	}

}
