package astreaInfinitum.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import astreaInfinitum.api.EnumPlayerEco;
import astreaInfinitum.api.recipes.RecipeEcoAltar;
import astreaInfinitum.api.recipes.RecipeRegistry;
import astreaInfinitum.items.AIItems;
import astreaInfinitum.utils.NBTHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {

	public static void postInit() {
		RecipeRegistry.registerEcoAltarRecipe(new RecipeEcoAltar(new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), EnumPlayerEco.light, 12));
		RecipeRegistry.registerEcoAltarRecipe(new RecipeEcoAltar(new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt), EnumPlayerEco.dark, 12));
		List<String> lores = new ArrayList<String>();
		lores.add("Current researched chapters are:");
		lores.add("Something Old");
		lores.add("Something New");
		lores.add("Something Blue... and Green, Red and Yellow");

		ItemStack stack = new ItemStack(AIItems.loreBook);
		writeLoreToNBT(NBTHelper.getTag(stack), lores);
		GameRegistry.addRecipe(new ShapelessOreRecipe(stack, Items.stick));
	}

	private static void writeLoreToNBT(NBTTagCompound tags, String lore) {
		NBTTagList nbttaglist = new NBTTagList();
		NBTTagCompound tagList = new NBTTagCompound();
		tagList.setInteger("position", tagList.getInteger("position") + 1);
		tagList.setString("name", lore);
		nbttaglist.appendTag(tagList);
		tags.setTag("Lore", nbttaglist);
	}

	private static void writeLoreToNBT(NBTTagCompound tags, List<String> lore) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int iter = 0; iter < lore.size(); iter++) {
			NBTTagCompound tagList = new NBTTagCompound();
			tagList.setByte("position", (byte) iter);
			tagList.setString("name", lore.get(iter));
			nbttaglist.appendTag(tagList);
		}

		tags.setTag("Lore", nbttaglist);
	}

	private static ArrayList<String> readLoreFromNBT(NBTTagCompound tags) {
		ArrayList<String> lore = new ArrayList<String>();
		NBTTagList nbttaglist = tags.getTagList("Lore", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = (NBTTagCompound) nbttaglist.getCompoundTagAt(iter);
			lore.set(iter, tagList.getString("name"));
		}
		return lore;
	}
}
