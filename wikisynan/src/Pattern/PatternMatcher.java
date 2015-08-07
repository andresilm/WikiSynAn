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

	public List<Pattern> findMatchForParsing(List<Dependency> deps) {
		List<Pattern> matches = new ArrayList();
		for (Pattern p : patternList) {
			
			if (p.matchesWithParsing(deps)) {
				
				matches.add(p);
			}
		}
		
		return matches;
	}
	
	public void addPattern(Pattern p) {
		this.patternList.add(p);
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
