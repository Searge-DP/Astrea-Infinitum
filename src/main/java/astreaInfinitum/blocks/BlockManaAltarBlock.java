package astreaInfinitum.blocks;

import com.cricketcraft.ctmlib.ISubmapManager;

import net.minecraft.block.material.Material;
import astreaInfinitum.AstreaInfinitum;
import astreaInfinitum.client.render.submaps.SubmapManagerCTM;

public class BlockManaAltarBlock extends BlockCtm  {

	protected BlockManaAltarBlock() {
		super(Material.rock);
	}
	
	@Override
	public int getRenderType() {
		return AstreaInfinitum.ctmID;
	}

	@Override
	public ISubmapManager getSubMap() {
		return new SubmapManagerCTM("manaAltarBlock");
	}

}
