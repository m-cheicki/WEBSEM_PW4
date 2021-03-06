package applications;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

import tools.JenaEngine;

public class Person {
	
	/**
	 * private constructor.
	 */
	private Person() {
	}
	
	static public Literal getName(Model model, String namespace, String name) {
		return JenaEngine.getValueOfDataTypeProperty(model, namespace, name, "name"); 
	}
	
	static private RDFNode getFather() {
		return null; 
	}
	
	static private RDFNode getMother() {
		return null; 
	}
	
	static public RDFNode getParents() {
		return null; 
	}
	
	static private RDFNode getBbrothers() {
		return null; 
	}
	
	static private RDFNode getSisters() {
		return null; 
	}
	
	static public RDFNode getSiblings() {
		return null; 
	}
	
	static private boolean isMarried() {
		return false; 
	}
	
	static public RDFNode getPartner() {
		return null; 
	}
	
	static public RDFNode getPartnerName() {
		return null; 
	}
	
	static public RDFNode getPartnerAge() {
		return null; 
	}
	
	
}
