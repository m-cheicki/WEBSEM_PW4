/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applications;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

import tools.JenaEngine;

/**
 * @author DO.ITSUDPARIS
 */
public class Main {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		String NS = "";
		
		// Read the model from an ontology
		Model model = JenaEngine.readModel("data/family.owl");
		
		if (model != null) {

			// Read ontology's namespace
			NS = model.getNsPrefixURI("");
			
			// Modify the model
			// Add a woman in the model : Nora, 50, estFilleDe Peter
			JenaEngine.createInstanceOfClass(model, NS, "Female", "Nora");
			JenaEngine.updateValueOfDataTypeProperty(model, NS, "Nora", "name", "Nora");
			JenaEngine.updateValueOfDataTypeProperty(model, NS, "Nora", "age", 50);
			JenaEngine.updateValueOfObjectProperty(model, NS, "Nora", "isDaughterOf", "Peter");
			
			// Add a man in the model : Rob, 51, seMarierAvec Nora
			JenaEngine.createInstanceOfClass(model, NS, "Male", "Rob");
			JenaEngine.updateValueOfDataTypeProperty(model, NS, "Rob", "age", 51);
			JenaEngine.updateValueOfDataTypeProperty(model, NS, "Rob", "name", "Rob Yeung");
			JenaEngine.updateValueOfObjectProperty(model, NS, "Rob", "isMarriedWith", "Nora");
			
			// Apply owl rules on the model
			Model owlInferencedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/owlrules.txt");
			
			// Apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(owlInferencedModel, "data/rules.txt");
			
			// Queries on the model after inference
//			for(int i = 0; i < 16; i++) {
//				System.out.println("Query n°" + i);
//				System.out.println(JenaEngine.executeQueryFile(inferedModel,"data/query_" + i + ".txt"));	
//			}
			
			
			RDFNode test = JenaEngine.getValueOfObjectProperty(inferedModel, NS, "Rob", "isMarriedWith"); 
			System.out.println(test); 
			
		} 
		else {
			System.out.println("Error when reading model from ontology");
		}
	}
}
