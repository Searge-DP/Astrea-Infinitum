package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import astreaInfinitum.api.runes.EnumSpellType;
import astreaInfinitum.utils.ClientUtils;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageItemSync implements IMessage, IMessageHandler<MessageItemSync, IMessage> {

	public int xp;
	public int xpMax;
	public int level;
	public EnumSpellType type;

	public MessageItemSync() {

	}

	public MessageItemSync(int xp, int xpMax, int level, EnumSpellType type) {
		this.xp = xp;
		this.xpMax = xpMax;
		this.level = level;
		this.type = type;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		level = buf.readInt();
		xp = buf.readInt();
		xpMax = buf.readInt();
		type = EnumSpellType.valueOf(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(level);
		buf.writeInt(xp);
		buf.writeInt(xpMax);
		ByteBufUtils.writeUTF8String(buf, type.name());

	}

	@Override
	public IMessage onMessage(MessageItemSync message, MessageContext ctx) {
		ClientUtils.updateItemInformation(message);
		return null;
	}
}
