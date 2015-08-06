package Pattern;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Pattern {
	List<DepPattern> depPatterns;

	String treeId;

	Pattern(String[] depPatListtStr, String treeId) {
		this.treeId = treeId;
		depPatterns = new ArrayList();

		for (String depPatternStr : depPatListtStr) {
			if (!depPatternStr.equals(""))
				depPatterns.add(new DepPattern(depPatternStr));
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
				if (dep.matchesWithDepPattern(depPattern)) {
					matches.get(depPattern).add(dep);
				}
			}
		}

		for (List<Dependency> dep : matches.values()) {
			if (dep.isEmpty())
				return false;
		}

		// List<DepPattern> byMarks = new ArrayList<DepPattern>();

		// if for all patterns there is at least a dependency matched

		return ret;
	}

	public String getMarkedWord() {
		return "";
	}

}
