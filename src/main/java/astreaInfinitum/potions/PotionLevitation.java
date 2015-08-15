package astreaInfinitum.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import astreaInfinitum.reference.PotionReference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionLevitation extends AIPotionBase {

	public PotionLevitation() {
		super(PotionReference.potionIDLevitation, "Levitation", false, 0xAED3E1, 0);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		EntityLivingBase e = event.entityLiving;
		if (hasEffect(e)) {
			if (e instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) e;
				if (player.isSneaking()) {
					e.motionY = -(0.1 * getEffectiveness());
				} else {
					e.motionY = 0.05 * getEffectiveness();
				}
			} else
				e.motionY = 0.05 * getEffectiveness();
			e.fallDistance = 0;
		}
	}

}
