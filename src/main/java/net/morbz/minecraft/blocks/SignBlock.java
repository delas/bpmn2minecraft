package net.morbz.minecraft.blocks;

import org.jnbt.ByteTag;
import org.jnbt.IntTag;
import org.jnbt.StringTag;
import org.jnbt.Tag;

import net.morbz.minecraft.blocks.states.Facing4State;
import net.morbz.minecraft.tags.CompoundTagFactory;

public class SignBlock extends IEntity implements IBlock {

	private String label;
	private Facing4State facing;
	
	public SignBlock(String label, Facing4State facing) {
		this.label = label;
		this.facing = facing;
	}
	
	@Override
	public byte getBlockId() {
		return (byte) Material.STANDING_SIGN.getValue();
	}

	@Override
	public byte getBlockData() {
		// Facing direction
		byte data = 0;
		switch(facing) {
		case EAST:
			data = 12;
			break;
		case WEST:
			data = 4;
			break;
		case SOUTH:
			data = 0;
			break;
		case NORTH:
			data = 8;
			break;
		}
		return data;
	}

	@Override
	public int getTransparency() {
		return 1;
	}

	@Override
	public Tag getTag() {
		CompoundTagFactory entity = new CompoundTagFactory("");
		entity.set(new StringTag("id", "minecraft:sign"));
		entity.set(new ByteTag("keepPacked", (byte) 0));
		entity.set(new IntTag("x", this.x));
		entity.set(new IntTag("y", this.y));
		entity.set(new IntTag("z", this.z));
		entity.set(new StringTag("Color", "black"));
		entity.set(new ByteTag("GlowingText", (byte) 0));
		entity.set(new StringTag("Text1", "{\"text\":\"" + label + "\"}"));
		entity.set(new StringTag("Text2", "{\"text\":\"\"}"));
		entity.set(new StringTag("Text3", "{\"text\":\"\"}"));
		entity.set(new StringTag("Text4", "{\"text\":\"\"}"));
		return entity.getTag();
	}

}
