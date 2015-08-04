package astreaInfinitum.items.lore;

import java.util.ArrayList;
import java.util.List;

import astreaInfinitum.utils.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ItemLoreBook extends Item {

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean part4) {
		if (NBTHelper.getTag(stack) != null) {
			for (String s : readLoreFromNBT(NBTHelper.getTag(stack))) {
				list.add(s);
			}
		}
	}

	public ArrayList<String> readLoreFromNBT(NBTTagCompound tags) {
		ArrayList<String> lore = new ArrayList<String>();
		NBTTagList nbttaglist = tags.getTagList("Lore", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = (NBTTagCompound) nbttaglist.getCompoundTagAt(iter);

			lore.add(tagList.getString("name"));
		}
		return lore;
	}

	public void writeLoreToNBT(NBTTagCompound tags, String lore) {
		NBTTagList nbttaglist = new NBTTagList();
		NBTTagCompound tagList = new NBTTagCompound();
		tagList.setString("name", lore);
		nbttaglist.appendTag(tagList);
		tags.setTag("Lore", nbttaglist);
	}
}
