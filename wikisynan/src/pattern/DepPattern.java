package pattern;

public class DepPattern {
	private String reln;
	private String gov;
	private String dep;
	private boolean govIsVar;
	private boolean depIsVar;

	private String govPOS;
	private String depPOS;
	int marked;

	final static int GOV = 0;
	final static int DEP = 1;

	public DepPattern(String pattern) {

		String[] split1 = pattern.split("\\(");
		setReln(split1[0]);

		String[] split2 = split1[1].split("\\,");

		String govData[] = parseNode(split2[0], GOV);
		setGovIsVar(govData[0] == ""); // isVar ==> gov == null && govPOS !=
										// null

		setGov(govData[0]);
		setGovPOS(govData[1]);

		String depData[] = parseNode(split2[1].substring(0, split2[1].length() - 1), DEP);
		setDepIsVar(depData[0] == ""); // isVar ==> dep == null && depPOS !=
										// null

		setDep(depData[0]);
		setDepPOS(depData[1]);

	}

	private String[] parseNode(String node, int nodeType) {

		String[] nodeData = new String[2];
		if (node.contains("/")) {// node = "word/tag"

			String[] splitNode = node.split("\\/");
			nodeData = splitNode;

		} else {// POS tag only => is var
			// System.out.println(node);
			nodeData[1] = node;// POS tag
			nodeData[0] = "";

			if (node.startsWith("<") && node.endsWith(">")) {

				nodeData[1] = node.substring(1, node.length() - 1);

				marked = nodeType;
			}

		}

		return nodeData;
	}

	public boolean matchesWithDependency(Dependency dep) {
		boolean sameRelName = dep.getReln().equals(this.getReln());

		boolean sameGovPOSTag = dep.getGovNormalizedPOSTag().equals(this.getGovPOS());

		boolean sameDepPOSTag = dep.getDepNormalizedPOSTag().equals(this.getDepPOS());
		boolean depIsVarOrSameWord = IMPLIES(!this.isGovIsVar(), ((this.getGovPOS().equals("V"))
				? this.getGov().equals(dep.getGovLemmatized()) : this.getGov().equals(dep.getGov())));
		boolean GovIsVarOrSameWord = IMPLIES(!this.isGovIsVar(), ((this.getGovPOS().equals("V"))
				? this.getGov().equals(dep.getGovLemmatized()) : this.getGov().equals(dep.getGov())));
		
		/*System.err.println("Pattern matching: " + dep.toString() + " with " +this.toString());
		System.err.println(sameRelName);
		System.err.println(sameGovPOSTag);
		System.err.println(sameDepPOSTag);
		System.err.println(depIsVarOrSameWord);
		System.err.println(GovIsVarOrSameWord);
		System.err.println("END Pattern matching: ");
*/
		return sameRelName && sameGovPOSTag && sameDepPOSTag && depIsVarOrSameWord && GovIsVarOrSameWord;

	}

	public String getMarkedInstantation(Dependency dep) {
		String ret = null;
		// if (this.matchesWithDependency(dep)) {
		if (this.marked == GOV) {
			ret = dep.getGov();
		} else if (this.marked == DEP) {
			ret = dep.getDep();
		} else {
			System.err.println("Not possible!.");
			ret = "";
		}
		// }

		return ret;

	}

	private boolean IMPLIES(boolean p, boolean q) {

		return !p || q;
	}

	@Override
	public String toString() {
		return getReln() + "(" + ((!getGov().equals("")) ? (getGov() + "/") : "") + ((marked == GOV) ? "<" : "")
				+ getGovPOS() + ((marked == GOV) ? ">" : "") + "," + ((!getDep().equals("")) ? (getDep() + "/") : "")
				+ ((marked == DEP) ? "<" : "") + getDepPOS() + ((marked == DEP) ? ">" : "") + ")";
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

	boolean isGovIsVar() {
		return govIsVar;
	}

	void setGovIsVar(boolean govIsVar) {
		this.govIsVar = govIsVar;
	}

	boolean isDepIsVar() {
		return depIsVar;
	}

	void setDepIsVar(boolean depIsVar) {
		this.depIsVar = depIsVar;
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

}
