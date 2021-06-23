package dk.dtu.bpmn2minecraft.models;

import dk.dtu.bpmn2minecraft.bpmn.BpmnMinecraftProcess;
import net.morbz.minecraft.blocks.IBlock;
import net.morbz.minecraft.blocks.SignBlock;
import net.morbz.minecraft.blocks.states.Facing4State;
import net.morbz.minecraft.world.World;

public class MinecraftWell extends MinecraftObject {
	
	private IBlock border;
	private IBlock fence;
	private IBlock content;
	
	public MinecraftWell(Minecraft2DShape shape, IBlock border, IBlock fence, IBlock content) {
		super(shape);
		this.border = border;
		this.fence = fence;
		this.content = content;
	}

	@Override
	public void draw(BpmnMinecraftProcess plane) {
		World world = plane.getWorld();
		int x = shape.getX();
		int y = shape.getY();
		int z = shape.getZ();
		int width = shape.getWidth();
		int length = shape.getLength();
		
		// border and fence
		for (int i = 1; i < width - 1; i++) {
			world.setBlock(x + i, y, z, border);
			world.setBlock(x + i, y, z + length - 1, border);
			
			for (int j = 1; j <= 2; j++) {
				world.setBlock(x + i, y + j, z, fence);
				world.setBlock(x + i, y + j, z + length - 1, fence);
			}
		}
		for (int i = 1; i < length - 1; i++) {
			world.setBlock(x, y, z + i, border);
			world.setBlock(x + width - 1, y, z + i, border);
			
			for (int j = 1; j <= 2; j++) {
				world.setBlock(x, y + j, z + i, fence);
				world.setBlock(x + width - 1, y + j, z + i, fence);
			}
		}
		
		// content
		for (int i = 1; i < width - 1; i++) {
			for (int j = 1; j < length - 1; j++) {
				world.setBlock(x + i, y, z + j, content);
				world.setBlock(x + i, y + 2, z + j, fence);
			}
		}
		
		world.setBlock(x, y, z, new SignBlock("Start here", Facing4State.WEST));
	}
}
