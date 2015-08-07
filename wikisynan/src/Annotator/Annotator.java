package Annotator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import DependencyParser.StanfordParserWrapper;
import Pattern.Dependency;
import Pattern.Pattern;
import Pattern.PatternMatcher;
import edu.stanford.nlp.trees.TypedDependency;

public class Annotator {
	private StanfordParserWrapper parser;
	private PatternMatcher patternMatcher;

	public Annotator(StanfordParserWrapper parser, PatternMatcher patternMatcher) {
		this.parser = parser;
		this.patternMatcher = patternMatcher;

	}

	public AnnotationResult annotateSentence(String sentence) {
		AnnotationResult result = new AnnotationResult();
		result.setOrigSentence(sentence);

		parser.analyzeSentence(sentence);

		List<Dependency> myDeps = new ArrayList<Dependency>();
		Collection<TypedDependency> deps = parser.getAllDependencies();

		for (TypedDependency dep : deps) {
			myDeps.add(new Dependency(dep));
		//	System.err.println("Dep: " + dep.toString());
		}

		Pattern patternMatched = patternMatcher.findMatchForParsing(myDeps);
		if (patternMatched != null) {
			result.setWord(patternMatched.getMarkedWord());
			int index = patternMatched.getMarkedIndex();
			String[] splitSent = sentence.split("\\ ");
			splitSent[index] = patternMatched.getMarkedWord();

			String resSent = "";
			for (String chunk : splitSent) {
				resSent += chunk + " ";
			}

			result.setResultSentence(resSent);
		} else {
			result = null;
		}

		return result;
	}

}
