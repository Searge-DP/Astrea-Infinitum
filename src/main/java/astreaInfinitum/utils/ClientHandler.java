package astreaInfinitum.utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientHandler {
	public static int ticks;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		ticks += 1;
	}
}
