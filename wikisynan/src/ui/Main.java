package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import Annotator.Annotator;
import Annotator.SentenceAnnotation;
import Pattern.PatternMatcher;
import Pattern.Pattern;
import Util.WikiSentenceParser;


public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length == 4) {
			String patternListFilename = args[2];
			String inputFilename = args[3];
			
			Scanner gzipSource = new Scanner(new GZIPInputStream(new FileInputStream(inputFilename)));
			PatternMatcher pMatcher = new PatternMatcher();
			pMatcher.loadFromFile(patternListFilename);
			annotateSource(gzipSource,pMatcher);
		} else {
			System.out.println("Usage: java -jar [this].jar patternsFile inputFile");
		}
	}

	private static void annotateSource(Scanner source,PatternMatcher pMatcher) throws IOException {
		WikiSentenceParser parser = new WikiSentenceParser(source);
		Annotator annotator = new Annotator(pMatcher);
		
		while (parser.hasNextSentence()) {
			String sentence = parser.parseNextSentence();
			SentenceAnnotation annotation = annotator.annotateSentence(sentence);
			System.out.println(annotation.getAnnotatedSentence());
		}

	}
}
