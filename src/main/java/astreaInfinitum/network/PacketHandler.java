package astreaInfinitum.network;

import astreaInfinitum.ModProps;
import astreaInfinitum.network.particles.MessageSpawnBeamFX;
import astreaInfinitum.network.particles.MessageSpawnEcoFX;
import astreaInfinitum.network.sync.MessageEcoAltarSync;
import astreaInfinitum.network.sync.MessageItemStackNBTSync;
import astreaInfinitum.network.sync.MessageRuneCarverGUISync;
import astreaInfinitum.network.sync.MessageRuneCarverServerSync;
import astreaInfinitum.network.sync.MessageRuneCarverSync;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModProps.modid);
	private static int id = 0;

	public static void init() {
		INSTANCE.registerMessage(MessagePlayerSync.class, MessagePlayerSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageItemSync.class, MessageItemSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEcoAltarSync.class, MessageEcoAltarSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageParticles.class, MessageParticles.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessagePedestalSync.class, MessagePedestalSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEcoBeamGeneratorSync.class, MessageEcoBeamGeneratorSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEntitySpawnSync.class, MessageEntitySpawnSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageSpawnBeamFX.class, MessageSpawnBeamFX.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageSpawnEcoFX.class, MessageSpawnEcoFX.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEcoBlockSync.class, MessageEcoBlockSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEcoVentSync.class, MessageEcoVentSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageEcoAltarBlockSync.class, MessageEcoAltarBlockSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageRuneCarverServerSync.class, MessageRuneCarverServerSync.class, id++, Side.SERVER);
		INSTANCE.registerMessage(MessageRuneCarverGUISync.class, MessageRuneCarverGUISync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageItemStackNBTSync.class, MessageItemStackNBTSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageRuneCarverSync.class, MessageRuneCarverSync.class, id++, Side.CLIENT);

	}

}
