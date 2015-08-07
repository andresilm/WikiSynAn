package Annotator;

public class AnnotationResult {
	private String origSentence;
	private String annotatedSentence;
	private String wordAnnotated;
	private int index;
	private String treeName;
	
	public AnnotationResult() {
		
	}
	
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
	public String getResultSentence() {
		return annotatedSentence;
	}
	void setResultSentence(String resultSentence) {
		this.annotatedSentence = resultSentence;
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
