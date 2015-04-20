package astreaInfinitum.api;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import astreaInfinitum.api.spell.IPrimarySpell;
import astreaInfinitum.api.spell.ISpell;
import astreaInfinitum.utils.AIUtils;

public class ItemSpell extends Item implements IPrimarySpell {

	public int castTimeTotal = 0;
	public int castTime = 0;
	public boolean setCasting = false;
	public boolean canCast = false;
	public String name;
	public int manaUsage;
	public ISpell spell;

	public ItemSpell(int castTimeTotal, String name, int manaUsage, ISpell spell) {
		this.castTimeTotal = castTimeTotal;
		this.name = name;
		this.manaUsage = manaUsage;
		this.spell = spell;
		setMaxStackSize(1);
		setMaxDamage(castTimeTotal);
	}

	public int getDisplayDamage(ItemStack stack) {
		return castTimeTotal - castTime;
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		if (castTime>0) {
			return false;
		}
		return true;
	}

	public void onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z) {
		if (!world.isRemote) {
			if (canCast && canCast(player, spell.getManaType())) {
				int mana = AIUtils.getPlayerMana(player, spell.getManaType());
				if (spell.onCast(stack, world, player, x, y, z)) {
					mana -= getManaUsage();
					AIUtils.setPlayerMana(player, spell.getManaType(), mana);
					castTime = -1;
					AIUtils.addXP(player, new Random().nextInt(3));
					player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerLevel(player) + ":" + AIUtils.getPlayerMaxXP(player) + ":" + AIUtils.getPlayerXP(player)));
					player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerManaMax(player, EnumMana.light) + ":" + AIUtils.getPlayerMana(player, EnumMana.light)));

				}
			} else if (!AIUtils.getPlayerKnowledge(player)) {
				player.addChatComponentMessage(new ChatComponentText("You need to study more!"));
			} else if (AIUtils.getPlayerMana(player, spell.getManaType()) <= getManaUsage()) {
				player.addChatComponentMessage(new ChatComponentText("You need more mana!"));
			} else if (AIUtils.getPlayerKnowledge(player) && AIUtils.getPlayerMana(player, spell.getManaType()) <= getManaUsage()) {
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "An error has occured."));
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getManaUsage() {
		return manaUsage;
	}

	public boolean canCast(EntityPlayer player, EnumMana mana) {
		if (AIUtils.getPlayerKnowledge(player)) {
			if (AIUtils.getPlayerMana(player, spell.getManaType()) >= getManaUsage()) {
				return true;
			}
		}
		return false;
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean par5) {
		stack.setItemDamage(castTimeTotal);
		if (!world.isRemote)
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				if (setCasting) {
					castTime++;
					canCast = false;
				}
				if (castTime == castTimeTotal) {
					canCast = true;
					setCasting = false;
				}
				if (canCast) {
					castTime -= 2;
				}
				if (castTime <= 0) {
					castTime = 0;
					canCast = false;
					setCasting = false;
				}
			}
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (castTime <= 0 && AIUtils.getPlayerMana(player, spell.getManaType()) >= manaUsage && AIUtils.getPlayerKnowledge(player)) {
				setCasting = true;
			}
			onCast(stack, world, player, (int) player.posX, (int) player.posY, (int) player.posZ);

		}
		return stack;
	}

}
