package astreaInfinitum.blocks.eco.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.eco.EnumEco;
import astreaInfinitum.api.eco.IEcoBlock;
import astreaInfinitum.items.AIItems;

public class BlockEcoOre extends Block implements IEcoBlock {

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
		drops.add(new ItemStack(AIItems.ecoDust, 2 + world.rand.nextInt(4)));
		drops.add(new ItemStack(AIItems.ecoOrb, 3+world.rand.nextInt(4), metadata));
		return drops;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	@Override
	public EnumEco getEco(World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		switch (metadata) {
		case 0:
			return EnumEco.red;
		case 1:
			return EnumEco.green;
		case 2:
			return EnumEco.blue;
		case 3:
			return EnumEco.yellow;
		case 4:
			return EnumEco.light;
		case 5:
			return EnumEco.dark;
		}
		return null;
	}
}
