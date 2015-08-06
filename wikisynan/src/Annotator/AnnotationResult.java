package Annotator;

public class AnnotationResult {
	private String origSentence;
	private String resultSentence;
	private String wordAnnotated;
	private int index;
	private String treeName;
	
	String getWord() {
		return getWordAnnotated();
	}
	void setWord(String word) {
		this.setWordAnnotated(word);
	}
	String getTreeName() {
		return treeName;
	}
	void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	
	
	@Override 
	public String toString() {
		return "["+getWordAnnotated()+"|"+getTreeName()+"]";
	}
	String getOrigSentence() {
		return origSentence;
	}
	void setOrigSentence(String origSentence) {
		this.origSentence = origSentence;
	}
	String getResultSentence() {
		return resultSentence;
	}
	void setResultSentence(String resultSentence) {
		this.resultSentence = resultSentence;
	}
	private String getWordAnnotated() {
		return wordAnnotated;
	}
	private void setWordAnnotated(String wordAnnotated) {
		this.wordAnnotated = wordAnnotated;
	}
	private int getIndex() {
		return index;
	}
	private void setIndex(int index) {
		this.index = index;
	}
}
