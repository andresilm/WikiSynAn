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
			// System.err.println("Dep: " + dep.toString());
		}

		List<Pattern> matches = patternMatcher.findMatchForParsing(myDeps);
		if (!matches.isEmpty()) {
			if (matches.size() == 1) {
				Pattern patternMatched = matches.get(0);
				result.setWord(patternMatched.getMarkedWord());
				int index = patternMatched.getMarkedIndex();
				System.err.println("Word: " + patternMatched.getMarkedWord() + " ; Index: " + index);
				String[] splitSent = sentence.split(" ");

				String resSent = "";
				for (int i = 0; i < splitSent.length; ++i) {
					if (i == index)
						resSent += "[" + patternMatched.getMarkedWord() + "/" + patternMatched.getTreeId() + "]";
					else
						resSent += splitSent[i];
					if (i < splitSent.length - 1)
						resSent += " ";
					else
						resSent += ".";
				}

				result.setResultSentence(resSent);
			} else {
				result = null;
			}
		} else {
			System.err.println("SURPRISE! more than one pattern matched...");
		}

		return result;
	}

}
