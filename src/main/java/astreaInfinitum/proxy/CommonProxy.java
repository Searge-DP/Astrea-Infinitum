package astreaInfinitum.proxy;

import astreaInfinitum.utils.ClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	
	

	public EntityPlayer getClientPlayer() {
		return null;
	}
	
	public void readBookBasic(){
		
	}
	public void readTablet(){
		
	}
	
	public void renderSpell(){
		
	}
	public World getClientWorld(){
		return null;
	}
	
	public Minecraft getMinecraft(){
		return null;
	}


	public void registerClientHandler(){
		MinecraftForge.EVENT_BUS.register(new ClientHandler());
	}
	
	public void registerRenderers(){
		
	}
}
