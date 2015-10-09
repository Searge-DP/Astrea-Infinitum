package astreaInfinitum.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.client.gui.runeCarver.ContainerRuneCarver;
import astreaInfinitum.client.gui.runeCarver.GuiRuneCarver;
import astreaInfinitum.client.gui.spell.ContainerSpell;
import astreaInfinitum.client.gui.spell.GuiSpell;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import cpw.mods.fml.common.network.IGuiHandler;

@SuppressWarnings("unused")
public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {
			case 0:
				return new ContainerSpell(player.inventory);
			case 1:
				return new ContainerRuneCarver(player.inventory, (TileEntityRuneCarver) te);
		}

		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity te = world.getTileEntity(x, y, z);

		switch (ID) {
			case 0:
				return new GuiSpell(player.inventory);
			case 1:
				return new GuiRuneCarver(player.inventory, (TileEntityRuneCarver) te);
		}

		return null;

	}

}
