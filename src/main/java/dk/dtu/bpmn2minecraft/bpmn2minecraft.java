package dk.dtu.bpmn2minecraft;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;

import dk.dtu.bpmn2minecraft.bpmn.BpmnMinecraftEndEvent;
import dk.dtu.bpmn2minecraft.bpmn.BpmnMinecraftProcess;
import dk.dtu.bpmn2minecraft.bpmn.BpmnMinecraftStartEvent;
import dk.dtu.bpmn2minecraft.bpmn.BpmnMinecraftTask;
import dk.dtu.bpmn2minecraft.models.MinecraftPath;
import net.morbz.minecraft.blocks.SignBlock;
import net.morbz.minecraft.blocks.SimpleBlock;

public class bpmn2minecraft {

	public static void main(String[] args) throws IOException {
		BpmnMinecraftProcess plane = new BpmnMinecraftProcess("test-1");
		File file = new File("C:\\Users\\andbur\\Desktop\\d.bpmn");
		
		BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);
		
		// create tasks
		ModelElementType taskType = modelInstance.getModel().getType(Task.class);
		for (ModelElementInstance i : modelInstance.getModelElementsByType(taskType)) {
			Task t = (Task) i;
			Optional<BpmnShape> shape = modelInstance.getModelElementsByType(BpmnShape.class).stream().filter(item -> item.getBpmnElement().equals(i)).findFirst();
			if (shape.isPresent()) {
				BpmnMinecraftTask minecraftTask = new BpmnMinecraftTask(t, shape.get());
				minecraftTask.draw(plane);
			}
		}
		
		// create start events
		ModelElementType startEventType = modelInstance.getModel().getType(StartEvent.class);
		for (ModelElementInstance i : modelInstance.getModelElementsByType(startEventType)) {
			Optional<BpmnShape> shape = modelInstance.getModelElementsByType(BpmnShape.class).stream().filter(item -> item.getBpmnElement().equals(i)).findFirst();
			if (shape.isPresent()) { 
				BpmnMinecraftStartEvent minecraftEvent = new BpmnMinecraftStartEvent(shape.get());
				minecraftEvent.draw(plane);
			}

		}
		
		// create end events
		ModelElementType endEventType = modelInstance.getModel().getType(EndEvent.class);
		for (ModelElementInstance i : modelInstance.getModelElementsByType(endEventType)) {
			Optional<BpmnShape> shape = modelInstance.getModelElementsByType(BpmnShape.class).stream().filter(item -> item.getBpmnElement().equals(i)).findFirst();
			if (shape.isPresent()) { 
				BpmnMinecraftEndEvent minecraftEvent = new BpmnMinecraftEndEvent(shape.get());
				minecraftEvent.draw(plane);
			}

		}
		
		// create paths
		ModelElementType edgeType = modelInstance.getModel().getType(SequenceFlow.class);
		for (ModelElementInstance i : modelInstance.getModelElementsByType(edgeType)) {
			Optional<BpmnEdge> shape = modelInstance.getModelElementsByType(BpmnEdge.class).stream().filter(item -> item.getBpmnElement().equals(i)).findFirst();
			if (shape.isPresent()) {
				MinecraftPath path = new MinecraftPath();
				for (Waypoint w : shape.get().getWaypoints()) {
					path.addWaypoint(w.getX().intValue() / 10, w.getY().intValue() / 10);
				}
				path.draw(plane);
			}
		}
		
//		SignBlock sign = new SignBlock();
//		plane.getWorld().setBlock(0, 4, 0, sign);
		
//		plane.getWorld().setBlock(1, 4, 1, SimpleBlock.BRICK_BLOCK);
		
		plane.save("C:\\Users\\andbur\\AppData\\Roaming\\.bpmn2minecraft\\saves\\", true);
	}

}
