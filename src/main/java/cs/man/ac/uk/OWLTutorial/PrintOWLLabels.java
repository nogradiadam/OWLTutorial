package cs.man.ac.uk.OWLTutorial;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

public class PrintOWLLabels {

	private final String OBSOLETE_CLASS_URI = "<http://www.geneontology.org/formats/oboInOwl#ObsoleteClass>";

	public void shouldCreateAndReadAnnotations()
			throws OWLOntologyCreationException {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLOntology ont = man.loadOntologyFromOntologyDocument(IRI
				.create("file:/Users/AdamNogradi/Documents/workspace/OWLTutorial/goOne.owl"));
		System.out.println("Loaded: " + ont.getOntologyID());

		OWLDataFactory df = man.getOWLDataFactory();
		OWLAnnotationProperty label = df
				.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_CLASS.getIRI());

		PrintWriter writer = null;
		try {
			writer = new PrintWriter (new FileWriter("output_no_obsolete.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean isObsoleteClass = false;

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
						System.out.println(cls + " -> " + val.getLiteral());
						writer.println(val.getLiteral());
					}
				}
			}
			
//			if (!isObsoleteClass){
//				writer.println(cls);
//			}

			isObsoleteClass = false;
		}

		writer.close();
	}

}
