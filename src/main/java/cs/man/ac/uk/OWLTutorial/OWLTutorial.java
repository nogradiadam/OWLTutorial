package cs.man.ac.uk.OWLTutorial;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLTutorial {

	public static void main (String[] args) {		
		PrintOWLLabels printer = new PrintOWLLabels();
		try {
			printer.shouldCreateAndReadAnnotations();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
}