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

public class MessagePlayerSync implements IMessage, IMessageHandler<MessagePlayerSync, IMessage> {

	public boolean knowledge;
	public int ecoLight;
	public int ecoDark;
	public int level;
	public int xp;
	public int maxXP;
	public int maxEcoLight;
	public int maxEcoDark;

	public MessagePlayerSync() {

	}

	public MessagePlayerSync(boolean knowledge, int ecoLight, int ecoDark, int level, int xp, int maxXP, int maxEcoLight, int maxEcoDark) {
		this.knowledge = knowledge;
		this.ecoLight = ecoLight;
		this.ecoDark = ecoDark;
		this.level = level;
		this.xp = xp;
		this.maxXP = maxXP;
		this.maxEcoLight = maxEcoLight;
		this.maxEcoDark = maxEcoDark;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		knowledge = buf.readBoolean();
		ecoLight = buf.readInt();
		ecoDark = buf.readInt();
		level = buf.readInt();
		xp = buf.readInt();
		maxXP = buf.readInt();
		maxEcoLight = buf.readInt();
		maxEcoDark = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(knowledge);
		buf.writeInt(ecoLight);
		buf.writeInt(ecoDark);
		buf.writeInt(level);
		buf.writeInt(xp);
		buf.writeInt(maxXP);
		buf.writeInt(maxEcoLight);
		buf.writeInt(maxEcoDark);

	}

	@Override
	public IMessage onMessage(MessagePlayerSync message, MessageContext ctx) {
		ClientUtils.updatePlayerInformation(message);
		return null;
	}
}
