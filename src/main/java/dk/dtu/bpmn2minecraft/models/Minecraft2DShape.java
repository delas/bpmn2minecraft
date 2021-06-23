package dk.dtu.bpmn2minecraft.models;

public class Minecraft2DShape {
	private int x;
	private int y = 4;
	private int z;
	private int width;
	private int length;

	public Minecraft2DShape(int x, int y, int z, int width, int length) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.length = length;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
