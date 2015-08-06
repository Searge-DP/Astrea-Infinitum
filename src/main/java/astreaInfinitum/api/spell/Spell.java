package astreaInfinitum.api.spell;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import astreaInfinitum.api.runes.EnumRuneFunction;

public class Spell {

	public String name;
	public EnumRuneFunction function;
	public int spellLevel;

	
	public Spell(String name, EnumRuneFunction function) {
		this.name = name;
		this.function = function;
	}
	public Spell(String name, EnumRuneFunction function, int spellLevel) {
		this.name = name;
		this.function = function;
		this.spellLevel = spellLevel;
	}

	public static Spell readSpellFromNbt(NBTTagCompound tags) {
		String name;
		EnumRuneFunction function;
		int spellLevel;
		NBTTagCompound nbt = tags.getCompoundTag("Spell");
		name = nbt.getString("name");
		function = EnumRuneFunction.valueOf(nbt.getString("function"));
		spellLevel = nbt.getInteger("spellLevel");
		return new Spell(name, function, spellLevel);
	}

	public static void WriteSpellFromNbt(NBTTagCompound tags, Spell spell) {
		NBTTagList nbttaglist = new NBTTagList();
		NBTTagCompound tagList = new NBTTagCompound();
		tagList.setString("name", spell.name);
		tagList.setString("function", spell.function.name);
		tagList.setInteger("spellLevel", spell.spellLevel);
		nbttaglist.appendTag(tagList);
		tags.setTag("Spell", nbttaglist);
	}
}
