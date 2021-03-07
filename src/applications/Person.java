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
	
	/**
	 * Gets the name.
	 * @param model
	 * @param namespace
	 * @param name
	 * @return Literal
	 */
	static public Literal getName(Model model, String namespace, String name) {
		return JenaEngine.getValueOfDataTypeProperty(model, namespace, name, "name"); 
	}
	
	/**
	 * Gets the age.
	 * @param model
	 * @param namespace
	 * @param name
	 * @return Literal
	 */
	static public Literal getAge(Model model, String namespace, String name) {
		return JenaEngine.getValueOfDataTypeProperty(model, namespace, name, "age"); 
	}
	
	/**
	 * Get one of its parent.
	 * @param model
	 * @param namespace
	 * @param name
	 * @param isAFemale
	 * @return RDFNode
	 */
	static public RDFNode getParents(Model model, String namespace, String name, boolean isAFemale) {
		return isAFemale ? 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isDaughterOf") : 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isSonOf");
	}
	
	/**
	 * Gets its siblings.
	 * @param model
	 * @param namespace
	 * @param name
	 * @param isAFemale
	 * @return RDFNode
	 */
	static public RDFNode getSiblings(Model model, String namespace, String name, boolean isAFemale) {
		return isAFemale ? 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isSisterOf") : 
				JenaEngine.getValueOfObjectProperty(model, namespace, name, "isBrotherOf"); 
	}
	
	/**
	 * Gets the Partner. 
	 * @param model
	 * @param namespace
	 * @param name
	 * @return RDFNode
	 */
	static private RDFNode isMarriedWith(Model model, String namespace, String name) {
		return JenaEngine.getValueOfObjectProperty(model, namespace, name, "isMarriedWith"); 
	}
	
	/**
	 * Gets the Partner's name.
	 * @param model
	 * @param namespace
	 * @param name
	 * @return String
	 */
	static public String getPartnerName(Model model, String namespace, String name) {
		RDFNode partner = isMarriedWith(model, namespace, name); 
		return partner.toString().split("#")[1]; 
	}
	
	/**
	 * Gets the Partner's age.
	 * @param model
	 * @param namespace
	 * @param name
	 * @return RDFNode
	 */
	static public RDFNode getPartnerAge(Model model, String namespace, String name) {
		String partner = getPartnerName(model, namespace, name); 
		return JenaEngine.getValueOfDataTypeProperty(model, namespace, partner, "age"); 
	}
	
	/**
	 * Gets the partner's name and age. 
	 * @param model
	 * @param namespace
	 * @param name
	 * @return String
	 */
	static public String getPartner(Model model, String namespace, String name) {
		return getPartnerName(model, namespace, name)+ " is his/her partner and that person is aged of " + getPartnerAge(model, namespace, name); 
	}
	
	/**
	 * Gets persons who have a given age difference.
	 * @param model
	 * @param namespace
	 * @param name
	 * @param difference
	 * @return
	 */
	// TODO: persons who have a given age difference.
	static public RDFNode getPersonWithGivenAgeDifference(Model model, String namespace, String name, int difference) {
		RDFNode age = getAge(model, namespace, name); 
		
		return age; 
	}
}
