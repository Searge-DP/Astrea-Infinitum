package astreaInfinitum.api;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import astreaInfinitum.api.spell.IPrimarySpell;
import astreaInfinitum.api.spell.IProjectileSpell;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.network.MessageItemSync;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.utils.AIUtils;
import astreaInfinitum.utils.NBTHelper;

public class ItemProjectileSpell extends Item implements IPrimarySpell {

	public int castTimeTotal = 0;
	public int castTime = 0;
	public boolean setCasting = false;
	public boolean canCast = false;
	public String name;
	public int manaUsage;
	public IProjectileSpell spell;

	public ItemProjectileSpell(int castTimeTotal, String name, int manaUsage, IProjectileSpell spell) {
		this.castTimeTotal = castTimeTotal;
		this.name = name;
		this.manaUsage = manaUsage;
		this.spell = spell;
		setMaxStackSize(1);
		setMaxDamage(castTimeTotal);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(String.valueOf(NBTHelper.getInt(stack, "AIItemLevel")));
		list.add(String.valueOf(NBTHelper.getInt(stack, "AIItemXP")));
		list.add(String.valueOf(NBTHelper.getInt(stack, "AIItemXPMax")));
	}

	public int getDisplayDamage(ItemStack stack) {
		return castTimeTotal - castTime;
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		if (castTime > 0) {
			return false;
		}
		return true;
	}

	public void onCast(ItemStack stack, World world, EntityPlayer player, int x, int y, int z) {
		if (!world.isRemote) {
			if (canCast && canCast(player, spell.getManaType())) {
				if (world != null && player != null) {
					EntitySpell eSpell = new EntitySpell(world, player);
					eSpell.setSpell(spell);
					eSpell.caster = player;
					world.spawnEntityInWorld(eSpell);
				}
				int mana = AIUtils.getPlayerMana(player, spell.getManaType());

				mana -= getManaUsage();
				AIUtils.setPlayerMana(player, spell.getManaType(), mana);
				castTime = -1;
				AIUtils.addXP(player, new Random().nextInt(3));
				player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerLevel(player) + ":" + AIUtils.getPlayerMaxXP(player) + ":" + AIUtils.getPlayerXP(player)));
				player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerManaMax(player, EnumMana.light) + ":" + AIUtils.getPlayerMana(player, EnumMana.light)));
				AIUtils.addSpellXP(stack, 3);
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
				if (NBTHelper.getInt(stack, "AIItemLevel") < 1) {
					AIUtils.initSpell(stack);
				}
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
				if (NBTHelper.getInt(stack, "AIItemXP") >= NBTHelper.getInt(stack, "AIItemXPMax")) {
					AIUtils.levelUpSpell(stack);
					PacketHandler.INSTANCE.sendToAll(new MessageItemSync(NBTHelper.getInt(stack, "AIItemXP"), NBTHelper.getInt(stack, "AIItemXPMax"), NBTHelper.getInt(stack, "AIItemLevel")));
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
