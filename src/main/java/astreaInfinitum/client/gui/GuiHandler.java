package astreaInfinitum.client.gui;

import astreaInfinitum.client.gui.spell.ContainerSpell;
import astreaInfinitum.client.gui.spell.GuiSpell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {
		case 0:
			return new ContainerSpell(player.inventory);

		}

		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity te = world.getTileEntity(x, y, z);

		switch (ID) {
		case 0:
			return new GuiSpell(player.inventory);

		}

		return null;

	}

}
