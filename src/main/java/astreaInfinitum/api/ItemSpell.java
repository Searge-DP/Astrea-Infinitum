package astreaInfinitum.api;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import astreaInfinitum.api.spell.IPrimarySpell;
import astreaInfinitum.api.spell.ISpell;
import astreaInfinitum.utils.AIUtils;
import astreaInfinitum.utils.NBTHelper;

public class ItemSpell extends Item implements IPrimarySpell {

	public int castTimeTotal = 0;
	public String name;
	public int ecoUsage;
	public ISpell spell;

	public ItemSpell(int castTimeTotal, String name, int ecoUsage, ISpell spell) {
		this.castTimeTotal = castTimeTotal;
		this.name = name;
		this.ecoUsage = ecoUsage;
		this.spell = spell;
		setMaxStackSize(1);
		setMaxDamage(castTimeTotal);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

	}

	public int getDisplayDamage(ItemStack stack) {
		return NBTHelper.getInt(stack, "castTimeTotal") - NBTHelper.getInt(stack, "castTime");
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		if (NBTHelper.getInt(item, "castTime") > 0) {
			return false;
		}
		return true;
	}

	public void onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z) {
		if (!world.isRemote) {
			if (NBTHelper.getBoolean(stack, "canCast") && canCast(player, spell.getEcoType())) {
				int eco = AIUtils.getPlayerEco(player, spell.getEcoType());
				if (spell.onCast(stack, world, player, x, y, z)) {
					eco -= getEcoUsage();
					AIUtils.setPlayerEco(player, spell.getEcoType(), eco);
					NBTHelper.setInteger(stack, "castTime", -1);
					AIUtils.addXP(player, new Random().nextInt(3));
					player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerLevel(player) + ":" + AIUtils.getPlayerMaxXP(player) + ":" + AIUtils.getPlayerXP(player)));
					player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerEcoMax(player, EnumEco.light) + ":" + AIUtils.getPlayerEco(player, EnumEco.light)));

				}
			} else if (!AIUtils.getPlayerKnowledge(player)) {
				player.addChatComponentMessage(new ChatComponentText("You need to study more!"));
			} else if (AIUtils.getPlayerEco(player, spell.getEcoType()) <= getEcoUsage()) {
				player.addChatComponentMessage(new ChatComponentText("You need more " + spell.getEcoType() + "eco!"));
			} else if (AIUtils.getPlayerKnowledge(player) && AIUtils.getPlayerEco(player, spell.getEcoType()) <= getEcoUsage()) {
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "An error has occured."));
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getEcoUsage() {
		return ecoUsage;
	}

	public boolean canCast(EntityPlayer player, EnumEco eco) {
		if (AIUtils.getPlayerKnowledge(player)) {
			if (AIUtils.getPlayerEco(player, spell.getEcoType()) >= getEcoUsage()) {
				return true;
			}
		}
		return false;
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean par5) {
		NBTHelper.setInteger(stack, "castTimeTotal", castTimeTotal);
		stack.setItemDamage(NBTHelper.getInt(stack, "castTimeTotal"));
		if (!world.isRemote)
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				if (NBTHelper.getBoolean(stack, "setCasting")) {
					NBTHelper.setBoolean(stack, "canCast", false);
					NBTHelper.setInteger(stack, "castTime", NBTHelper.getInt(stack, "castTime") + 1);
				}
				if (NBTHelper.getInt(stack, "castTime") == NBTHelper.getInt(stack, "castTimeTotal")) {
					NBTHelper.setBoolean(stack, "canCast", true);
					NBTHelper.setBoolean(stack, "setCasting", false);
				}
				if (NBTHelper.getBoolean(stack, "canCast")) {
					NBTHelper.setInteger(stack, "castTime", NBTHelper.getInt(stack, "castTime") - 2);
				}
				if (NBTHelper.getInt(stack, "castTime") <= 0) {
					NBTHelper.setInteger(stack, "castTime", 0);
					NBTHelper.setBoolean(stack, "canCast", false);
					NBTHelper.setBoolean(stack, "setCasting", false);
				}
			}
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (NBTHelper.getInt(stack, "castTime") <= 0 && AIUtils.getPlayerEco(player, spell.getEcoType()) >= ecoUsage && AIUtils.getPlayerKnowledge(player)) {
				NBTHelper.setBoolean(stack, "setCasting", true);
			}
			onCast(stack, world, player, (int) player.posX, (int) player.posY, (int) player.posZ);

		}
		return stack;
	}

//	@Override
//	public boolean requiresMultipleRenderPasses() {
//		return true;
//	}
//
//	@Override
//	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
//		return null;
//	}
}
