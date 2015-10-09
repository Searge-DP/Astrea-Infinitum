package astreaInfinitum.items.runes;

import java.util.List;

import fluxedCore.util.CoordinatePair;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import astreaInfinitum.ModProps;
import astreaInfinitum.utils.NBTHelper;

public class ItemRune extends Item {

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		super.addInformation(stack, player, list, p_77624_4_);
		NBTTagList nodes = NBTHelper.getTagList(stack, "nodes", NBT.TAG_COMPOUND);
		for (int i = 0; i < nodes.tagCount(); i++) {
			list.add(i + ") x: " + nodes.getCompoundTagAt(i).getInteger("x") + " y: " + nodes.getCompoundTagAt(i).getInteger("y"));
		}
		NBTTagList leftLines= NBTHelper.getTagList(stack, "linesLeft", NBT.TAG_COMPOUND);
		NBTTagList rightLines= NBTHelper.getTagList(stack, "linesRight", NBT.TAG_COMPOUND);
		for (int i = 0; i < leftLines.tagCount(); i++) {
			list.add(i + ") left: " + CoordinatePair.readFromNBT(leftLines.getCompoundTagAt(i)).getX() + ":" + CoordinatePair.readFromNBT(leftLines.getCompoundTagAt(i)).getY() + " right: " + CoordinatePair.readFromNBT(rightLines.getCompoundTagAt(i)).getX() + ":" + CoordinatePair.readFromNBT(rightLines.getCompoundTagAt(i)).getY());
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (NBTHelper.getTagList(stack, "nodes", NBT.TAG_COMPOUND).tagCount()==0) {
			NBTTagList nodes = new NBTTagList();
			for (int i = 0; i < 8; i++) {
				NBTTagCompound node = new NBTTagCompound();
				node.setInteger("x", new java.util.Random().nextInt(120));
				node.setInteger("y", new java.util.Random().nextInt(120));
				nodes.appendTag(node);
			}
			NBTHelper.setTagList(stack, "nodes", nodes);
		}
	}

	@Override
	public void registerIcons(IIconRegister icons) {
		this.itemIcon = icons.registerIcon(ModProps.modid + ":runes/basicRune");
	}

}
