package annotator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dependency_parser.StanfordParserWrapper;
import edu.stanford.nlp.trees.TypedDependency;
import pattern.Dependency;
import pattern.Pattern;
import pattern.PatternMatcher;

public class Annotator {
	private StanfordParserWrapper parser;
	private PatternMatcher patternMatcher;

	public Annotator(PatternMatcher patternMatcher) {
		this.parser = new StanfordParserWrapper();
		this.patternMatcher = patternMatcher;

	}

	public SentenceAnnotation annotateSentence(String sentence) {
		SentenceAnnotation result = new SentenceAnnotation(sentence);

		parser.analyzeSentence(sentence);

		List<Dependency> myDeps = new ArrayList<Dependency>();
		Collection<TypedDependency> deps = parser.getAllDependencies();

		
		for (TypedDependency dep : deps) {
		//	System.err.println(dep);
			myDeps.add(new Dependency(dep));
			
		}

		List<Pattern> matches = patternMatcher.findMatchForParsing(myDeps);
		if (!matches.isEmpty()) {
			for (Pattern patternMatched : matches) {
				result.addAnnotation(patternMatched.getMarkedWord(), patternMatched.getMarkedIndex(), patternMatched.getTreeId());
				
				}
			
			result.applyToSentence();
			
		} else {
			result = null;
		}

		return result;
	}

}
