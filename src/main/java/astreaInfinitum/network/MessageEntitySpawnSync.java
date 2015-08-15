package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEntitySpawnSync implements IMessage, IMessageHandler<MessageEntitySpawnSync, IMessage> {

	public NBTTagCompound entity;

	public MessageEntitySpawnSync() {
	}

	public MessageEntitySpawnSync(NBTTagCompound entity) {
		this.entity = entity;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entity = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, entity);
	}

	@Override
	public IMessage onMessage(MessageEntitySpawnSync message, MessageContext ctx) {
		World world = FMLClientHandler.instance().getClient().theWorld;
		world.spawnEntityInWorld(EntityList.createEntityFromNBT(message.entity, world));
		return null;
	}
}
