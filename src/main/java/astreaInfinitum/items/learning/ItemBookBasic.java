package astreaInfinitum.items.learning;

import static astreaInfinitum.utils.AIUtils.getPlayerKnowledge;
import static astreaInfinitum.utils.AIUtils.getPlayerLevel;
import static astreaInfinitum.utils.AIUtils.getPlayerEco;
import static astreaInfinitum.utils.AIUtils.getPlayerEcoMax;
import static astreaInfinitum.utils.AIUtils.getPlayerMaxXP;
import static astreaInfinitum.utils.AIUtils.getPlayerXP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.network.MessagePlayerSync;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.utils.AIUtils;

public class ItemBookBasic extends Item {

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (player != null && !(player instanceof FakePlayer)) {
				AstreaInfinitum.proxy.readBookBasic();
				AIUtils.initPlayer(player);
				
				
			}
		}
		return stack;
	}
}
