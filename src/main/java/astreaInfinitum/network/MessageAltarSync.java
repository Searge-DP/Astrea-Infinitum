package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.TileEntityEcoAltar;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageAltarSync implements IMessage, IMessageHandler<MessageAltarSync, IMessage> {

	public boolean activated;
	public boolean shouldConvert;
	public int convertTime;
	public boolean converted;
	public int x;
	public int y;
	public int z;

	public MessageAltarSync() {

	}

	public MessageAltarSync(boolean activated, boolean shouldConvert, int convertTime, boolean converted, int x, int y, int z) {
		this.activated = activated;
		this.shouldConvert = shouldConvert;
		this.convertTime = convertTime;
		this.converted = converted;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageAltarSync(TileEntityEcoAltar tile) {
		this(tile.activated, tile.shouldConvert, tile.convertTime, tile.converted, tile.xCoord, tile.yCoord, tile.zCoord);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		activated = buf.readBoolean();
		shouldConvert = buf.readBoolean();
		converted = buf.readBoolean();
		convertTime = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(activated);
		buf.writeBoolean(shouldConvert);
		buf.writeBoolean(converted);
		buf.writeInt(convertTime);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public IMessage onMessage(MessageAltarSync message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityEcoAltar) {

			((TileEntityEcoAltar) tileEntity).activated = message.activated;
			((TileEntityEcoAltar) tileEntity).shouldConvert = message.shouldConvert;
			((TileEntityEcoAltar) tileEntity).converted = message.converted;
			((TileEntityEcoAltar) tileEntity).convertTime = message.convertTime;

		}

		return null;
	}

}
