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

	private String treeId;
	private String markedWord = "";
	private int markedIndex = -1;

	public Pattern(String[] depPatListtStr, String treeId) {
		this.setTreeId(treeId);
		depPatterns = new ArrayList();

		for (String depPatternStr : depPatListtStr) {
			if (!depPatternStr.equals(""))
				depPatterns.add(new DepPattern(depPatternStr));
		}

	}
	
	public Pattern(DepPattern[] depPatList, String treeId) {
		this.setTreeId(treeId);
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
				
				if (dep.matchesWithDepPattern(depPattern)) {
				
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
				
			}
			else {
				if (key.marked == DepPattern.GOV) {
					diffMarks.add(dep.get(0).getGov());
					this.setMarkedWord(dep.get(0).getGov());
					this.setMarkedIndex(dep.get(0).getGovIndex());
				}
				else if (key.marked == DepPattern.DEP) {
					diffMarks.add(dep.get(0).getDep());
					this.setMarkedWord(dep.get(0).getDep());
					this.setMarkedIndex(dep.get(0).getDepIndex());
				}
			}
		}
		
		if (diffMarks.size() > 1)// more than a word marked with <> sounds bad!
			return false;
	
	System.err.println(this.getAnnotation());

		return ret;
	}

	public String getAnnotation() {
		return "["+getMarkedWord() + "-" +  this.getMarkedIndex() + "|" + getTreeId() + "]";
	}

	public String getMarkedWord() {
		return markedWord;
	}

	public void setMarkedWord(String markedWord) {
		this.markedWord = markedWord;
	}

	public int getMarkedIndex() {
		return markedIndex;
	}

	void setMarkedIndex(int markedIndex) {
		this.markedIndex = markedIndex;
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (DepPattern d: this.depPatterns) {
			ret += d + ";";
		}
		return ret;
	}

	public String getTreeId() {
		return treeId;
	}

	void setTreeId(String treeId) {
		this.treeId = treeId;
	}

}
