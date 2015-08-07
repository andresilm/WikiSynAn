package Annotator;
import java.util.ArrayList;
import java.util.List;

import Util.Triple;

public class Annotation {
	private String origSentence;
	private String annotatedSentence = "";
	private List<Triple<String,Integer,String>> wordsAnnotated;
	
	
	
	public Annotation(String origSentence) {
		this.origSentence = origSentence;
		wordsAnnotated = new ArrayList();
	}
	
	public void addAnnotation(String word, int index, String treeName) {
		wordsAnnotated.add(new Triple(word,index,treeName));
	}
	
	public  List<Triple<String,Integer,String>> getAnnotations() {
		return wordsAnnotated;
	}
	
	public void applyToSentence() {
		int index_offset = 1;
		String[] splitSent = origSentence.split(" ");

	
		for (int i = 0; i < splitSent.length; ++i) {
			if (splitSent[i].contains(",") || splitSent[i].contains(";") || splitSent[i].contains("-") || splitSent[i].contains("(")
					|| splitSent[i].contains(")") || splitSent[i].contains("{") || splitSent[i].contains("}")
					|| splitSent[i].contains("[") || splitSent[i].contains("]"))
				++index_offset;
			Triple<String,Integer,String> wordToAnnotate = searchWordToAnnotate(i+index_offset);
			if (wordToAnnotate != null) {
			
				annotatedSentence += "[" + wordToAnnotate.getX() + "/" + wordToAnnotate.getZ() + "] ";
			}
			else {
				annotatedSentence += splitSent[i];
				if (i < splitSent.length - 1)
					annotatedSentence += " ";
				
			}
			
			
				
		}
	}

	private Triple<String, Integer, String> searchWordToAnnotate(int i) {
		for (Triple<String, Integer, String> wordToAnnotate : this.getAnnotations()) {
			if (wordToAnnotate.getY() == i)
				return wordToAnnotate;
		}
		return null;
	}

	public String getAnnotatedSentence() {
		return annotatedSentence;
	}

	 void setAnnotatedSentence(String annotatedSentence) {
		this.annotatedSentence = annotatedSentence;
	}

}
