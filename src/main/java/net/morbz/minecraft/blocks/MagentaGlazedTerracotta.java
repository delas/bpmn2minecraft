package net.morbz.minecraft.blocks;

import net.morbz.minecraft.blocks.states.Facing5State;

public class MagentaGlazedTerracotta extends Facing5Block {

	public MagentaGlazedTerracotta(Facing5State facing) {
		super(facing);
	}

	@Override
	protected Material getMaterial() {
		return Material.MAGENTA_GLAZED_TERRACOTTA;
	}

}
