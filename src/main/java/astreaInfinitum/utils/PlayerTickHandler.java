package astreaInfinitum.utils;

import static astreaInfinitum.utils.AIUtils.addMana;
import static astreaInfinitum.utils.AIUtils.getPlayerKnowledge;
import static astreaInfinitum.utils.AIUtils.getPlayerLevel;
import static astreaInfinitum.utils.AIUtils.getPlayerMana;
import static astreaInfinitum.utils.AIUtils.getPlayerManaMax;
import static astreaInfinitum.utils.AIUtils.getPlayerMaxXP;
import static astreaInfinitum.utils.AIUtils.getPlayerXP;
import static astreaInfinitum.utils.AIUtils.levelUp;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureVillagePieces.WoodHut;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumMana;
import astreaInfinitum.api.ItemProjectileSpell;
import astreaInfinitum.api.ItemSpell;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.network.MessagePlayerSync;
import astreaInfinitum.network.PacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerTickHandler {

	@SubscribeEvent
	public void tooltip(ItemTooltipEvent e) {
		if (e.itemStack.getItem() instanceof ItemSpell) {
			ItemSpell spell = (ItemSpell) e.itemStack.getItem();
			e.toolTip.add("Casting Mana: " + spell.getManaUsage());

		}
		if (e.itemStack.getItem() instanceof ItemProjectileSpell) {
			ItemProjectileSpell spell = (ItemProjectileSpell) e.itemStack.getItem();
			e.toolTip.add("Casting Mana: " + spell.getManaUsage());

		}

	}

	@SubscribeEvent
	public void clayDrop(BlockEvent.HarvestDropsEvent e) {
		if (e.harvester != null && !e.isSilkTouching && e.block == Blocks.clay) {
			if (new Random().nextInt(100) == 5)
				if (!getPlayerKnowledge(e.harvester)) {
					e.drops.add(new ItemStack(AIItems.tabletKnowledge));
				}
		}
	}

	@SubscribeEvent
	public void mobSpells(LivingUpdateEvent e) {
		if (!e.entity.worldObj.isRemote)
			if (e.entity.getEntityData().getBoolean("trans")) {
				int transTime = e.entity.getEntityData().getInteger("transTime");
				if (transTime > 0) {
					e.entity.getEntityData().setInteger("transTime", transTime - 1);
				}
				if (e.entity.getEntityData().getInteger("transTime") <= 0) {
					e.entity.getEntityData().setBoolean("trans", false);
					EntityLiving ent = (EntityLiving)EntityList.createEntityByName(e.entity.getEntityData().getString("prevEntName"), e.entity.worldObj);
					ent.readEntityFromNBT(e.entity.getEntityData().getCompoundTag("prevEnt"));
					ent.setPosition(e.entity.posX, e.entity.posY, e.entity.posZ);
					e.entity.worldObj.spawnEntityInWorld(ent);
					
					e.entity.setDead();
				}
			}

	}

	@SubscribeEvent
	public void generateMana(LivingUpdateEvent e) {
		if (!e.entity.worldObj.isRemote)
			if (e.entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) e.entity;
				if (getPlayerKnowledge(player)) {
					if (new Random().nextInt(20) == 0) {
						if (getPlayerMana(player, EnumMana.light) < getPlayerManaMax(player, EnumMana.light)) {
							addMana(player, EnumMana.light, 1);
						}
						if (getPlayerMana(player, EnumMana.dark) < getPlayerManaMax(player, EnumMana.dark)) {
							addMana(player, EnumMana.dark, 1);
						}

						if (getPlayerMaxXP(player) > 0)
							levelUp(player);
					}
				}

				PacketHandler.INSTANCE.sendToAll(new MessagePlayerSync(getPlayerKnowledge(player), getPlayerMana(player, EnumMana.light), getPlayerMana(player, EnumMana.dark), getPlayerLevel(player), getPlayerXP(player), getPlayerMaxXP(player), getPlayerManaMax(player, EnumMana.light), getPlayerMana(player, EnumMana.dark)));
			}
	}

	@SubscribeEvent
	public void renderGUI(RenderGameOverlayEvent.Text e) {
		if ((e.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) || (e.type == RenderGameOverlayEvent.ElementType.TEXT)) {
			EntityPlayer player = AstreaInfinitum.proxy.getClientPlayer();
			if (getPlayerKnowledge(player)) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ModProps.modid, "textures/gui/overlay.png"));
				Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) + 91, e.resolution.getScaledHeight() - 37, 0, 1, 75, 37);
				Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) + 92, e.resolution.getScaledHeight() - 38, 84, 0, getScaledBar(75, getPlayerMana(player, EnumMana.light), getPlayerManaMax(player, EnumMana.light)), 52);
				Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) - 91 - 74, e.resolution.getScaledHeight() - 39, 0, 38, 75, 40);
				Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect((e.resolution.getScaledWidth() / 2) - 90 - getScaledBar(75, getPlayerMana(player, EnumMana.dark), getPlayerManaMax(player, EnumMana.dark)), e.resolution.getScaledHeight() - 38, 84 + 75 - getScaledBar(75, getPlayerMana(player, EnumMana.dark), getPlayerManaMax(player, EnumMana.dark)), 39, getScaledBar(75, getPlayerMana(player, EnumMana.dark), getPlayerManaMax(player, EnumMana.dark)), 52);
				Minecraft.getMinecraft().fontRenderer.drawString("" + getPlayerLevel(player), e.resolution.getScaledWidth() / 2, e.resolution.getScaledHeight() - 44, 0xFF55FF, true);
			}
		}
	}

	public int getScaledBar(int scale, int mana, int maxMana) {
		return (int) ((mana * scale) / 100);
	}
}
