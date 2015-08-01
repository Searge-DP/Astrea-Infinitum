package astreaInfinitum.client.particle;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public  class EntityEcoFX extends EntityFX {
	private static final ResourceLocation tex = new ResourceLocation(ModProps.MODID + ":textures/particles/soft.png");

	private double targetX, targetY, targetZ;
	private byte indexX, indexY, age, maxAge, breakCheckTimer = 10;

	public EntityEcoFX(World world, double x, double y, double z, double targetX, double targetY, double targetZ) {
		super(world, x, y, z);
		motionX = motionY = motionZ = particleAlpha = 0;

		//		particleRed = 0.727F;
//		particleGreen = 0.684F;
//		particleBlue = 0.527F;
		particleRed = rand.nextFloat()*rand.nextFloat();
		particleGreen = rand.nextFloat()*rand.nextFloat();
		particleBlue = rand.nextFloat()*rand.nextFloat();
		indexX = (byte) rand.nextInt(4);
		indexY = (byte) rand.nextInt(4);

		particleScale = (targetX != 0D && targetY != 0D && targetZ != 0D ? 0.5F : 1F) * rand.nextFloat() * 0.2F + 0.4F;
		maxAge = (byte) (2 + rand.nextInt(20));

		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;

		if (targetX == 0D && targetZ == 0D)
			motionY = targetY;
	}

	public EntityEcoFX(World world, double x, double y, double z) {
		this(world, x, y, z, 0D, 0D, 0D);
	}

	public EntityEcoFX(World world, double x, double y, double z, double motionY) {
		this(world, x, y, z, 0D, motionY, 0D);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		
		if (++age > maxAge)
			setDead();
		if (age < 1)
			particleAlpha = Math.min(1F, particleAlpha + rand.nextFloat() * 0.2F);
		if (age > maxAge - 18)
			particleAlpha = Math.max(0F, particleAlpha - rand.nextFloat() * 0.25F);

		handleMotion();

		if (--breakCheckTimer < 0) {
			breakCheckTimer = 10;
//			age = (byte) (maxAge - 18);
		}

		if (rand.nextInt(3) == 0)
			posX += rand.nextDouble() * 0.02D - 0.01D;
		if (rand.nextInt(3) == 0)
			posY += rand.nextDouble() * 0.02D - 0.01D;
		if (rand.nextInt(3) == 0)
			posZ += rand.nextDouble() * 0.02D - 0.01D;
	}

	protected void handleMotion() {
		if (targetX != 0D && targetZ != 0D) {
			maxAge = 1;

			Vec3 motionVec = Vec3.createVectorHelper(targetX - posX, targetY - posY, targetZ - posZ).normalize();

			double speedFactor = rand.nextDouble() * 0.05D + 0.15D;
			posX += motionVec.xCoord * speedFactor;
			posY += motionVec.yCoord * speedFactor;
			posZ += motionVec.zCoord * speedFactor;

			return;
		} else if (motionY != 0D)
			posY += motionY *= 0.98D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float partialTickTime) {
		return Integer.MAX_VALUE;
	}

	@Override
	public float getBrightness(float partialTickTime) {
		return Float.MAX_VALUE;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float partialTickTime, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
		Minecraft.getMinecraft().renderEngine.bindTexture(tex);
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		float left = indexX * 0.25F, right = left + 0.25F, top = indexY * 0.25F, bottom = top + 0.25F, x = (float) (prevPosX + (posX - prevPosX) * partialTickTime - interpPosX + (player.prevPosX - player.posX)), y = (float) (prevPosY + (posY - prevPosY) * partialTickTime - interpPosY + (player.prevPosY - player.posY)), z = (float) (prevPosZ + (posZ - prevPosZ) * partialTickTime - interpPosZ + (player.prevPosZ - player.posZ));

		GL11.glPushMatrix();
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		RenderHelper.disableStandardItemLighting();

		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 0.2f);
		tessellator.setNormal(0F, 1F, 0F);
		tessellator.setBrightness(65);
		tessellator.addVertexWithUV(x - rotX * particleScale - rotYZ * particleScale, y - rotXZ * particleScale, z - rotZ * particleScale - rotXY * particleScale, right, bottom);
		tessellator.addVertexWithUV(x - rotX * particleScale + rotYZ * particleScale, y + rotXZ * particleScale, z - rotZ * particleScale + rotXY * particleScale, right, top);
		tessellator.addVertexWithUV(x + rotX * particleScale + rotYZ * particleScale, y + rotXZ * particleScale, z + rotZ * particleScale + rotXY * particleScale, left, top);
		tessellator.addVertexWithUV(x + rotX * particleScale - rotYZ * particleScale, y - rotXZ * particleScale, z + rotZ * particleScale - rotXY * particleScale, left, bottom);
		tessellator.draw();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}

	@Override
	public int getFXLayer() {
		return 3;
	}
}