package astreaInfinitum.network.sync;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageRuneCarverSync implements IMessage, IMessageHandler<MessageRuneCarverSync, IMessage> {

	public ItemStack item;
	public int x;
	public int y;
	public int z;

	public MessageRuneCarverSync() {

	}

	public MessageRuneCarverSync(ItemStack item, int x, int y, int z) {
		this.item = item;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageRuneCarverSync(TileEntityRuneCarver tile) {
		this(tile.getStackInSlot(1), tile.xCoord, tile.yCoord, tile.zCoord);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		item = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeItemStack(buf, item);
	}

	@Override
	public IMessage onMessage(MessageRuneCarverSync message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityRuneCarver) {
			((TileEntityRuneCarver) tileEntity).setInventorySlotContents(1, message.item);
		}

		return null;
	}

}
