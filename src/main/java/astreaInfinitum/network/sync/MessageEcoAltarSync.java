package astreaInfinitum.network.sync;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.eco.TileEntityEcoAltar;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEcoAltarSync implements IMessage, IMessageHandler<MessageEcoAltarSync, IMessage> {

	public boolean activated;
	public int x;
	public int y;
	public int z;

	public MessageEcoAltarSync() {

	}

	public MessageEcoAltarSync(boolean activated, int x, int y, int z) {
		this.activated = activated;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageEcoAltarSync(TileEntityEcoAltar tile) {
		this(tile.activated, tile.xCoord, tile.yCoord, tile.zCoord);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		activated = buf.readBoolean();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(activated);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public IMessage onMessage(MessageEcoAltarSync message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityEcoAltar) {
			((TileEntityEcoAltar) tileEntity).activated = message.activated;
		}
		return null;
	}

}
