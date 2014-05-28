package cs.man.ac.uk.OWLTutorial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
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

	public void shouldCreateAndReadAnnotations()
            throws OWLOntologyCreationException {
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        OWLOntology ont = man.loadOntologyFromOntologyDocument(IRI
                .create("file:/Users/AdamNogradi/Documents/workspace/OWLTutorial/goOne.owl"));
        System.out.println("Loaded: " + ont.getOntologyID());
        OWLDataFactory df = man.getOWLDataFactory();
       
        
        OWLAnnotationProperty label = df
                .getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI());
        OWLAnnotationProperty subclass = df
                .getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_SUBCLASS_OF.getIRI());
                
        PrintWriter writer = null;
        try {
			writer = new PrintWriter ("output_no_obsolete.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        Set<OWLClass> myClasses = ont.getClassesInSignature();
        for (OWLClass c : myClasses) {
        	System.out.println("*************Classname:********************");
        	System.out.println(c.toString());
        	
        	System.out.println("------------Annotations:--------------------");
        	for (OWLAnnotation a : c.getAnnotations(ont)) {
        		System.out.println(a.toString());
        	}
        	
        	System.out.println("-------------Subclasses:---------");
        	for (OWLClassExpression e : c.getSubClasses(ont)) {
        		System.out.println(e.toString());
        	}
        }
//        OWLClass firstClass = myClasses.iterator().next();
//        System.out.println(firstClass.toString());
//        Set<OWLAnnotation> annotations = firstClass.getAnnotations(ont);
//        for (OWLAnnotation a : annotations) {
//        	System.out.println("here");
//        	System.out.println(a.toString());
//        }
//        for (OWLClass cls : ont.getClassesInSignature()) {
//            for (OWLAnnotation annotation : cls.getAnnotations(ont, label)) {
//                if (annotation.getValue() instanceof OWLLiteral) {
//                    OWLLiteral val = (OWLLiteral) annotation.getValue();
//                        System.out.println(cls + " -> " + val.getLiteral());
//                        writer.println(val.getLiteral());
//                }
//            }
//        }
        
//        for (OWLClass cls : ont.getClassesInSignature()) {
//        	System.out.println(cls);
//            for (OWLAnnotation annotation : cls.getAnnotations(ont)) {
//            	System.out.println(annotation.getValue());
//                if (annotation.getValue() instanceof OWLLiteral) {
//                    OWLLiteral val = (OWLLiteral) annotation.getValue();
//                        System.out.println(cls + " -> " + val.getLiteral());
//                        writer.println(val.getLiteral());
//                }
//            }
//        }
    }

}
