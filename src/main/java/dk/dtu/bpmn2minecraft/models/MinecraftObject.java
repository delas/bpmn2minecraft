package dk.dtu.bpmn2minecraft.models;

public abstract class MinecraftObject implements MinecraftDrawable {
	
	protected Minecraft2DShape shape;

	public MinecraftObject(Minecraft2DShape shape) {
		this.shape = shape;
	}
}
