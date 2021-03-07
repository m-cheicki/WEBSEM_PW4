package applications;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
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
	
	static public Literal getAge(Model model, String namespace, String name) {
		return JenaEngine.getValueOfDataTypeProperty(model, namespace, name, "age"); 
	}
	
	static public RDFNode getParents(Model model, String namespace, String name, boolean isAFemale) {
		return isAFemale ? 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isDaughterOf") : 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isSonOf");
	}
	
	static public RDFNode getSiblings(Model model, String namespace, String name, boolean isAFemale) {
		return isAFemale ? 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isSisterOf") : 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isBrotherOf"); 
	}
	

	static private RDFNode isMarriedWith(Model model, String namespace, String name) {
		return JenaEngine.getValueOfObjectProperty(model, namespace, name, "isMarriedWith"); 
	}
	
	static public String getPartnerName(Model model, String namespace, String name) {
		RDFNode partner = isMarriedWith(model, namespace, name); 
		return partner.toString().split("#")[1]; 
	}
	
	static public RDFNode getPartnerAge(Model model, String namespace, String name) {
		String partner = getPartnerName(model, namespace, name); 
		return JenaEngine.getValueOfDataTypeProperty(model, namespace, partner, "age"); 
	}
	
	static public String getPartner(Model model, String namespace, String name) {
		return getPartnerName(model, namespace, name)+ " is his/her partner and that person is aged of " + getPartnerAge(model, namespace, name); 
	}
	
	static public RDFNode getPersonWithGivenAgeDifference(Model model, String namespace, String name, int difference) {
		RDFNode age = getAge(model, namespace, name); 
		
		return age; 
	}
}
