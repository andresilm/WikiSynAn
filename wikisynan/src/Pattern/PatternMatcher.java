package Pattern;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatternMatcher {
	List<Pattern> patternList;

	public PatternMatcher() {
		patternList = new ArrayList<Pattern>();
	}

	public Pattern findMatchForParsing(List<Dependency> deps) {

		for (Pattern p : patternList) {
			System.err.println("Parse pattern: "+p.toString());
			if (p.matchesWithParsing(deps))
				return p;
		}
		System.err.println("No pattern matched!. Returning null");
		return null;
	}

	public void loadFromFile(String filename) throws FileNotFoundException {

		Scanner file = new Scanner(new FileReader(filename));

		while (file.hasNextLine()) {

			String nline = file.nextLine();
			if (!nline.equals("") && !nline.equals("\n")) {
			
				String[] line = nline.split("\\|");
			
			
				patternList.add(new Pattern(line[1].split("\\;"), line[0]));
			}
		}

	}
}
