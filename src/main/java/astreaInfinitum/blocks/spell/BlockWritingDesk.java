package astreaInfinitum.blocks.spell;

import astreaInfinitum.AstreaInfinitum;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockWritingDesk extends Block {

	public BlockWritingDesk() {
		super(Material.rock);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9) {
		if (!world.isRemote) {
			player.openGui(AstreaInfinitum.INSTANCE, 0, world, x, y, z);
		}
		return true;
	}
}
