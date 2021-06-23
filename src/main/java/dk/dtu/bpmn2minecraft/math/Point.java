package dk.dtu.bpmn2minecraft.math;

public class Point {
	public int x;
	public int z;
	
	public Point(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	@Override
	public String toString() {
		return this.x + "," + this.z;
	}
}
