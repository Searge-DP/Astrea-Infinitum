package astreaInfinitum.blocks.eco;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockEcoBlock extends ItemBlockWithMetadata {
	public String[] names = new String[] { "Red", "Green", "Blue", "Yellow", "Light", "Dark" };

	public ItemBlockEcoBlock(Block block) {
		super(block, block);
		setHasSubtypes(true);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		return ((BlockEcoBlock) field_150939_a).icons[damage];
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
