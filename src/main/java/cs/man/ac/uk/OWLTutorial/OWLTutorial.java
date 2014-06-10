package cs.man.ac.uk.OWLTutorial;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.semanticweb.owlapi.model.OWLClass;

public class OWLTutorial {
	
	private static PrintWriter writer = null;
	
	public static void main (String[] args) {
		try {
			writer = new PrintWriter (new FileWriter("classes_with_definitions.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File goont = new File("/Users/AdamNogradi/Documents/workspace/OWLTutorial/go.owl");
		OWLHandler handler = new OWLHandler();
		handler.loadOntology(goont);
		List<OWLClass> nonObsoleteClasses = handler.getNonObsoleteClasses();
		Map<OWLClass, String> classesWithDefinitions =  handler.getClassesWithDefinitions(nonObsoleteClasses);
		
		for (Map.Entry<OWLClass, String> entry : classesWithDefinitions.entrySet()) {
			writer.println("Class: " + entry.getKey() + "    Definition: " + entry.getValue());
		}
		
		writer.close();
	}
}