package dk.dtu.bpmn2minecraft.bpmn;

import java.io.IOException;

import net.morbz.minecraft.blocks.Material;
import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.level.FlatGenerator;
import net.morbz.minecraft.level.GameType;
import net.morbz.minecraft.level.IGenerator;
import net.morbz.minecraft.level.Level;
import net.morbz.minecraft.world.DefaultLayers;
import net.morbz.minecraft.world.World;

public class BpmnMinecraftProcess {

	private String name;
	private World world;
	
	public BpmnMinecraftProcess(String name) {
		DefaultLayers layers = new DefaultLayers();
		layers.setLayer(0, Material.BEDROCK);
		layers.setLayers(1, 2, Material.DIRT);
		layers.setLayer(3, Material.GRASS);

		IGenerator generator = new FlatGenerator(layers);

		Level level = new Level(name, generator);
		level.setGameType(GameType.CREATIVE);
		level.setAllowCommands(true);
		level.setSpawnPoint(0, 5, 0);

		this.name = name;
		this.world = new World(level, layers);
		
//		for (int i = -250; i < 250; i++) {
//			for (int j = -250; j < 250; j++) {
//				world.setBlock(i, 0, j, SimpleBlock.BEDROCK);
//				world.setBlock(i, 1, j, SimpleBlock.GRASS);
//				world.setBlock(i, 2, j, SimpleBlock.GRASS);
//				world.setBlock(i, 3, j, SimpleBlock.GRASS);
//			}
//		}
	}
	
	public World getWorld() {
		return world;
	}
	
	public void save(String basePath, boolean overwrite) throws IOException {
		world.save(basePath, overwrite);
	}
	
	public void save() throws IOException {
		world.save();
	}
}
