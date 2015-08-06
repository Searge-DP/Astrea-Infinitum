package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageParticles implements IMessage, IMessageHandler<MessageParticles, IMessage> {

	public String name;
	public double x;
	public double y;
	public double z;
	public double extra1;
	public double extra2;
	public double extra3;
	public int repeat;

	public MessageParticles() {

	}

	public MessageParticles(String name, double x, double y, double z, double extra1, double extra2, double extra3, int repeat) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.extra1 = extra1;
		this.extra2 = extra2;
		this.extra3 = extra3;
		this.repeat = repeat;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		name = ByteBufUtils.readUTF8String(buf);
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		extra1 = buf.readDouble();
		extra2 = buf.readDouble();
		extra3 = buf.readDouble();
		repeat = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, name);
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeDouble(extra1);
		buf.writeDouble(extra2);
		buf.writeDouble(extra3);
		buf.writeInt(repeat);
	}

	@Override
	public IMessage onMessage(MessageParticles message, MessageContext ctx) {

		for (int i = 0; i < message.repeat; i++) {
			FMLClientHandler.instance().getClient().theWorld.spawnParticle(message.name, message.x + FMLClientHandler.instance().getClient().theWorld.rand.nextDouble(), message.y + 0.5 + FMLClientHandler.instance().getClient().theWorld.rand.nextDouble(), message.z + FMLClientHandler.instance().getClient().theWorld.rand.nextDouble(), message.extra1, message.extra2 * FMLClientHandler.instance().getClient().theWorld.rand.nextDouble(), message.extra3);
		}
		return null;
	}
}
