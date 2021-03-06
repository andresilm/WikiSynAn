package pattern;

import java.util.List;

import edu.stanford.nlp.trees.TypedDependency;


public class Dependency {
	private String reln;
	private String gov;
	private String dep;
	
	private String govPOS;
	private String depPOS;
	
	private int govIndex;
	private int depIndex;
	

	private static Lemmatizer lemmatizer;

	static {
		lemmatizer = Lemmatizer.getInstance();
	}

	
	
	public Dependency(String depStr) {
		String[] split1 = depStr.split("\\(");
		setReln(split1[0]);
		
		String[] split2 = split1[1].split("\\,");
		setGov(split2[0].split("\\/")[0].split("\\-")[0]);
		
		setGovPOS(split2[0].split("\\/")[1]);
		
		String[] split3 = split2[1].split("\\)");
		setDep(split3[0].split("\\/")[0].split("\\-")[0]);
		
		setDepPOS(split3[0].split("\\/")[1]);
	}
	
	public Dependency(TypedDependency typDep) {
		reln = typDep.reln().toString();
		gov = typDep.gov().originalText();
		dep = typDep.dep().originalText();
		govPOS =typDep.gov().tag();
		depPOS = typDep.dep().tag();
		govIndex = typDep.gov().index();
		depIndex = typDep.dep().index();
	}
	
	public String getGovNormalizedPOSTag() {
		
			return normalizePOS(this.govPOS);
		
			
		
	}
	
	public String getDepNormalizedPOSTag() {
		
		return normalizePOS(this.depPOS);
	}
	
	public String getDepLemmatized() {
		return lemmatize(this.dep);
	}
	
	public String getGovLemmatized() {
		return lemmatize(this.gov);
	}
		
	

	
	private static String normalizePOS(String tag) {
	       String norm = "";
	        if (tag != null && !tag.equals("")) {
	            if (tag.equals("NNP") || tag.equals("NNS") || tag.equals("NNPS") || tag.equals("PRP") || tag.equals("WP") || tag.equals("NN") || tag.equals("NP")) {
	                norm = "N";
	            } else if (tag.equals("VB") || tag.equals("VBZ") || tag.equals("VBD") || tag.equals("VBN") || tag.equals("VBP") || tag.equals("VBG")) {
	                norm = "V";
	            } else {
	                norm = tag;
	            }
	        }
	        return norm;		
	}
	private String lemmatize(String word) {
		/*String[] indexSep = word.split("\\-");
		this.setGovIndex(Integer.valueOf(indexSep[1]));
		
		return indexSep[0];*/
		
		return lemmatizer.getLemma(word);
		
	}

	String getReln() {
		return reln;
	}

	void setReln(String reln) {
		this.reln = reln;
	}

	String getGov() {
		return gov;
	}

	void setGov(String gov) {
		this.gov = gov;
	}

	String getDep() {
		return dep;
	}

	void setDep(String dep) {
		this.dep = dep;
	}

	String getGovPOS() {
		return govPOS;
	}

	void setGovPOS(String govPOS) {
		this.govPOS = govPOS;
	}

	String getDepPOS() {
		return depPOS;
	}

	void setDepPOS(String depPOS) {
		this.depPOS = depPOS;
	}
	
	@Override
	public String toString() {
		return reln + "(" + gov + "/" + govPOS + "," + dep + "/"+ depPOS + ")";
	}
	
	
	public boolean matchesWithDepPattern(DepPattern deps) {
		return deps.matchesWithDependency(this);
		
	}

	public int getGovIndex() {
		return govIndex;
	}

	private void setGovIndex(int govIndex) {
		this.govIndex = govIndex;
	}

	public int getDepIndex() {
		return depIndex;
	}

	private void setDepIndex(int depIndex) {
		this.depIndex = depIndex;
	}
}
