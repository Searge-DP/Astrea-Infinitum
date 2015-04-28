package astreaInfinitum.blocks;

import astreaInfinitum.api.EnumMana;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.utils.AIUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockManaExtractor extends Block {

	protected BlockManaExtractor() {
		super(Material.rock);
		setHardness(0.5f);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (!player.isSneaking() && AIUtils.drainMana(player, EnumMana.light, 2)) {
				world.spawnEntityInWorld(new EntityItem(world, x + .5, y + 1.5, z + .5, new ItemStack(AIBlocks.manaDustLight)));
				return true;
			}
			player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerMana(player, EnumMana.light) + ""));
			if (player.isSneaking() && AIUtils.drainMana(player, EnumMana.dark, 2)) {
				world.spawnEntityInWorld(new EntityItem(world, x + .5, y + 1.5, z + .5, new ItemStack(AIBlocks.manaDustDark)));
				return true;
			}

		}
		return false;
	}
}
