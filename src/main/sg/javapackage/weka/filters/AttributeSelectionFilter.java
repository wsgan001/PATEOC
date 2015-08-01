package main.sg.javapackage.weka.filters;

import main.sg.javapackage.logging.Logger;
import weka.attributeSelection.GreedyStepwise;
import weka.attributeSelection.WrapperSubsetEval;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.Remove;

public class AttributeSelectionFilter {
	
	public AttributeSelectionFilter() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * returns only the community features from 
	 * the main training data
	 * 
	 * @return Instances
	 * @throws Exception
	 */
	public static Instances performAttributeSelection1(Instances data) throws Exception{
		
		//Only community attributes featuring from 1-9, and last nominal attribute
		Remove remove =  new Remove();
		remove.setAttributeIndices("1-9,last");
		remove.setInvertSelection(true);
		remove.setInputFormat(data);
		Instances updated_traningset = Filter.useFilter(data, remove);
		return updated_traningset;

	}
	
	/**
	 * returns all features : community features +
	 * leadership features + temporal features from
	 * the main training data
	 * 
	 * @return Instances
	 * @throws Exception
	 */
	public static Instances performAttributeSelection2(Instances data) throws Exception{
		
//		//Only community attributes featuring from 1-9, and last nominal attribute
//		Remove remove =  new Remove();
//		remove.setAttributeIndices("1-9,last");
//		remove.setInvertSelection(true);
//		remove.setInputFormat(data);
//		Instances updated_traningset = Filter.useFilter(data, remove);
//		return updated_traningset;
		return data;
	}
	
	/**
	 * performs attribute selection using
	 * WrapperFunction / SubsetEvalutions
	 * Bestfirst / GreedyStepwise search
	 * 
	 * @param data
	 * @param model
	 * @return Instances
	 * @throws Exception
	 */
	public static Instances performAttributeSelection3(Instances data, Classifier model) throws Exception{
		
		AttributeSelection attributeselector = new AttributeSelection();
		WrapperSubsetEval wrapper = new WrapperSubsetEval();
		wrapper.setClassifier(model);
		GreedyStepwise searcher = new GreedyStepwise();
		searcher.setSearchBackwards(true);
		attributeselector.setEvaluator(wrapper);
		attributeselector.setSearch(searcher);
		attributeselector.setInputFormat(data);
		// generate new data
		Instances updated_traningset = Filter.useFilter(data, attributeselector);
		Logger.writeToLogln(" HERERERERE"+ updated_traningset.attribute(0).name());
		return updated_traningset;
	}
	

}