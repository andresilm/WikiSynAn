package Pattern;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Pattern {
	List<DepPattern> depPatterns;

	String treeId;

	public Pattern(String[] depPatListtStr, String treeId) {
		this.treeId = treeId;
		depPatterns = new ArrayList();

		for (String depPatternStr : depPatListtStr) {
			if (!depPatternStr.equals(""))
				depPatterns.add(new DepPattern(depPatternStr));
		}

	}
	
	public Pattern(DepPattern[] depPatList, String treeId) {
		this.treeId = treeId;
		depPatterns = new ArrayList();

		for (DepPattern depPatternStr : depPatList) {
				depPatterns.add(depPatternStr);
		}

	}

	public boolean matchesWithParsing(List<Dependency> deps) {
		boolean ret = true;
		Map<DepPattern, List<Dependency>> matches = new HashMap<DepPattern, List<Dependency>>();

		for (DepPattern depPattern : depPatterns) {
			matches.put(depPattern, new ArrayList<Dependency>());
		}

		for (DepPattern depPattern : depPatterns) {
			for (Dependency dep : deps) {
				System.err.println("Evaluating pattern " + depPattern.toString() + " with dep " + dep.toString());
				if (dep.matchesWithDepPattern(depPattern)) {
					System.err.println("Matches!");
					matches.get(depPattern).add(dep);
				}
			}
		}
		
		
		Set<String> diffMarks = new HashSet();
		
		for (DepPattern key : matches.keySet()) {
			List<Dependency> dep = matches.get(key);
			
			if (dep.isEmpty())
				return false;
			else if (dep.size() > 1) {
				System.err.println("CAUTION: more than one dependency matched. Fix code to handle this!.");
			}
			else {
				if (key.marked == DepPattern.GOV)
					diffMarks.add(dep.get(0).getGov());
				else if (key.marked == DepPattern.DEP) {
					diffMarks.add(dep.get(0).getDep());
				}
			}
		}
		
		if (diffMarks.size() > 1)// more than a word marked with <> sounds bad!
			return false;

	

		return ret;
	}

	public String getMarkedWord() {
		return "";
	}

}
