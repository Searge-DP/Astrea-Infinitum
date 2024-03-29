package astreaInfinitum.utils;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.runes.EnumSpellType;
import astreaInfinitum.entities.properties.EntityData;
import astreaInfinitum.items.spells.ItemSpell;
import astreaInfinitum.network.MessagePlayerSync;
import astreaInfinitum.network.PacketHandler;

public class AIUtils {

	public static void initPlayer(EntityPlayer player) {
		setPlayerKnowledge(player, true);
		setPlayerLevel(player, 1);
		setPlayerEco(player, EnumPlayerEco.light, 0);
		setPlayerEco(player, EnumPlayerEco.dark, 0);
		setPlayerMaxEco(player, EnumPlayerEco.light, 40);
		setPlayerMaxEco(player, EnumPlayerEco.dark, 40);
		setPlayerMaxXP(player, 10);
		setPlayerXP(player, 0);
		addChatMessage(player, "level:" + getPlayerLevel(player));
		addChatMessage(player, "maxLight:" + getPlayerEcoMax(player, EnumPlayerEco.light));
		addChatMessage(player, "maxDark:" + getPlayerEcoMax(player, EnumPlayerEco.dark));
		addChatMessage(player, "currentLight:" + getPlayerEco(player, EnumPlayerEco.light));
		addChatMessage(player, "currentDark:" + getPlayerEco(player, EnumPlayerEco.dark));
		addChatMessage(player, "maxXP:" + getPlayerMaxXP(player));
		addChatMessage(player, "xp:" + getPlayerXP(player));
		PacketHandler.INSTANCE.sendToAll(new MessagePlayerSync(getPlayerKnowledge(player), getPlayerEco(player, EnumPlayerEco.light), getPlayerEco(player, EnumPlayerEco.dark), getPlayerLevel(player), getPlayerXP(player), getPlayerMaxXP(player), getPlayerEcoMax(player, EnumPlayerEco.light), getPlayerEcoMax(player, EnumPlayerEco.dark)));

	}

	public static void addChatMessage(EntityPlayer player, String message) {
		player.addChatComponentMessage(new ChatComponentText(message));
	}

	public static EntityData getEntityData(EntityLivingBase entity) {
		return (EntityData) entity.getExtendedProperties(ModProps.modid);
	}

	public static boolean getPlayerKnowledge(EntityPlayer player) {
		return getEntityData(player).isKnowledge();
	}

	public static int getPlayerEco(EntityPlayer player, EnumPlayerEco eco) {
		if (eco == EnumPlayerEco.light) {
			return getEntityData(player).getEcoLight();
		}
		if (eco == EnumPlayerEco.dark) {
			return getEntityData(player).getEcoDark();
		}
		return 0;
	}

	public static int getPlayerEcoMax(EntityPlayer player, EnumPlayerEco eco) {
		if (eco == EnumPlayerEco.light) {
			return getEntityData(player).getEcoMaxLight();
		}
		if (eco == EnumPlayerEco.dark) {
			return getEntityData(player).getEcoMaxDark();
		}
		return 0;
	}

	public static int getPlayerMaxXP(EntityPlayer player) {
		return getEntityData(player).getMaxXP();
	}

	public static int getPlayerXP(EntityPlayer player) {
		return getEntityData(player).getXp();
	}

	public static int getPlayerLevel(EntityPlayer player) {
		return getEntityData(player).getLevel();
	}

	public static void setPlayerKnowledge(EntityPlayer player, boolean bool) {
		getEntityData(player).setKnowledge(bool);
	}

	public static void setPlayerEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		if (eco == EnumPlayerEco.light) {
			getEntityData(player).setEcoLight(amount);
		}
		if (eco == EnumPlayerEco.dark) {
			getEntityData(player).setEcoDark(amount);
		}
	}

	public static void setPlayerMaxXP(EntityPlayer player, int max) {
		getEntityData(player).setMaxXP(max);
	}

	public static void setPlayerXP(EntityPlayer player, int xp) {
		getEntityData(player).setXp(xp);
	}

	public static void setPlayerLevel(EntityPlayer player, int level) {
		getEntityData(player).setLevel(level);
	}

	public static void setPlayerMaxEco(EntityPlayer player, EnumPlayerEco eco, int max) {
		if (eco == EnumPlayerEco.light) {
			getEntityData(player).setEcoMaxLight(max);
		}
		if (eco == EnumPlayerEco.dark) {
			getEntityData(player).setEcoMaxDark(max);
		}
	}

	public static void removeEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		int ecoA = 0;
		if (eco == EnumPlayerEco.light) {
			ecoA = getEntityData(player).getEcoLight();
		}
		if (eco == EnumPlayerEco.dark) {
			ecoA = getEntityData(player).getEcoDark();
		}
		ecoA -= amount;
		setPlayerEco(player, eco, ecoA);
	}

	public static void levelUp(EntityPlayer player) {
		if (getPlayerXP(player) >= getPlayerMaxXP(player)) {
			setPlayerXP(player, 0);
			setPlayerMaxXP(player, getPlayerMaxXP(player) + (getPlayerMaxXP(player) / 2));
			setPlayerMaxEco(player, EnumPlayerEco.light, (getPlayerEcoMax(player, EnumPlayerEco.light) / 2) + getPlayerEcoMax(player, EnumPlayerEco.light));
			setPlayerMaxEco(player, EnumPlayerEco.dark, (getPlayerEcoMax(player, EnumPlayerEco.dark) / 2) + getPlayerEcoMax(player, EnumPlayerEco.dark));
			setPlayerLevel(player, getPlayerLevel(player) + 1);
		}
	}

	public static void addEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		int ecoA = getPlayerEco(player, eco);
		ecoA += amount;
		setPlayerEco(player, eco, ecoA);
	}

	public static void addXP(EntityPlayer player, int amount) {
		int xp = getPlayerXP(player);
		xp += amount * getPlayerLevel(player);
		setPlayerXP(player, xp);
	}

	public static boolean drainEco(EntityPlayer player, EnumPlayerEco eco, int amount) {
		int ecoA = getPlayerEco(player, eco);
		if (ecoA >= amount) {
			ecoA -= amount;
			setPlayerEco(player, eco, ecoA);
			return true;
		}
		return false;
	}

	public static void initSpell(ItemStack stack) {
		NBTHelper.setInteger(stack, "AIItemXP", 0);
		NBTHelper.setInteger(stack, "AIItemXPMax", 10);
		NBTHelper.setInteger(stack, "AIItemLevel", 1);
	}

	public static void updateSpell(ItemStack stack) {
		if (stack != null) {
			if (stack.getItem() instanceof ItemSpell) {
				ItemSpell spell = (ItemSpell) stack.getItem();
				int xp = NBTHelper.getInt(stack, "AIItemXP");
				int xpMax = NBTHelper.getInt(stack, "AIItemXPMax");
				int level = NBTHelper.getInt(stack, "AIItemLevel");
				EnumSpellType type = spell.getSpellType(stack);
				spell.changeSpellType(stack, type);
				if (xp >= xpMax && level < 5) {
					level++;
					xp = 0;
					xpMax = (xpMax + (xpMax / 2));
					NBTHelper.setInteger(stack, "AIItemLevel", level);
					NBTHelper.setInteger(stack, "AIItemXP", xp);
					NBTHelper.setInteger(stack, "AIItemXPMax", xpMax);
				} else if (level >= 5) {
					xp = 0;
					xpMax = 0;
				}
			}
		}

	}

	public static void addSpellXP(ItemStack stack, int amount) {
		int xp = NBTHelper.getInt(stack, "AIItemXP");
		xp += new Random().nextInt(amount * (NBTHelper.getInt(stack, "AIItemLevel") + 1));
		NBTHelper.setInteger(stack, "AIItemXP", xp);
	}

	public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3) {
		float var4 = 1.0F;
		float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * var4;

		float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * var4;

		double posX = entity.prevPosX + (entity.posX - entity.prevPosX) * var4;

		double posY = entity.prevPosY + (entity.posY - entity.prevPosY) * var4 + 1.62D - entity.yOffset;

		double posZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * var4;

		Vec3 var13 = Vec3.createVectorHelper(posX, posY, posZ);
		float var14 = MathHelper.cos(-yaw * 0.01745329F - 3.141593F);
		float var15 = MathHelper.sin(-yaw * 0.01745329F - 3.141593F);
		float var16 = -MathHelper.cos(-pitch * 0.01745329F);
		float var17 = MathHelper.sin(-pitch * 0.01745329F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 10.0D;
		Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);

		return world.func_147447_a(var13, var23, par3, !par3, false);
	}

	public static MovingObjectPosition getTargetBlock(World world, double x, double y, double z, float yaw, float pitch, boolean par3, double range) {
		Vec3 vec = Vec3.createVectorHelper(x, y, z);
		float var14 = MathHelper.cos(-yaw * 0.01745329F - 3.141593F);
		float var15 = MathHelper.sin(-yaw * 0.01745329F - 3.141593F);
		float var16 = -MathHelper.cos(-pitch * 0.01745329F);
		float var17 = MathHelper.sin(-pitch * 0.01745329F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		Vec3 var23 = vec.addVector(var18 * range, var17 * range, var20 * range);

		return world.func_147447_a(vec, var23, par3, !par3, false);
	}

	@SuppressWarnings("rawtypes")
	public static Entity getPointedEntity(World world, EntityLivingBase entityplayer, double range, double collideRadius, boolean nonCollide) {
		Entity pointedEntity = null;
		double d = range;
		Vec3 vec3d = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ);
		Vec3 vec3d1 = entityplayer.getLookVec();
		Vec3 vec3d2 = vec3d.addVector(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d);
		double f1 = collideRadius;
		List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.boundingBox.addCoord(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d).expand(f1, f1, f1));

		double d2 = 0.0D;
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			MovingObjectPosition mop = world.rayTraceBlocks(
					Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ),
					Vec3.createVectorHelper(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ),
					false);
			if (((entity.canBeCollidedWith()) || (nonCollide)) && mop == null) {
				float f2 = Math.max(0.8F, entity.getCollisionBorderSize());
				AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f2, f2, f2);
				MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d2);
				if (axisalignedbb.isVecInside(vec3d)) {
					if ((0.0D < d2) || (d2 == 0.0D)) {
						pointedEntity = entity;
						d2 = 0.0D;
					}

				} else if (movingobjectposition != null) {
					double d3 = vec3d.distanceTo(movingobjectposition.hitVec);
					if ((d3 < d2) || (d2 == 0.0D)) {
						pointedEntity = entity;
						d2 = d3;
					}
				}
			}
		}
		return pointedEntity;
	}
}
