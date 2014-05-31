package cs.man.ac.uk.OWLTutorial;

public class OWLTutorial {

	public static void main (String[] args) {		
		OWLHandler handler = new OWLHandler();
		handler.loadOntology("file:/Users/AdamNogradi/Documents/workspace/OWLTutorial/goOne.owl");
		handler.filterObsoleteClasses();
	}
}