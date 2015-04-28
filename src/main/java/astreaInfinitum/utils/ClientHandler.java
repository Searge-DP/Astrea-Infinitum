package astreaInfinitum.utils;

import jdk.nashorn.internal.objects.annotations.Getter;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class ClientHandler {
	public static int ticks;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		ticks += 1;
	}
}
