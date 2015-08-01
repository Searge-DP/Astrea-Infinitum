package astreaInfinitum.blocks.eco.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;

public class BlockEcoOre extends Block {

	public String[] names = new String[] { "Red", "Green", "Blue", "Yellow", "Light", "Dark" };
	public IIcon[] icons = new IIcon[names.length];

	public BlockEcoOre() {
		super(Material.rock);

		setHardness(1.0f);
		setHarvestLevel("pickaxe", 2);
	}

	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		return super.getDrops(world, x, y, z, metadata, fortune);
	}
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		for (int i = 0; i < names.length; i++) {
			icons[i] = icon.registerIcon(ModProps.modid + ":ecoOre" + names[i]);
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z));
	}
}
