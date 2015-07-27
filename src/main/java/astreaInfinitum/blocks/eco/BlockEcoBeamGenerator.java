package astreaInfinitum.blocks.eco;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import astreaInfinitum.tileEntities.eco.TileEntityEcoBeamGenerator;

public class BlockEcoBeamGenerator extends Block implements ITileEntityProvider {

	public BlockEcoBeamGenerator() {
		super(Material.anvil);
		setHardness(0.5f);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEcoBeamGenerator();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (player.inventory.getCurrentItem().getItem() != null) {
			if (player.inventory.getCurrentItem().getItem() == Items.stick) {
				TileEntityEcoBeamGenerator test = (TileEntityEcoBeamGenerator) world.getTileEntity(x, y, z);
				test.setType(0);
				return true;
			}
			if (player.inventory.getCurrentItem().getItem() == Items.arrow) {
				TileEntityEcoBeamGenerator test = (TileEntityEcoBeamGenerator) world.getTileEntity(x, y, z);
				test.setType(1);
				return true;
			}
			if (player.inventory.getCurrentItem().getItem() == Items.apple) {
				TileEntityEcoBeamGenerator test = (TileEntityEcoBeamGenerator) world.getTileEntity(x, y, z);
				test.setType(2);
				return true;
			}
			if (player.inventory.getCurrentItem().getItem() == Items.beef) {
				TileEntityEcoBeamGenerator test = (TileEntityEcoBeamGenerator) world.getTileEntity(x, y, z);
				test.setType(3);
				return true;
			}
		}

		return false;
	}
}
