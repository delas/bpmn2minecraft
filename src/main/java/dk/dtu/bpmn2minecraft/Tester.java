package dk.dtu.bpmn2minecraft;

import java.io.IOException;

import net.morbz.minecraft.blocks.IBlock;
import net.morbz.minecraft.blocks.Material;
import net.morbz.minecraft.blocks.PlanksBlock;
import net.morbz.minecraft.blocks.RedstoneTorchBlock;
import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.blocks.StoneBrickBlock;
import net.morbz.minecraft.blocks.states.WoodState;
import net.morbz.minecraft.level.FlatGenerator;
import net.morbz.minecraft.level.GameType;
import net.morbz.minecraft.level.IGenerator;
import net.morbz.minecraft.level.Level;
import net.morbz.minecraft.world.DefaultLayers;
import net.morbz.minecraft.world.World;

public class Tester {

	
	public static void drawHome(World world, int x, int y, int z) {
		// floor
		IBlock floorBlock = new PlanksBlock(WoodState.SPRUCE);
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				world.setBlock(x + i, y - 1, z + j, floorBlock);

		// columns
		for (int i = 0; i < 3; i++)
			world.setBlock(x, y + i, z, StoneBrickBlock.NORMAL);
		for (int i = 0; i < 3; i++)
			world.setBlock(x + 4, y + i, z, StoneBrickBlock.MOSSY);
		for (int i = 0; i < 3; i++)
			world.setBlock(x, y + i, z + 4, StoneBrickBlock.NORMAL);
		for (int i = 0; i < 3; i++)
			world.setBlock(x + 4, y + i, z + 4, StoneBrickBlock.NORMAL);
		
		// walls
		IBlock wallBlock = new PlanksBlock(WoodState.OAK);
		// north wall
		for (int i = 1; i < 4; i++)
			world.setBlock(x + i, y, z, wallBlock);
		for (int i = 1; i < 4; i+=2)
			world.setBlock(x + i, y + 1, z, wallBlock);
		for (int i = 1; i < 4; i++)
			world.setBlock(x + i, y + 2, z, wallBlock);
		// south wall
		for (int i = 1; i < 4; i++)
			world.setBlock(x + i, y, z + 4, wallBlock);
		for (int i = 1; i < 4; i+=2)
			world.setBlock(x + i, y + 1, z + 4, wallBlock);
		for (int i = 1; i < 4; i++)
			world.setBlock(x + i, y + 2, z + 4, wallBlock);
		// west wall
		for (int i = 1; i < 4; i++)
			world.setBlock(x, y, z + i, wallBlock);
		for (int i = 1; i < 4; i+=2)
			world.setBlock(x, y + 1, z + i, wallBlock);
		for (int i = 1; i < 4; i++)
			world.setBlock(x, y + 2, z + i, wallBlock);
		// east wall
		for (int i = 1; i < 4; i+=2)
			world.setBlock(x + 4, y, z + i, wallBlock);
		for (int i = 1; i < 4; i+=2)
			world.setBlock(x + 4, y + 1, z + i, wallBlock);
		for (int i = 1; i < 4; i++)
			world.setBlock(x + 4, y + 2, z + i, wallBlock);
		
		// roof
		IBlock roofBaseBlock = StoneBrickBlock.NORMAL;
		for (int i = 0; i < 6; i++) {
			world.setBlock(x + i, y + 3, z, roofBaseBlock);
			world.setBlock(x + i, y + 3, z + 4, roofBaseBlock);
		}
		for (int i = 1; i < 4; i++) {
			world.setBlock(x, y + 3, z + i, roofBaseBlock);
			world.setBlock(x + 5, y + 3, z + i, roofBaseBlock);
		}
		IBlock roofTopBlock = new PlanksBlock(WoodState.SPRUCE);
		for (int i = 1; i < 5; i++)
			for (int j = 1; j < 4; j++)
				world.setBlock(x + i, y + 4, z + j, roofTopBlock);

	}
	
	
	
	public static void main(String[] args) throws IOException {
		DefaultLayers layers = new DefaultLayers();
		layers.setLayer(0, Material.BEDROCK);
		layers.setLayers(1, 2, Material.DIRT);
		layers.setLayer(3, Material.GRASS);

		IGenerator generator = new FlatGenerator(layers);

		Level level = new Level("DiamondWorld", generator);
		level.setGameType(GameType.CREATIVE);
		level.setSpawnPoint(0, 0, 21);

		World world = new World(level, layers);
		
		for (int i = 0; i<10; i++)
			drawHome(world, 0, 4, i*10);
		
		

//		// Create a huge structure of glass that has an area of 100x100 blocks and is 50 blocks 
//		// height. On top of the glass structure we put a layer of grass.
//		for(int x = 0; x < 100; x++) {
//			for(int z = 0; z < 100; z++) {
//				// Set glass
//				for(int y = 0; y < 50; y++) {
//					world.setBlock(x, y, z, SimpleBlock.DIAMOND_ORE);
//				}
//			
//				// Set grass
//				world.setBlock(x, 50, z, SimpleBlock.GRASS);
//			}
//		}
//
//		// Now we create the door. It consists of 2 blocks, that's why we can't use a SimpleBlock 
//		// here.
//		world.setBlock(50, 51, 50, DoorBlock.makeLower(DoorMaterial.OAK, Facing4State.EAST, false));
//		world.setBlock(50, 52, 50, DoorBlock.makeUpper(DoorMaterial.OAK, HingeSide.LEFT));

		world.save();
		
		System.out.println("done");

	}
}
