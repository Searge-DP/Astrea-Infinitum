package astreaInfinitum.network;

import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.utils.AIUtils;
import astreaInfinitum.utils.ClientUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageItemSync implements IMessage, IMessageHandler<MessageItemSync, IMessage> {

	public int xp;
	public int xpMax;
	public int level;

	public MessageItemSync() {

	}

	public MessageItemSync(int xp, int xpMax, int level) {
		this.xp = xp;
		this.xpMax = xpMax;
		this.level = level;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		level = buf.readInt();
		xp = buf.readInt();
		xpMax = buf.readInt();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(level);
		buf.writeInt(xp);
		buf.writeInt(xpMax);
		
		
	}

	@Override
	public IMessage onMessage(MessageItemSync message, MessageContext ctx) {
		ClientUtils.updateItemInformation(message);
		return null;
	}
}
