package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.TileEntityPedestal;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessagePedestalSync implements IMessage, IMessageHandler<MessagePedestalSync, IMessage> {

	public ItemStack item;
	public float angle;
	public int x;
	public int y;
	public int z;

	public MessagePedestalSync() {

	}

	public MessagePedestalSync(ItemStack item, float angle, int x, int y, int z) {
		this.item = item;
		this.angle = angle;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessagePedestalSync(TileEntityPedestal tile) {
		this(tile.items[0], tile.angle, tile.xCoord, tile.yCoord, tile.zCoord);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		angle = buf.readFloat();
		item = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeFloat(angle);
		ByteBufUtils.writeItemStack(buf, item);
	}

	@Override
	public IMessage onMessage(MessagePedestalSync message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityPedestal) {
			((TileEntityPedestal) tileEntity).angle = message.angle;
			((TileEntityPedestal)tileEntity).setInventorySlotContents(0, item);
		}
		
		return null;
	}

}
