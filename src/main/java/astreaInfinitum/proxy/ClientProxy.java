package astreaInfinitum.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import astreaInfinitum.client.gui.GuiBookBasic;
import astreaInfinitum.client.gui.GuiKnowledgeTablet;
import astreaInfinitum.client.render.RenderSpell;
import astreaInfinitum.entities.EntitySpell;

public class ClientProxy extends CommonProxy {

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void readBookBasic() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiBookBasic());
	}

	@Override
	public void readTablet() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiKnowledgeTablet());
	}

	@Override
	public void renderSpell() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySpell.class, new RenderSpell());
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	@Override
	public Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}
}
