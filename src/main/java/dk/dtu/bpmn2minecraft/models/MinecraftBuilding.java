package dk.dtu.bpmn2minecraft.models;

import java.util.Random;

import dk.dtu.bpmn2minecraft.bpmn.BpmnMinecraftProcess;
import net.morbz.minecraft.blocks.IBlock;
import net.morbz.minecraft.blocks.PlanksBlock;
import net.morbz.minecraft.blocks.SignBlock;
import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.blocks.StoneBrickBlock;
import net.morbz.minecraft.blocks.TorchBlock;
import net.morbz.minecraft.blocks.states.Facing4State;
import net.morbz.minecraft.blocks.states.Facing5State;
import net.morbz.minecraft.blocks.states.WoodState;
import net.morbz.minecraft.world.World;

public class MinecraftBuilding extends MinecraftObject {

	private static final IBlock FLOOR_BLOCK = new PlanksBlock(WoodState.SPRUCE);
	private static final IBlock COLUMN_BLOCK_1 = StoneBrickBlock.NORMAL;
	private static final IBlock COLUMN_BLOCK_2 = StoneBrickBlock.MOSSY;
	private static final IBlock WALL_BLOCK = new PlanksBlock(WoodState.OAK);
	private static final IBlock ROOF_BASE_BLOCK = StoneBrickBlock.NORMAL;
	private static final IBlock ROOF_TOP_BLOCK = new PlanksBlock(WoodState.SPRUCE);
	private static final IBlock BORDER_BLOCK = SimpleBlock.GRAVEL;
	
	private String label = null;
	private Random random = new Random();
	
	public MinecraftBuilding(Minecraft2DShape shape, String label) {
		super(shape);
		
		this.label = label;
	}

	@Override
	public void draw(BpmnMinecraftProcess plane) {
		World world = plane.getWorld();
		int x = shape.getX();
		int y = shape.getY();
		int z = shape.getZ();
		int width = shape.getWidth();
		int length = shape.getLength();
		
		// floor
		for (int i = 0; i < width + 2; i++) {
			for (int j = 0; j < length + 2; j++) {
				world.setBlock(x + i, y - 1, z + j, FLOOR_BLOCK);
			}
		}
		
		// border
		for (int i = -1 ; i < length + 2; i++) {
			world.setBlock(x - 1, y - 1, z + i, BORDER_BLOCK);
			world.setBlock(x + width + 1, y - 1, z + i, BORDER_BLOCK);
		}
		for (int i = 0; i <= width; i++) {
			world.setBlock(x + i, y - 1, z - 1, BORDER_BLOCK);
			world.setBlock(x + i, y - 1, z + length + 1, BORDER_BLOCK);
		}

		// columns
		for (int i = 0; i < 3; i++) {
			world.setBlock(x, y + i, z, random.nextBoolean()? COLUMN_BLOCK_1 : COLUMN_BLOCK_2);
			world.setBlock(x + width, y + i, z, random.nextBoolean()? COLUMN_BLOCK_1 : COLUMN_BLOCK_2);
			world.setBlock(x, y + i, z + length, random.nextBoolean()? COLUMN_BLOCK_1 : COLUMN_BLOCK_2);
			world.setBlock(x + width, y + i, z + length, random.nextBoolean()? COLUMN_BLOCK_1 : COLUMN_BLOCK_2);
		}
		
		// walls
		// north wall
		for (int i = 1; i < width; i++) {
			if (i != width / 2) {
				world.setBlock(x + i, y, z, WALL_BLOCK);
			}
			if (i % 2 == 0) {
				world.setBlock(x + i, y + 1, z, WALL_BLOCK);
				world.setBlock(x + i, y + 1, z + 1, new TorchBlock(Facing5State.SOUTH));
			}
			world.setBlock(x + i, y + 2, z, WALL_BLOCK);
		}
		world.setBlock(x + width / 2 + 1, y, z - 1, new SignBlock(label, Facing4State.NORTH));
		
		// south wall
		for (int i = 1; i < width; i++) {
			if (i != width / 2) {
				world.setBlock(x + i, y, z + length, WALL_BLOCK);
			}
			if (i % 2 == 0) {
				world.setBlock(x + i, y + 1, z + length, WALL_BLOCK);
				world.setBlock(x + i, y + 1, z + length - 1, new TorchBlock(Facing5State.NORTH));
			}
			world.setBlock(x + i, y + 2, z + length, WALL_BLOCK);
		}
		world.setBlock(x + width / 2 + 1, y, z + length + 1, new SignBlock(label, Facing4State.SOUTH));
		
		// west wall
		for (int i = 1; i < length; i++) {
			if (i != width / 2) {
				world.setBlock(x, y, z + i, WALL_BLOCK);
			}
			if (i % 2 == 0) {
				world.setBlock(x, y + 1, z + i, WALL_BLOCK);
				world.setBlock(x + 1, y + 1, z + i, new TorchBlock(Facing5State.EAST));
			}
			world.setBlock(x, y + 2, z + i, WALL_BLOCK);
		}
		world.setBlock(x - 1, y, z + length / 2 - 1, new SignBlock(label, Facing4State.WEST));
			
		// east wall
		for (int i = 1; i < length; i++) {
			if (i != width / 2) {
				world.setBlock(x + width, y, z + i, WALL_BLOCK);
			}
			if (i % 2 == 0) {
				world.setBlock(x + width, y + 1, z + i, WALL_BLOCK);
				world.setBlock(x + width - 1, y + 1, z + i, new TorchBlock(Facing5State.WEST));
			}
			world.setBlock(x + width, y + 2, z + i, WALL_BLOCK);
		}
		world.setBlock(x + width + 1, y, z + length / 2 - 1, new SignBlock(label, Facing4State.EAST));
		
		// roof
		for (int i = -1; i <= width+1; i++) {
			world.setBlock(x + i, y + 3, z - 1, ROOF_BASE_BLOCK);
			world.setBlock(x + i, y + 3, z, ROOF_BASE_BLOCK);
			world.setBlock(x + i, y + 3, z + length, ROOF_BASE_BLOCK);
			world.setBlock(x + i, y + 3, z + length + 1, ROOF_BASE_BLOCK);
		}
		for (int i = 1; i < length; i++) {
			world.setBlock(x - 1, y + 3, z + i, ROOF_BASE_BLOCK);
			world.setBlock(x, y + 3, z + i, ROOF_BASE_BLOCK);
			world.setBlock(x + width, y + 3, z + i, ROOF_BASE_BLOCK);
			world.setBlock(x + width + 1, y + 3, z + i, ROOF_BASE_BLOCK);
		}
		for (int i = 1; i < width; i++) {
			for (int j = 1; j < length; j++) {
				world.setBlock(x + i, y + 4, z + j, ROOF_TOP_BLOCK);
			}
		}
	}

}
