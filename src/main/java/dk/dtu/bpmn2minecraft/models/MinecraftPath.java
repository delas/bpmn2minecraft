package dk.dtu.bpmn2minecraft.models;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.bpmn2minecraft.bpmn.BpmnMinecraftProcess;
import dk.dtu.bpmn2minecraft.math.Bresenham;
import dk.dtu.bpmn2minecraft.math.Point;
import net.morbz.minecraft.blocks.IBlock;
import net.morbz.minecraft.blocks.MagentaGlazedTerracotta;
import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.blocks.states.Facing5State;
import net.morbz.minecraft.world.World;

public class MinecraftPath implements MinecraftDrawable {

	private IBlock material = SimpleBlock.GRAVEL;
	private int y = 3;
	private ArrayList<Point> waypoints = new ArrayList<Point>();
	
	public void addWaypoint(int x, int z) {
		waypoints.add(new Point(x, z));
	}
	
	@Override
	public void draw(BpmnMinecraftProcess plane) {
		World world = plane.getWorld();
		
		for (int i = 0; i < waypoints.size() - 1; i++) {
			Point start = waypoints.get(i);
			Point end = waypoints.get(i + 1);
			List<Point> path = Bresenham.findLine(start, end);
			Facing5State dirSource = null;
			if (path.size() > 1) {
				Point p0 = path.get(0);
				Point p1 = path.get(1);
				if (p0.z == p1.z && p0.x < p1.x) {
					dirSource = Facing5State.EAST;
				} else if (p0.z == p1.z && p0.x > p1.x) {
					dirSource = Facing5State.SOUTH;
				} else if (p0.z > p1.z && p0.x == p1.x) {
					dirSource = Facing5State.NORTH;
				} else if (p0.z < p1.z && p0.x == p1.x) {
					dirSource = Facing5State.WEST;
				}
			}
			
			for (int j = 0; j < path.size() - 1; j++) {
				Point p = path.get(j);
				if (dirSource == null) {
					world.setBlock(p.x, y, p.z, material);
				} else {
					world.setBlock(p.x, y, p.z, new MagentaGlazedTerracotta(dirSource));
				}
			}
			
		}
	}
	
}
