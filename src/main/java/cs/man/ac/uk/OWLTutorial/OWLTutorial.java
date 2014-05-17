package cs.man.ac.uk.OWLTutorial;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class OWLTutorial {

	public static void main (String[] args) {
		System.out.println("Test");
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI ontologyIRI = IRI.create("http://www.geneontology.org/ontology/obo_format_1_2/gene_ontology_ext.obo");
		try {
			OWLOntology ontology = man.loadOntology(ontologyIRI);
			System.out.println(ontology.getLogicalAxiomCount());
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
}