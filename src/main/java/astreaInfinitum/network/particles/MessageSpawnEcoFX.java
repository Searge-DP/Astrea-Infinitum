package astreaInfinitum.network.particles;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import astreaInfinitum.client.particle.EntityEcoFX;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSpawnEcoFX implements IMessage, IMessageHandler<MessageSpawnEcoFX, IMessage> {

	public double x;
	public double y;
	public double z;
	public float red;
	public float green;
	public float blue;
	public double motionY;

	public MessageSpawnEcoFX() {

	}

	public MessageSpawnEcoFX(double x, double y, double z, float red, float green, float blue, double motionY) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.motionY = motionY;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();

		red = buf.readFloat();
		green = buf.readFloat();
		blue = buf.readFloat();
		motionY = buf.readDouble();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);

		buf.writeFloat(red);
		buf.writeFloat(green);
		buf.writeFloat(blue);
		buf.writeDouble(motionY);

	}

	@Override
	public IMessage onMessage(MessageSpawnEcoFX message, MessageContext ctx) {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		minecraft.effectRenderer.addEffect(new EntityEcoFX(minecraft.theWorld, message.x, message.y, message.z, message.motionY, message.red, message.green, message.blue));

		return null;
	}

}
