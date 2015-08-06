package Pattern;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatternMatcher {
	List<Pattern> patternList;
	
	PatternMatcher() {
		patternList = new ArrayList<Pattern>();
	}
	
	public Pattern findMatchForParsing(List<Dependency> deps) {
		Pattern pattern = null;
		
		return pattern;
	}
	
	

	
	public void loadFromFile(String filename) throws FileNotFoundException {
	
		Scanner file = new Scanner(new FileReader(filename));

		while (file.hasNextLine()) {
			String[] line = file.nextLine().split("\\|");
			patternList.add(new Pattern(line[1].split("\\;"), line[0]));

		}

		
	}
}
