package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.TileEntityPedestal;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBlock;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEcoBlockSync implements IMessage, IMessageHandler<MessageEcoBlockSync, IMessage> {

	public int x;
	public int y;
	public int z;
	public boolean visible;

	public MessageEcoBlockSync() {

	}

	public MessageEcoBlockSync(int x, int y, int z, boolean visible) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.visible = visible;
	}

	public MessageEcoBlockSync(TileEntityEcoBlock tile) {
		this(tile.xCoord, tile.yCoord, tile.zCoord, tile.visible);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		visible = buf.readBoolean();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(visible);
	}

	@Override
	public IMessage onMessage(MessageEcoBlockSync message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityEcoBlock) {
			((TileEntityEcoBlock) tileEntity).visible = message.visible;
		}

		return null;
	}

}
