package Annotator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Pattern.Dependency;
import Pattern.Pattern;
import Pattern.PatternMatcher;
import Util.Parser;
import edu.stanford.nlp.trees.TypedDependency;

public class Annotator {
	private Parser parser;
	private PatternMatcher patternMatcher;
	

	Annotator(Parser parser,PatternMatcher patternMatcher) {
		this.parser = parser;
		this.patternMatcher = patternMatcher;
		
	}
	
	private static AnnotationResult getAnnotationData(List<Dependency> deps) {
		AnnotationResult ret = new AnnotationResult();
		
		return ret;
	}
	
	public AnnotationResult annotateSentence(String sentence) {
		AnnotationResult result = new AnnotationResult();
		result.setOrigSentence(sentence);
		
		parser.analyzeSentence(sentence);
		
		List<Dependency> myDeps = new ArrayList<Dependency>();
		Collection<TypedDependency> deps = parser.getAllDependencies();
		
		for (TypedDependency dep: deps) {
			myDeps.add(new Dependency(dep));
		}
		
		Pattern patternMatched = patternMatcher.findMatchForParsing(myDeps);
		
		
		
		
		return result;
	}
	
	
}
