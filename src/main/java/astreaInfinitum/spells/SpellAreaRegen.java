package astreaInfinitum.spells;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import astreaInfinitum.api.EnumEco;
import astreaInfinitum.api.spell.ISpell;
import astreaInfinitum.utils.AIUtils;

public class SpellAreaRegen implements ISpell {

	@Override
	public boolean onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z) {
		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2));
		for (EntityLivingBase e : entities) {
			if (!(e instanceof EntityMob))
				e.addPotionEffect(new PotionEffect(Potion.regeneration.id, AIUtils.getPlayerLevel(player) * 300, AIUtils.getPlayerLevel(player) / 2, true));
		}
		return true;
	}

	@Override
	public EnumEco getEcoType() {
		return EnumEco.light;
	}

}
