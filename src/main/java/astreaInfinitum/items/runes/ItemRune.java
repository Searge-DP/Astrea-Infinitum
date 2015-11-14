package astreaInfinitum.items.runes;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import astreaInfinitum.ModProps;
import astreaInfinitum.utils.NBTHelper;
import fluxedCore.util.CoordinatePair;

public class ItemRune extends Item {

	public String[] runes = new String[] { "stone", "sandStone", "obsidian" };
	public IIcon[] icons;

	public ItemRune() {
		setMaxStackSize(1);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < runes.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		super.addInformation(stack, player, list, p_77624_4_);
		NBTTagList nodes = NBTHelper.getTagList(stack, "nodes", NBT.TAG_COMPOUND);
		for (int i = 0; i < nodes.tagCount(); i++) {
			list.add(i + ") x: " + nodes.getCompoundTagAt(i).getInteger("x") + " y: " + nodes.getCompoundTagAt(i).getInteger("y"));
		}
		NBTTagList leftLines = NBTHelper.getTagList(stack, "linesLeft", NBT.TAG_COMPOUND);
		NBTTagList rightLines = NBTHelper.getTagList(stack, "linesRight", NBT.TAG_COMPOUND);
		for (int i = 0; i < leftLines.tagCount(); i++) {
			list.add(i + ") left: " + CoordinatePair.readFromNBT(leftLines.getCompoundTagAt(i)).getX() + ":" + CoordinatePair.readFromNBT(leftLines.getCompoundTagAt(i)).getY() + " right: " + CoordinatePair.readFromNBT(rightLines.getCompoundTagAt(i)).getX() + ":" + CoordinatePair.readFromNBT(rightLines.getCompoundTagAt(i)).getY());
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (NBTHelper.getTagList(stack, "nodes", NBT.TAG_COMPOUND).tagCount() == 0) {
			NBTTagList nodes = new NBTTagList();
			for (int i = 0; i < 8; i++) {
				NBTTagCompound node = new NBTTagCompound();
				node.setInteger("x", 23 + new java.util.Random().nextInt(82));
				node.setInteger("y", 23 + new java.util.Random().nextInt(82));
				nodes.appendTag(node);
			}
			NBTHelper.setTagList(stack, "nodes", nodes);
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int damage) {
		return icons[damage];
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.icons = new IIcon[runes.length];
		for (int i = 0; i < this.icons.length; i++) {
			this.icons[i] = icons.registerIcon(ModProps.modid + ":runes/basicRune" + runes[i]);
		}
	}

}
