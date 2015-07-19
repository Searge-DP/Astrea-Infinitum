package astreaInfinitum.blocks;

import astreaInfinitum.api.EnumEco;
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

public class BlockEcoExtractor extends Block {

	protected BlockEcoExtractor() {
		super(Material.rock);
		setHardness(0.5f);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (!player.isSneaking() && AIUtils.drainEco(player, EnumEco.light, 2)) {
				world.spawnEntityInWorld(new EntityItem(world, x + .5, y + 1.5, z + .5, new ItemStack(AIBlocks.ecoDustLight)));
				return true;
			}
			player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerEco(player, EnumEco.light) + ""));
			if (player.isSneaking() && AIUtils.drainEco(player, EnumEco.dark, 2)) {
				world.spawnEntityInWorld(new EntityItem(world, x + .5, y + 1.5, z + .5, new ItemStack(AIBlocks.ecoDustDark)));
				return true;
			}

		}
		return false;
	}
}
