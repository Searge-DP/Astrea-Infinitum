package astreaInfinitum.potions;

import astreaInfinitum.ModProps;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AIPotionBase extends Potion {

	private static final ResourceLocation resource = new ResourceLocation(ModProps.MODID, "textures/gui/potions.png");

	public AIPotionBase(int id, String name, boolean badEffect, int color, int iconIndex) {
		super(id, badEffect, color);
		setPotionName("ai.potion." + name);
		setIconIndex(iconIndex % 8, iconIndex / 8);
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(resource);

		return super.getStatusIconIndex();
	}

	public boolean hasEffect(EntityLivingBase entity) {
		return hasEffect(entity, this);
	}

	public boolean hasEffect(EntityLivingBase entity, Potion potion) {
		return entity.getActivePotionEffect(potion) != null;
	}

}