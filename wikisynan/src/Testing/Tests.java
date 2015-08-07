package Testing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Annotator.AnnotationResult;
import Annotator.Annotator;
import DependencyParser.StanfordParserWrapper;
import Pattern.DepPattern;
import Pattern.Dependency;
import Pattern.Pattern;
import Pattern.PatternMatcher;

public class Tests {
	public static void main(String[] args) throws FileNotFoundException {
		DepPattern depp1 = new DepPattern("nsubj(<N>,N)");
		DepPattern depp2 = new DepPattern("cop(<N>,was/V)");

		DepPattern depp3 = new DepPattern("vmod(N,<V>)");
		DepPattern depp4 = new DepPattern("aux(<V>, to/TO)");
		DepPattern depp5 = new DepPattern("prep-on(<V>,N)");

		Dependency dep1 = new Dependency("nsubj(astronaut-3/NN,Armstrong-1/NP)");
		Dependency dep2 = new Dependency("cop(astronaut-3/NN,was-2/VBD)");

		Dependency dep3 = new Dependency("vmod(person-5/NN,walk-7/VB)");// ajustar
																		// depdenency
																		// para
																		// que
																		// parsee
																		// el
																		// indice
		Dependency dep4 = new Dependency("aux(walk-7/VB, to-6/TO)");
		Dependency dep5 = new Dependency("prep-on(walk-7/VB,moon-8/NNP)");

		List<Dependency> deps = new ArrayList();
		deps.add(dep1);
		deps.add(dep2);
		deps.add(dep3);
		deps.add(dep4);
		deps.add(dep5);

		System.out.println("=== Test 1 ===");
		System.out.println(dep1);
		System.out.println(dep2);

		System.out.println(depp1);
		System.out.println(depp2);

		System.out.println(depp1.matchesWithDependency(dep1));
		System.out.println(depp2.matchesWithDependency(dep2));
		System.out.println(depp2.matchesWithDependency(dep1));
		System.out.println(depp1.matchesWithDependency(dep2));

		System.out.println("=== Test 2 ===");
		String[] depList1 = new String[2];
		depList1[0] = "nsubj(<N>,N)";
		depList1[1] = "cop(<N>,was/V)";
		Pattern p1 = new Pattern(depList1, "n0N1-Canonical");
		DepPattern[] depList2 = new DepPattern[3];
		depList2[0] = depp3;
		depList2[1] = depp4;
		depList2[2] = depp5;
		Pattern p2 = new Pattern(depList2, "beta-n0Vpn1");

		System.out.println(p1.matchesWithParsing(deps));
		System.out.println(p1.getAnnotation());
		System.out.println(p2.matchesWithParsing(deps));
		System.out.println(p2.getAnnotation());

		System.out.println("=== Test 3 ===");

		PatternMatcher pMatcher = new PatternMatcher();
		pMatcher.loadFromFile("resources/patterns.txt");
		Annotator annotator = new Annotator(new StanfordParserWrapper(), pMatcher);

		AnnotationResult result = annotator.annotateSentence(
				"Neil Alden Armstrong (August 5, 1930 – August 25, 2012) was an American astronaut and the first person to walk on the Moon.");
		if (result != null)
		System.out.print(result.getResultSentence());
		else
			System.out.print("No result returned from Annotator");
		
	}
}
