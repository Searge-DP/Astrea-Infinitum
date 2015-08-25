package astreaInfinitum.api.eco;

import net.minecraft.world.World;

public interface IEcoBlock {

	public EnumEco getEco(World world, int x, int y, int z);

}
