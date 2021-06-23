package dk.dtu.bpmn2minecraft.bpmn;

import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;

import dk.dtu.bpmn2minecraft.models.Minecraft2DShape;
import dk.dtu.bpmn2minecraft.models.MinecraftWell;
import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.blocks.StoneBlock;

public class BpmnMinecraftEndEvent extends MinecraftWell {

	public BpmnMinecraftEndEvent(BpmnShape shape) {
		super(
			new Minecraft2DShape(
				shape.getBounds().getX().intValue() / 10,	// x
				4,											// y
				shape.getBounds().getY().intValue() / 10,	// z
				shape.getBounds().getWidth().intValue() / 10,	// width
				shape.getBounds().getHeight().intValue() / 10	// length
				),
			StoneBlock.STONE,	// border
			SimpleBlock.FENCE,	// fence
			SimpleBlock.LAVA	// content
			);
	}
}
