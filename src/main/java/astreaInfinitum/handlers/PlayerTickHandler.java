package astreaInfinitum.handlers;

import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.entities.properties.EntityData;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.items.spells.ItemSpell;
import astreaInfinitum.network.MessagePlayerSync;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.utils.AIUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Random;

import static astreaInfinitum.utils.AIUtils.*;

public class PlayerTickHandler {

	@SubscribeEvent
	public void tooltip(ItemTooltipEvent e) {
		if (e.itemStack.getItem() instanceof ItemSpell) {
			ItemSpell spell = (ItemSpell) e.itemStack.getItem();
			e.toolTip.add("Casting Eco: " + spell.getEcoUsage());

		}
	}

	@SubscribeEvent
	public void clayDrop(BlockEvent.HarvestDropsEvent e) {
		if (e.harvester != null && !e.isSilkTouching && e.block == Blocks.clay) {
			if (new Random().nextInt(100) == 5) if (!getPlayerKnowledge(e.harvester)) {
				e.drops.add(new ItemStack(AIItems.tabletKnowledge));
			}
		}
	}

	@SubscribeEvent
	public void shear(EntityInteractEvent e) {
		if (e.target.getEntityData().getBoolean("trans"))
			if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemShears) {
				if (e.target instanceof IShearable) {
					e.setCanceled(true);
				}
			}
	}

	@SubscribeEvent
	public void entityDead(LivingHurtEvent e) {
		if (e.entityLiving.getEntityData().getBoolean("trans")) {
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void mobSpells(LivingUpdateEvent e) {
		if (!e.entity.worldObj.isRemote) if (e.entity.getEntityData().getBoolean("trans")) {
			int transTime = e.entity.getEntityData().getInteger("transTime");
			if (transTime > 0) {
				e.entity.getEntityData().setInteger("transTime", transTime - 1);
			}
			if (e.entity.getEntityData().getInteger("transTime") <= 0) {
				e.entity.getEntityData().setBoolean("trans", false);
				EntityLiving ent = (EntityLiving) EntityList.createEntityByName(e.entity.getEntityData().getString("prevEntName"), e.entity.worldObj);
				ent.readEntityFromNBT(e.entity.getEntityData().getCompoundTag("prevEnt"));
				ent.setPosition(e.entity.posX, e.entity.posY, e.entity.posZ);
				e.entity.worldObj.spawnEntityInWorld(ent);
				e.entity.setDead();
			}
		}
	}

	@SubscribeEvent
	public void generateEco(LivingUpdateEvent e) {
		if (!e.entity.worldObj.isRemote) if (e.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.entity;
			if (getPlayerKnowledge(player)) {
				int rand = new Random().nextInt(10);
				if (rand == 0) {
					if (getPlayerEco(player, EnumPlayerEco.light) < getPlayerEcoMax(player, EnumPlayerEco.light)) {
						addEco(player, EnumPlayerEco.light, 1);
					}
					if (getPlayerEco(player, EnumPlayerEco.dark) < getPlayerEcoMax(player, EnumPlayerEco.dark)) {
						addEco(player, EnumPlayerEco.dark, 1);
					}

					if (getPlayerMaxXP(player) > 0) levelUp(player);
				}
				if (getPlayerEcoMax(player, EnumPlayerEco.light) < getPlayerEco(player, EnumPlayerEco.light)) {
					AIUtils.setPlayerEco(player, EnumPlayerEco.light, getPlayerEcoMax(player, EnumPlayerEco.light));
				}
				if (getPlayerEcoMax(player, EnumPlayerEco.dark) < getPlayerEco(player, EnumPlayerEco.dark)) {
					AIUtils.setPlayerEco(player, EnumPlayerEco.dark, getPlayerEcoMax(player, EnumPlayerEco.dark));
				}
			}
			PacketHandler.INSTANCE.sendToAll(new MessagePlayerSync(getPlayerKnowledge(player), getPlayerEco(player, EnumPlayerEco.light), getPlayerEco(player, EnumPlayerEco.dark), getPlayerLevel(player), getPlayerXP(player), getPlayerMaxXP(player), getPlayerEcoMax(player, EnumPlayerEco.light), getPlayerEcoMax(player, EnumPlayerEco.dark)));
		}
	}

	@SubscribeEvent
	public void renderGUI(RenderGameOverlayEvent.Text e) {
		if ((e.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) || (e.type == RenderGameOverlayEvent.ElementType.TEXT)) {
			EntityPlayer player = AstreaInfinitum.proxy.getClientPlayer();
			if (getPlayerKnowledge(player)) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ModProps.modid, "textures/gui/overlay.png"));
				Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) + 91, e.resolution.getScaledHeight() - 12, 6, 2, 102, 12);
				Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) - 91 - 102, e.resolution.getScaledHeight() - 12, 6, 18, 102, 12);
				if (getPlayerEco(player, EnumPlayerEco.light) > 0) {
					Minecraft.getMinecraft().fontRenderer.drawString("" + getPlayerEco(player, EnumPlayerEco.light) + ":" + getPlayerEcoMax(player, EnumPlayerEco.light) + ":" + getScaledBar(102, getPlayerEco(player, EnumPlayerEco.light), getPlayerEcoMax(player, EnumPlayerEco.light)), e.resolution.getScaledWidth() / 2, e.resolution.getScaledHeight() - 500, 0xFFFFFF, true);
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ModProps.modid, "textures/gui/overlay.png"));
					Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) + 93, e.resolution.getScaledHeight() - 11, 120, 3, getScaledBar(102, getPlayerEco(player, EnumPlayerEco.light), getPlayerEcoMax(player, EnumPlayerEco.light)), 12);
				}
				if (getPlayerEco(player, EnumPlayerEco.dark) > 0) {
					Minecraft.getMinecraft().fontRenderer.drawString("" + getPlayerEco(player, EnumPlayerEco.dark) + ":" + getPlayerEcoMax(player, EnumPlayerEco.dark) + ":" + getScaledBar(102, getPlayerEco(player, EnumPlayerEco.dark), getPlayerEcoMax(player, EnumPlayerEco.dark)), e.resolution.getScaledWidth() / 2, e.resolution.getScaledHeight() - 550, 0xccaacc, true);
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ModProps.modid, "textures/gui/overlay.png"));
					/**
					 * Draws a textured rectangle at the stored z-value. Args:
					 * x, y, u, v, width, height
					 */
					Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) - 93 - 98, e.resolution.getScaledHeight() - 11, 222 - getScaledBar(102, getPlayerEco(player, EnumPlayerEco.dark), getPlayerEcoMax(player, EnumPlayerEco.dark)), 19, getScaledBar(102, getPlayerEco(player, EnumPlayerEco.dark), getPlayerEcoMax(player, EnumPlayerEco.dark)), 12);
				}
				Minecraft.getMinecraft().fontRenderer.drawString("" + getPlayerLevel(player), e.resolution.getScaledWidth() / 2, e.resolution.getScaledHeight() - 44, 0xFF55FF, true);

			}
		}
	}

	public int getScaledBar(int scale, int eco, int maxEco) {
		int barWidth = (int) (((float) eco / maxEco) * scale);
		return barWidth;
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityLivingBase) {
			event.entity.registerExtendedProperties(ModProps.modid, new EntityData());
		}
	}
}
