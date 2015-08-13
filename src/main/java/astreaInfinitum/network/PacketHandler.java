package astreaInfinitum.network;

import astreaInfinitum.ModProps;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModProps.modid);
	private static int id = 0;

	public static void init() {
		INSTANCE.registerMessage(MessagePlayerSync.class, MessagePlayerSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageItemSync.class, MessageItemSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageAltarSync.class, MessageAltarSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageParticles.class, MessageParticles.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessagePedestalSync.class, MessagePedestalSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEcoBeamGeneratorSync.class, MessageEcoBeamGeneratorSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEntitySpawnSync.class, MessageEntitySpawnSync.class, id++, Side.CLIENT);


	}

}
