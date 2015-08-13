package astreaInfinitum.api.spell;

import astreaInfinitum.api.runes.EnumRuneFunction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Spell {

	public String name;
	public EnumRuneFunction function;


	public Spell(String name, EnumRuneFunction function) {
		this.name = name;
		this.function = function;
	}

	public static Spell readSpellFromNbt(NBTTagCompound tags) {
		String name;
		EnumRuneFunction function;
		NBTTagCompound nbt = tags.getCompoundTag("Spell");
		name = nbt.getString("name");
		function = EnumRuneFunction.valueOf(nbt.getString("function"));
		return new Spell(name, function);
	}

	public static void WriteSpellFromNbt(NBTTagCompound tags, Spell spell) {
		NBTTagList nbttaglist = new NBTTagList();
		NBTTagCompound tagList = new NBTTagCompound();
		tagList.setString("name", spell.name);
		tagList.setString("function", spell.function.name);
		nbttaglist.appendTag(tagList);
		tags.setTag("Spell", nbttaglist);
	}
}
