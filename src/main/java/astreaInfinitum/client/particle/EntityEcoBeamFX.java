package astreaInfinitum.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import cpw.mods.fml.client.FMLClientHandler;
@SuppressWarnings("unused")
public class EntityEcoBeamFX extends EntityFX {
	private double offset = 0.0D;
	private double targX = 0.0D;
	private double targY = 0.0D;
	private double targZ = 0.0D;
	private double prevTickX = 0.0D;
	
	private double prevTickY = 0.0D;
	private double prevTickZ = 0.0D;
	private float prevSize = 0.0F;
	private float length = 0.0F;
	private float rotYaw = 0.0F;
	private float rotPitch = 0.0F;
	private float prevYaw = 0.0F;
	private float prevPitch = 0.0F;
	private float endMod = 1.0F;
	private boolean reverse = false;
	private int rotationspeed = 3;

	public EntityEcoBeamFX(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float red, float green, float blue, int age) {
		super(par1World, posX, posY, posZ, 0.0D, 0.0D, 0.0D);

		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		setSize(0.02F, 0.02F);
		this.noClip = true;
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.targX = targetX;
		this.targY = targetY;
		this.targZ = targetZ;
		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;
		this.particleMaxAge = age;

		EntityLivingBase renderentity = FMLClientHandler.instance().getClient().renderViewEntity;
		int visibleDistance = 50;
		if (!FMLClientHandler.instance().getClient().gameSettings.fancyGraphics) {
			visibleDistance = 25;
		}
		if (renderentity.getDistance(this.posX, this.posY, this.posZ) > visibleDistance) {
			this.particleMaxAge = 0;
		}
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = (this.posY + this.offset);
		this.prevPosZ = this.posZ;

		this.prevTickX = this.targX;
		this.prevTickY = this.targY;
		this.prevTickZ = this.targZ;

		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;

		float distX = (float) (this.posX - this.targX);
		float distY= (float) (this.posY - this.targY);
		float distZ= (float) (this.posZ - this.targZ);
		this.length = MathHelper.sqrt_float(distX * distX + distY * distY + distZ * distZ);
		double var7 = MathHelper.sqrt_double(distX * distX + distZ * distZ);
		this.rotYaw = ((float) (Math.atan2(distX, distZ) * 180.0D / Math.PI));
		this.rotPitch = ((float) (Math.atan2(distY, var7) * 180.0D / Math.PI));
		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;
		if (this.particleAge++ >= this.particleMaxAge) {
			setDead();
		}
	}

	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
		tessellator.draw();
		GL11.glPushMatrix();
		float var9 = 1.0F;
		float slide = Minecraft.getMinecraft().thePlayer.ticksExisted;
		float rot = (float) (this.worldObj.provider.getWorldTime() % (360 / this.rotationspeed) * this.rotationspeed) + this.rotationspeed * f;
		float size = 0.5F;
		size = Math.min(this.particleAge / 4.0F, 1.0F);
		size = (float) (this.prevSize + (size - this.prevSize) * f);
		float op = 0.4F;
		if ((this.particleMaxAge - this.particleAge <= 4)) {
			op = 0.4F - (4 - (this.particleMaxAge - this.particleAge)) * 0.1F;
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModProps.MODID + ":textures/particles/beam.png"));
		GL11.glTexParameterf(3553, 10242, 10497.0F);
		GL11.glTexParameterf(3553, 10243, 10497.0F);

		GL11.glDisable(2884);

		float var11 = slide + f;
		if (this.reverse) {
			var11 *= -1.0F;
		}
		float var12 = var11 * 0.2F - MathHelper.floor_float(-var11 * 0.1F);

		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);
		GL11.glDepthMask(false);

		float xx = (float) (this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
		float yy = (float) (this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
		float zz = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
		GL11.glTranslated(xx, yy, zz);

		float rotYaw = (float) (this.prevYaw + (this.rotYaw - this.prevYaw) * f);
		float rotPitch = (float) (this.prevPitch + (this.rotPitch - this.prevPitch) * f);
		GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F + rotYaw, 0.0F, 0.0F, -1.0F);
		GL11.glRotatef(rotPitch, 1.0F, 0.0F, 0.0F);

		double var44 = -0.08D * size;
		double var17 = 0.08D * size;
		double var44b = -0.08D * size * this.endMod;
		double var17b = 0.08D * size * this.endMod;

		GL11.glRotatef(rot, 0.0F, 1, 0.0F);
		for (int t = 0; t < 6; t++) {
			double var29 = this.length * size * var9;
			double var31 = 0.0D;
			double var33 = 1.0D;
			double var35 = -1.0F + var12 + t / 6.0F;
			double var37 = this.length * size * var9 + var35;

			GL11.glRotatef(30, 0.0F, 1.0F, 0.0F);
			tessellator.startDrawingQuads();
			tessellator.setBrightness(0xFFFFFF);
			tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, op);
			tessellator.addVertexWithUV(var44b, var29, 0.0D, var33, var37);
			tessellator.addVertexWithUV(var44, 0.0D, 0.0D, var33, var35);
			tessellator.addVertexWithUV(var17, 0.0D, 0.0D, var31, var35);
			tessellator.addVertexWithUV(var17b, var29, 0, var31, var37);
			tessellator.draw();
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glEnable(2884);
		GL11.glPopMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
		tessellator.startDrawingQuads();
		this.prevSize = size;
	}

	public void setRGB(float r, float g, float b) {
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
	}

	public void setEndMod(float endMod) {
		this.endMod = endMod;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public void setRotationspeed(int rotationspeed) {
		this.rotationspeed = rotationspeed;
	}
}
