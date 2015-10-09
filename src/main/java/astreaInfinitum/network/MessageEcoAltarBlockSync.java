package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.eco.TileEntityEcoAltarBlock;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageEcoAltarBlockSync implements IMessage, IMessageHandler<MessageEcoAltarBlockSync, IMessage> {

	public int x;
	public int y;
	public int z;

	public boolean shouldTick;

	public MessageEcoAltarBlockSync() {

	}

	public MessageEcoAltarBlockSync(int x, int y, int z, boolean shouldTick) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.shouldTick = shouldTick;
	}

	public MessageEcoAltarBlockSync(TileEntityEcoAltarBlock tile) {
		this(tile.xCoord, tile.yCoord, tile.zCoord, tile.shouldTick);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		shouldTick = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(shouldTick);
	}

	@Override
	public IMessage onMessage(MessageEcoAltarBlockSync message, MessageContext ctx) {
		TileEntity tileEntity = null;
		if (ctx.side == Side.CLIENT) {
			tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		}
		if (ctx.side == Side.SERVER) {
			tileEntity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getTileEntity(message.x, message.y, message.z);
		}

		if (tileEntity instanceof TileEntityEcoAltarBlock) {
			((TileEntityEcoAltarBlock) tileEntity).shouldTick = message.shouldTick;
		}

		return null;
	}

}
