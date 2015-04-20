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
	public int manaLight;
	public int manaDark;
	public int level;
	public int xp;
	public int maxXP;
	public int maxManaLight;
	public int maxManaDark;

	public MessagePlayerSync() {

	}

	public MessagePlayerSync(boolean knowledge, int manaLight, int manaDark, int level, int xp, int maxXP, int maxManaLight, int maxManaDark) {
		this.knowledge = knowledge;
		this.manaLight = manaLight;
		this.manaDark = manaDark;
		this.level = level;
		this.xp = xp;
		this.maxXP = maxXP;
		this.maxManaLight = maxManaLight;
		this.maxManaDark = maxManaDark;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		knowledge = buf.readBoolean();
		manaLight = buf.readInt();
		manaDark = buf.readInt();
		level = buf.readInt();
		xp = buf.readInt();
		maxXP = buf.readInt();
		maxManaLight = buf.readInt();
		maxManaDark = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(knowledge);
		buf.writeInt(manaLight);
		buf.writeInt(manaDark);
		buf.writeInt(level);
		buf.writeInt(xp);
		buf.writeInt(maxXP);
		buf.writeInt(maxManaLight);
		buf.writeInt(maxManaDark);

	}

	@Override
	public IMessage onMessage(MessagePlayerSync message, MessageContext ctx) {
		ClientUtils.updatePlayerInformation(message);
		return null;
	}
}
