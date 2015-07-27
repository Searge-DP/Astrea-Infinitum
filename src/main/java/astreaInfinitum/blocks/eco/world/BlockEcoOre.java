package astreaInfinitum.blocks.eco.world;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;

public class BlockEcoOre extends Block {

	public BlockEcoOre() {
		super(Material.rock);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 6; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	public IIcon overlay, underlay;

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		overlay = icon.registerIcon(ModProps.MODID + ":ecoOreOverlay");
		underlay = icon.registerIcon(ModProps.MODID + ":ecoOreUnderlay");
		this.blockIcon = underlay;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return AstreaInfinitum.oreID;
	}
}
