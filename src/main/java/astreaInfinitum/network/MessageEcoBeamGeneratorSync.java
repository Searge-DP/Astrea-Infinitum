package astreaInfinitum.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;
import astreaInfinitum.tileEntities.eco.TileEntityEcoCutter;
import astreaInfinitum.tileEntities.eco.TileEntityEcoInfuser;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEcoBeamGeneratorSync implements IMessage, IMessageHandler<MessageEcoBeamGeneratorSync, IMessage> {

	public ItemStack item;
	public int x;
	public int y;
	public int z;

	public int cutterX;
	public int cutterY;
	public int cutterZ;

	public boolean shouldSend;

	public MessageEcoBeamGeneratorSync() {

	}

	public MessageEcoBeamGeneratorSync(ItemStack item, int x, int y, int z, int cutterX, int cutterY, int cutterZ, boolean shouldSend) {
		this.item = item;
		this.x = x;
		this.y = y;
		this.z = z;
		this.cutterX = cutterX;
		this.cutterY = cutterY;
		this.cutterZ = cutterZ;
		this.shouldSend = shouldSend;

	}

	public MessageEcoBeamGeneratorSync(TileEntityEcoBeamGenerator tile) {
		this(tile.getStackInSlot(0), tile.xCoord, tile.yCoord, tile.zCoord, tile.cutter.xCoord, tile.cutter.yCoord, tile.cutter.zCoord, tile.shouldSendEco);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		item = ByteBufUtils.readItemStack(buf);

		cutterX = buf.readInt();
		cutterY = buf.readInt();
		cutterZ = buf.readInt();

		shouldSend = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeItemStack(buf, item);
		buf.writeInt(cutterX);
		buf.writeInt(cutterY);
		buf.writeInt(cutterZ);
		buf.writeBoolean(shouldSend);
	}

	@Override
	public IMessage onMessage(MessageEcoBeamGeneratorSync message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		TileEntityEcoCutter cutter = (TileEntityEcoCutter) FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.cutterX, message.cutterY, message.cutterZ);
		if (tileEntity instanceof TileEntityEcoBeamGenerator) {
			((TileEntityEcoBeamGenerator) tileEntity).setInventorySlotContents(0, message.item);
			((TileEntityEcoBeamGenerator) tileEntity).shouldSendEco = message.shouldSend;
			((TileEntityEcoBeamGenerator) tileEntity).cutterX= message.cutterX;
			((TileEntityEcoBeamGenerator) tileEntity).cutterY= message.cutterY;
			((TileEntityEcoBeamGenerator) tileEntity).cutterZ= message.cutterZ;
			if (cutter != null) {
				((TileEntityEcoBeamGenerator) tileEntity).cutter = cutter;

			}

		}

		return null;
	}

}
