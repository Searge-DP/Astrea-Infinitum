package astreaInfinitum.blocks.eco.world;

import astreaInfinitum.ModProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockEcoOre extends ItemBlockWithMetadata {
	public String[] names = new String[] { "Red", "Green", "Blue", "Yellow", "Light", "Dark" };

	public ItemBlockEcoOre(Block block) {
		super(block, block);
		setHasSubtypes(true);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		return ((BlockEcoOre) field_150939_a).icons[damage];
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + "." + names[stack.getItemDamage()];
	}

}
