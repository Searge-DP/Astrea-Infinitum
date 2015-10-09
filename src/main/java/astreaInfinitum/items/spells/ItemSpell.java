package astreaInfinitum.items.spells;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import astreaInfinitum.ModProps;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.runes.EnumSpellType;
import astreaInfinitum.api.spell.IPrimarySpell;
import astreaInfinitum.api.spell.Spell;
import astreaInfinitum.client.render.RenderUtils;
import astreaInfinitum.entities.EntitySpell;
import astreaInfinitum.network.MessageItemSync;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.utils.AIUtils;
import astreaInfinitum.utils.NBTHelper;
import astreaInfinitum.utils.SpellHelper;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class ItemSpell extends Item implements IPrimarySpell {

	public int castTimeTotal = 0;
	public String name;
	public int ecoUsage;
	public Spell spell;

	public IIcon spellIcon = null;
	public String spellIconName = "";

	public ItemSpell(int castTimeTotal, String name, int ecoUsage, Spell spell, String spellIconName) {
		this.castTimeTotal = castTimeTotal;
		this.name = name;
		this.ecoUsage = ecoUsage;
		this.spell = spell;
		setMaxStackSize(1);
		setMaxDamage(castTimeTotal);
		this.spellIconName = spellIconName;
	}

	public ItemSpell() {
		setMaxStackSize(1);
		setMaxDamage(castTimeTotal);
	}

	public IIcon getSpellIcon() {
		return spellIcon;
	}

	public void setSpellIcon(IIcon spellIcon) {
		this.spellIcon = spellIcon;
	}

	public String getSpellIconName() {
		return spellIconName;
	}

	public void setSpellIconName(String spellIconName) {
		this.spellIconName = spellIconName;
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
		if (pass == 1) {
			return spellIcon;
		} else
			return itemIcon;
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getRenderPasses(int metadata) {
		return 2;
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		super.registerIcons(icons);
		spellIcon = icons.registerIcon(ModProps.modid + ":runes/icons/" + spellIconName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("Type: " + getSpellType(stack));
		list.add("Level: " + NBTHelper.getInt(stack, "AIItemLevel"));
		list.add(String.valueOf(NBTHelper.getInt(stack, "AIItemXP")));
		list.add(String.valueOf(NBTHelper.getInt(stack, "AIItemXPMax")));
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
			if (NBTHelper.getBoolean(stack, "canCast") && canCast(player, spell.function.action.getEcoUsed())) {
				int eco = AIUtils.getPlayerEco(player, spell.function.action.getEcoUsed());

				if (spell.function.action.canCast(world, player, NBTHelper.getInt(stack, "AIItemLevel"), x, y, z)) {
					switch (EnumSpellType.valueOf(NBTHelper.getString(stack, "AISpellType"))) {
						case touch:
							eco -= spell.function.action.onHitTouch(world, player, NBTHelper.getInt(stack, "AIItemLevel"), x, y, z, world.getBlock(x, y, z));
							break;
						case self:
							eco -= spell.function.action.onHitSelf(world, player, NBTHelper.getInt(stack, "AIItemLevel"), x, y, z);
							break;
						case projectile:
							EntitySpell eSpell = new EntitySpell(world, player);
							eSpell.setSpell(spell);
							eSpell.caster = player;
							eSpell.spellLevel = NBTHelper.getInt(stack, "AIItemLevel");
							world.spawnEntityInWorld(eSpell);
							eco -= spell.function.action.getSpellUsage(NBTHelper.getInt(stack, "AIItemLevel"));
							break;
					}

					AIUtils.addSpellXP(stack, 3);
					AIUtils.setPlayerEco(player, spell.function.action.getEcoUsed(), eco);
					NBTHelper.setInteger(stack, "castTime", -1);
					AIUtils.addXP(player, new Random().nextInt(3));
					player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerLevel(player) + ":" + AIUtils.getPlayerMaxXP(player) + ":" + AIUtils.getPlayerXP(player)));
					player.addChatComponentMessage(new ChatComponentText(AIUtils.getPlayerEcoMax(player, EnumPlayerEco.light) + ":" + AIUtils.getPlayerEco(player, EnumPlayerEco.light)));

				}
			} else if (!AIUtils.getPlayerKnowledge(player)) {
				player.addChatComponentMessage(new ChatComponentText("You need to study more!"));
			} else if (AIUtils.getPlayerEco(player, spell.function.action.getEcoUsed()) <= getEcoUsage()) {
				player.addChatComponentMessage(new ChatComponentText("You need more " + spell.function.action.getEcoUsed() + " eco!"));
			} else if (AIUtils.getPlayerKnowledge(player) && AIUtils.getPlayerEco(player, spell.function.action.getEcoUsed()) <= getEcoUsage()) {
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "An error has occured."));
			}
			PacketHandler.INSTANCE.sendToAllAround(new MessageItemSync(NBTHelper.getInt(stack, "AIItemXP"), NBTHelper.getInt(stack, "AIItemXPMax"), NBTHelper.getInt(stack, "AIItemLevel"), getSpellType(stack)), new TargetPoint(world.provider.dimensionId, player.posX, player.posY, player.posZ, 128D));

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

	public boolean canCast(EntityPlayer player, EnumPlayerEco eco) {
		if (AIUtils.getPlayerKnowledge(player)) {
			if (AIUtils.getPlayerEco(player, spell.function.action.getEcoUsed()) >= getEcoUsage()) {
				return true;
			}
		}
		return false;
	}

	public EnumSpellType getSpellType(ItemStack stack) {
		if (NBTHelper.getString(stack, "AISpellType").isEmpty()) {
			NBTHelper.setString(stack, "AISpellType", spell.function.action.getActionTypes()[0].name());
		}
		return EnumSpellType.valueOf(NBTHelper.getString(stack, "AISpellType"));
	}

	public void changeSpellType(ItemStack stack, EnumSpellType type) {
		EnumSpellType[] types = spell.function.action.getActionTypes();
		List<EnumSpellType> typeList = new ArrayList<EnumSpellType>();
		for (EnumSpellType typez : types) {
			typeList.add(typez);
		}
		if (typeList.contains(type)) {
			NBTHelper.setString(stack, "AISpellType", type.name());
		}
	}

	public boolean canSpellHaveType(ItemStack stack, EnumSpellType type) {
		EnumSpellType[] types = spell.function.action.getActionTypes();
		List<EnumSpellType> typeList = new ArrayList<EnumSpellType>();
		for (EnumSpellType typez : types) {
			typeList.add(typez);
		}
		if (typeList.contains(type)) {
			return true;
		}
		return false;
	}

	public void cycleSpellType(ItemStack stack, World world, int x, int y, int z) {
		EnumSpellType type = getSpellType(stack);
		EnumSpellType newType = null;
		switch (type) {
			case touch:
				newType = EnumSpellType.self;
				if (!canSpellHaveType(stack, newType)) {
					newType = EnumSpellType.projectile;
				}
				if (!canSpellHaveType(stack, newType)) {
					newType = EnumSpellType.touch;
				}
				break;
			case self:
				newType = EnumSpellType.projectile;
				if (!canSpellHaveType(stack, newType)) {
					newType = EnumSpellType.touch;
				}
				if (!canSpellHaveType(stack, newType)) {
					newType = EnumSpellType.self;
				}
				break;
			case projectile:
				newType = EnumSpellType.touch;
				if (!canSpellHaveType(stack, newType)) {
					newType = EnumSpellType.self;
				}
				if (!canSpellHaveType(stack, newType)) {
					newType = EnumSpellType.projectile;
				}
				break;
		}
		NBTHelper.setString(stack, "AISpellType", newType.name());
		PacketHandler.INSTANCE.sendToAllAround(new
				MessageItemSync(NBTHelper.getInt(stack, "AIItemXP"),
						NBTHelper.getInt(stack, "AIItemXPMax"), NBTHelper.getInt(stack,
								"AIItemLevel"), getSpellType(stack)), new
				TargetPoint(world.provider.dimensionId, x, y, z, 128D));
	}

	public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean par5) {

		if (!world.isRemote) {
			if (!NBTHelper.getBoolean(stack, "init")) {
				AIUtils.initSpell(stack);
				NBTHelper.setBoolean(stack, "init", true);
			}
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				NBTHelper.setInteger(stack, "castTimeTotal", castTimeTotal);
				stack.setItemDamage(NBTHelper.getInt(stack, "castTimeTotal"));
				if (NBTHelper.getBoolean(stack, "setCasting") && canCast(player, spell.function.action.getEcoUsed())) {
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

				if (spell == null) {
					spell = Spell.readSpellFromNbt(stack.stackTagCompound);
				} else {
					Spell.WriteSpellFromNbt(stack.stackTagCompound, spell);
				}

				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == this) {
					AIUtils.updateSpell(stack);
					PacketHandler.INSTANCE.sendToAllAround(new MessageItemSync(NBTHelper.getInt(stack, "AIItemXP"), NBTHelper.getInt(stack, "AIItemXPMax"), NBTHelper.getInt(stack, "AIItemLevel"), getSpellType(stack)), new TargetPoint(world.provider.dimensionId, entity.posX, entity.posY, entity.posZ, 128D));
				}
			}

		}
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			PacketHandler.INSTANCE.sendToAllAround(new MessageItemSync(NBTHelper.getInt(stack, "AIItemXP"), NBTHelper.getInt(stack, "AIItemXPMax"), NBTHelper.getInt(stack, "AIItemLevel"), getSpellType(stack)), new TargetPoint(world.provider.dimensionId, player.posX, player.posY, player.posZ, 128D));
			if (player.isSneaking()) {
				cycleSpellType(stack, world, (int) player.posX, (int) player.posY, (int) player.posZ);
				return stack;
			}
			if (canCast(player, spell.function.action.getEcoUsed()) && NBTHelper.getInt(stack, "castTime") <= 0 && AIUtils.getPlayerEco(player, spell.function.action.getEcoUsed()) >= ecoUsage && AIUtils.getPlayerKnowledge(player)) {
				NBTHelper.setBoolean(stack, "setCasting", true);
			}
			onCast(stack, world, player, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			PacketHandler.INSTANCE.sendToAllAround(new MessageItemSync(NBTHelper.getInt(stack, "AIItemXP"), NBTHelper.getInt(stack, "AIItemXPMax"), NBTHelper.getInt(stack, "AIItemLevel"), getSpellType(stack)), new TargetPoint(world.provider.dimensionId, x, y, z, 128D));
			if (player.isSneaking()) {
				cycleSpellType(stack, world, x, y, z);
				return true;
			}
			if (canCast(player, spell.function.action.getEcoUsed()) && NBTHelper.getInt(stack, "castTime") <= 0 && AIUtils.getPlayerEco(player, spell.function.action.getEcoUsed()) >= ecoUsage && AIUtils.getPlayerKnowledge(player)) {
				NBTHelper.setBoolean(stack, "setCasting", true);
			}
			onCast(stack, world, player, x, y, z);
		}
		return true;
	}
}
