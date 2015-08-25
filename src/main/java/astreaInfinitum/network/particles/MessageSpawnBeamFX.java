package astreaInfinitum.network.particles;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import astreaInfinitum.client.particle.EntityEcoBeamFX;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSpawnBeamFX implements IMessage, IMessageHandler<MessageSpawnBeamFX, IMessage> {

	public double x;
	public double y;
	public double z;
	public double targetX;
	public double targetY;
	public double targetZ;
	public int age;
	public float red;
	public float green;
	public float blue;

	public MessageSpawnBeamFX() {

	}

	public MessageSpawnBeamFX(double x, double y, double z, double targetX, double targetY, double targetZ, int age, float red, float green, float blue) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
		this.age = age;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();

		targetX = buf.readDouble();
		targetY = buf.readDouble();
		targetZ = buf.readDouble();
		age = buf.readInt();

		red = buf.readFloat();
		green = buf.readFloat();
		blue = buf.readFloat();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeDouble(targetX);
		buf.writeDouble(targetY);
		buf.writeDouble(targetZ);
		buf.writeInt(age);

		buf.writeFloat(red);
		buf.writeFloat(green);
		buf.writeFloat(blue);

	}

	@Override
	public IMessage onMessage(MessageSpawnBeamFX message, MessageContext ctx) {

		Minecraft minecraft = FMLClientHandler.instance().getClient();
		minecraft.effectRenderer.addEffect(new EntityEcoBeamFX(minecraft.theWorld, message.x, message.y, message.z, message.targetX, message.targetY, message.targetZ, message.red, message.green, message.blue, message.age));

		return null;
	}

}
