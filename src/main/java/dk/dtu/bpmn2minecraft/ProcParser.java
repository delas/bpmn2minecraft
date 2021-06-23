package dk.dtu.bpmn2minecraft;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Optional;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;

public class ProcParser {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("C:\\Users\\andbur\\Desktop\\d.bpmn");
		
		BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);
		
		ModelElementType taskType = modelInstance.getModel().getType(Task.class);
		Collection<ModelElementInstance> taskInstances = modelInstance.getModelElementsByType(taskType);

		for (ModelElementInstance i : taskInstances) {
			Task t = (Task) i;
			Optional<BpmnShape> shape = modelInstance.getModelElementsByType(BpmnShape.class).stream().filter(item -> item.getBpmnElement().equals(t)).findFirst();
			if (shape.isEmpty()) {
				continue;
			}
			
			System.out.print(i.getElementType().getTypeName() + " - ");
			System.out.print(t.getName() + " - ");
			System.out.print(t.getId() + " - ");
			
			double x = shape.get().getBounds().getX();
			double y = shape.get().getBounds().getY();
			double width = shape.get().getBounds().getWidth();
			double height = shape.get().getBounds().getHeight();
			
			System.out.print(x + "," + y + " ; " + width +","+height);
			
			System.out.println("");
			
		}
		
		System.out.println("done");
		
	}
}
