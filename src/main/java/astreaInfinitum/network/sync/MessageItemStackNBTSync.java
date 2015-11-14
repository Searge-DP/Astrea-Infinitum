package astreaInfinitum.network.sync;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageItemStackNBTSync implements IMessage, IMessageHandler<MessageItemStackNBTSync, IMessage> {

	public ItemStack stack;
	public NBTTagCompound tag;
	public int x, y, z;

	public MessageItemStackNBTSync(ItemStack stack, NBTTagCompound tag, int x, int y, int z) {
		this.stack = stack;
		this.x = x;
		this.y = y;
		this.z = z;
		this.tag = tag;
	}

	public MessageItemStackNBTSync() {

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		stack = ByteBufUtils.readItemStack(buf);
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		tag = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, stack);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeTag(buf, tag);
	}

	@Override
	public IMessage onMessage(MessageItemStackNBTSync message, MessageContext ctx) {
		TileEntity tileEntity = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityRuneCarver) {
			TileEntityRuneCarver tile = (TileEntityRuneCarver) tileEntity;
			if (tile.getStackInSlot(1) != null)
				tile.getStackInSlot(1).stackTagCompound = message.stack.stackTagCompound;
		}
		return null;
	}

}
