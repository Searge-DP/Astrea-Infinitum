package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;
import astreaInfinitum.tileEntities.eco.TileEntityEcoInfuser;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEcoBeamGeneratorSync implements IMessage, IMessageHandler<MessageEcoBeamGeneratorSync, IMessage> {

	public ItemStack item;
	public int rotation;
	public int color;
	public int x;
	public int y;
	public int z;

	public int infuserX;
	public int infuserY;
	public int infuserZ;

	public boolean shouldSend;

	public MessageEcoBeamGeneratorSync() {

	}

	public MessageEcoBeamGeneratorSync(ItemStack item, int rotation, int color, int x, int y, int z, int infuserX, int infuserY, int infuserZ, boolean shouldSend) {
		this.item = item;
		this.color = color;
		this.rotation = rotation;
		this.x = x;
		this.y = y;
		this.z = z;
		this.infuserX = infuserX;
		this.infuserY = infuserY;
		this.infuserZ = infuserZ;
		this.shouldSend = shouldSend;

	}

	public MessageEcoBeamGeneratorSync(TileEntityEcoBeamGenerator tile) {
		this(tile.getStackInSlot(0), tile.rotation, tile.color, tile.xCoord, tile.yCoord, tile.zCoord, tile.infuser.xCoord, tile.infuser.yCoord, tile.infuser.zCoord, tile.shouldSendEco);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		rotation = buf.readInt();
		color = buf.readInt();
		item = ByteBufUtils.readItemStack(buf);

		infuserX = buf.readInt();
		infuserY = buf.readInt();
		infuserZ = buf.readInt();

		shouldSend = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(rotation);
		buf.writeInt(color);
		ByteBufUtils.writeItemStack(buf, item);
		buf.writeInt(infuserX);
		buf.writeInt(infuserY);
		buf.writeInt(infuserZ);
		buf.writeBoolean(shouldSend);
	}

	@Override
	public IMessage onMessage(MessageEcoBeamGeneratorSync message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		TileEntityEcoInfuser infuser = (TileEntityEcoInfuser) FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.infuserX, message.infuserY, message.infuserZ);
		if (tileEntity instanceof TileEntityEcoBeamGenerator) {
			((TileEntityEcoBeamGenerator) tileEntity).rotation = message.rotation;
			((TileEntityEcoBeamGenerator) tileEntity).color = message.color;
			((TileEntityEcoBeamGenerator) tileEntity).setInventorySlotContents(0, message.item);
			((TileEntityEcoBeamGenerator) tileEntity).shouldSendEco =  message.shouldSend;
			if (infuser != null) {
				((TileEntityEcoBeamGenerator) tileEntity).infuser = infuser;
								
			}

		}

		return null;
	}

}
