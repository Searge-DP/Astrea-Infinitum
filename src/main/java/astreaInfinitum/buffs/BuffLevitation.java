package astreaInfinitum.buffs;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import astreaInfinitum.ModProps;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import fluxedCore.buffs.BuffHelper;
import fluxedCore.util.CoordinatePair;
import fluxedCore.util.ResourceInformation;

public class BuffLevitation extends AIBuffBase {

	public BuffLevitation() {
		super("ai.levitation", false, new ResourceInformation(ModProps.buffLocation, new CoordinatePair(0, 0), new CoordinatePair(16, 16)));
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public void onBuffTick(World world, EntityLivingBase entity, int duration, int power) {
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		EntityLivingBase e = event.entityLiving;
		if (BuffHelper.hasBuff(e, this)) {
			int effectiveness = BuffHelper.getEntitybuff(e, this).getPower();
			if (e instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) e;
				if (player.isSneaking()) {
					e.motionY = -(0.1 * effectiveness);
				} else {
					e.motionY = 0.05 * effectiveness;
				}
			} else
				e.motionY = 0.05 * effectiveness;
			e.fallDistance = 0;
		}
	}
}
