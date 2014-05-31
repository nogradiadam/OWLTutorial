package cs.man.ac.uk.OWLTutorial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

public class OWLHandler {

	private final String OBSOLETE_CLASS_URI = "<http://www.geneontology.org/formats/oboInOwl#ObsoleteClass>";
	private OWLOntology ont;
	private OWLDataFactory df;
	private PrintWriter writer = null;

	public void loadOntology (String ontURL) {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		try {
			ont = man.loadOntologyFromOntologyDocument(IRI.create(ontURL));
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		System.out.println("Loaded: " + ont.getOntologyID());

		df = man.getOWLDataFactory();
	}

	public void filterObsoleteClasses() {
		try {
			writer = new PrintWriter (new FileWriter("output_no_obsolete.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean isObsoleteClass = false;
		OWLAnnotationProperty label = df
				.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI());

		for (OWLClass cls : ont.getClassesInSignature()) {
			if (!(cls.getSuperClasses(ont).isEmpty())) {
				for (OWLClassExpression clsExpr : cls.getSuperClasses(ont)) {
					if (clsExpr.toString().equals(OBSOLETE_CLASS_URI)) {
						isObsoleteClass = true;
					}
				}
			}

			if (!isObsoleteClass) {
				for (OWLAnnotation annotation : cls.getAnnotations(ont, label)) {
					if (annotation.getValue() instanceof OWLLiteral) {
						OWLLiteral val = (OWLLiteral) annotation.getValue();
						writer.println(val.getLiteral());
					}
				}
			}
			isObsoleteClass = false;
		}

		writer.close();
	}
}
