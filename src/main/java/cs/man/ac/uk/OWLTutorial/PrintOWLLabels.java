package cs.man.ac.uk.OWLTutorial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

public class PrintOWLLabels {

	public void shouldCreateAndReadAnnotations()
            throws OWLOntologyCreationException {
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        OWLOntology ont = man.loadOntologyFromOntologyDocument(IRI
                .create("file:/Users/AdamNogradi/Documents/workspace/OWLTutorial/go.owl"));
        System.out.println("Loaded: " + ont.getOntologyID());
        OWLDataFactory df = man.getOWLDataFactory();
       
        
        OWLAnnotationProperty label = df
                .getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_CLASS.getIRI());
        PrintWriter writer = null;
        try {
			writer = new PrintWriter ("output_rdfsclass.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        for (OWLClass cls : ont.getClassesInSignature()) {
            for (OWLAnnotation annotation : cls.getAnnotations(ont, label)) {
                if (annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral val = (OWLLiteral) annotation.getValue();
                        System.out.println(cls + " -> " + val.getLiteral());
                        writer.println(val.getLiteral());
                }
            }
        }
    }

}
