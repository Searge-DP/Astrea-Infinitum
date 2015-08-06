package astreaInfinitum.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import astreaInfinitum.client.particle.EntityFluxFX;
import astreaInfinitum.client.particle.EntitySphereFX;

public class RenderParticles {
	private static Minecraft mc = Minecraft.getMinecraft();
	private static World theWorld = mc.theWorld;

	public static EntityFX spawnParticle(String particleName, double par2, double par4, double par6, double par8, double par10, double par12) {
		if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {
			int var14 = mc.gameSettings.particleSetting;

			if (var14 == 1 && theWorld.rand.nextInt(3) == 0) {
				var14 = 2;
			}

			double var15 = mc.renderViewEntity.posX - par2;
			double var17 = mc.renderViewEntity.posY - par4;
			double var19 = mc.renderViewEntity.posZ - par6;
			EntityFX var21 = null;
			double var22 = 16.0D;

			if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22) {
				return null;
			} else if (var14 > 1) {
				return null;
			} else {
				if (particleName.equals("solarFlux")) {

					var21 = new EntityFluxFX(theWorld, par2, par4, par6, par8, par10, par12);
				} else if (particleName.equals("sphere")) {

					var21 = new EntitySphereFX(theWorld, par2, par4, par6, par8, par10, par12);
				}

				if (var21 != null) {
					mc.effectRenderer.addEffect(var21);
				}
				return var21;
			}
		}
		return null;
	}
}