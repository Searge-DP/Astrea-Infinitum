package astreaInfinitum.network.sync;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;

import org.apache.commons.lang3.tuple.MutablePair;

import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCore.util.CoordinatePair;

public class MessageRuneCarverServerSync implements IMessage, IMessageHandler<MessageRuneCarverServerSync, IMessage> {

	private List<CoordinatePair> nodes = new ArrayList<CoordinatePair>();
	private List<MutablePair<CoordinatePair, CoordinatePair>> lines = new ArrayList<MutablePair<CoordinatePair, CoordinatePair>>();
	private CoordinatePair prevNode;
	public int x;
	public int y;
	public int z;

	public MessageRuneCarverServerSync() {

	}

	/**
	 * @param nodes
	 * @param lines
	 * @param prevNode
	 * @param x
	 * @param y
	 * @param z
	 */
	public MessageRuneCarverServerSync(List<CoordinatePair> nodes, List<MutablePair<CoordinatePair, CoordinatePair>> lines, CoordinatePair prevNode, int x, int y, int z) {
		this.nodes = nodes;
		this.lines = lines;
		this.prevNode = prevNode;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageRuneCarverServerSync(TileEntityRuneCarver tile) {
		this(tile.nodes, tile.lines, tile.prevNode, tile.xCoord, tile.yCoord, tile.zCoord);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		int nodesSize = buf.readInt();
		for (int i = 0; i < nodesSize; i++) {
			this.nodes.add(CoordinatePair.readFromByteBuf(buf));
		}

		int linesSize = buf.readInt();
		for (int i = 0; i < linesSize; i++) {
			this.lines.add(new MutablePair<CoordinatePair, CoordinatePair>(CoordinatePair.readFromByteBuf(buf), CoordinatePair.readFromByteBuf(buf)));
		}
		if (buf.readBoolean())
			prevNode = CoordinatePair.readFromByteBuf(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(this.nodes.size());
		for (CoordinatePair co : this.nodes) {
			co.writeToByteBuf(buf);
		}
		buf.writeInt(this.lines.size());
		for (MutablePair<CoordinatePair, CoordinatePair> co : this.lines) {
			co.left.writeToByteBuf(buf);
			co.right.writeToByteBuf(buf);
		}
		buf.writeBoolean(prevNode != null);
		if (prevNode != null)
			prevNode.writeToByteBuf(buf);
	}

	@Override
	public IMessage onMessage(MessageRuneCarverServerSync message, MessageContext ctx) {
		TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityRuneCarver) {
			((TileEntityRuneCarver) tileEntity).prevNode = message.prevNode;
			((TileEntityRuneCarver) tileEntity).lines = message.lines;
			((TileEntityRuneCarver) tileEntity).nodes = message.nodes;
		}
		return null;
	}

}
