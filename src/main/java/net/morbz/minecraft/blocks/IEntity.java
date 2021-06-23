package net.morbz.minecraft.blocks;

import org.jnbt.Tag;

public abstract class IEntity {

	protected int x, y, z;
	
	public void setCoords(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public abstract Tag getTag();
}
