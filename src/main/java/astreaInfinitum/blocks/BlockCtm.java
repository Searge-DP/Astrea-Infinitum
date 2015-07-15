package astreaInfinitum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import astreaInfinitum.ModProps;

import com.cricketcraft.ctmlib.ISubmapManager;

public abstract class BlockCtm extends Block{

	protected BlockCtm(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
	
	
	public abstract ISubmapManager getSubMap();
	
	
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		getSubMap().registerIcons(ModProps.MODID, this, icon);
	}
	

}
