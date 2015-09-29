package dependency_parser;

// Note : The command line alternative is

import edu.stanford.nlp.ling.CoreLabel;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.StringReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


// Copied from http://nlp.stanford.edu/software/dependencies_manual.pdf
/*
 The options to get the different types of representation are as follows:
 -basic basic dependencies
 -collapsed collapsed dependencies (not necessarily a tree structure)
 -CCprocessed collapsed dependencies with propagation of conjunct
 dependencies (not necessarily a tree structure)
 -collapsedTree collapsed dependencies that preserve a tree structure
 -nonCollapsed non-collapsed dependencies: basic dependencies as well as
 the extra ones which do not preserve a tree structure
 -conllx dependencies printed out in CoNLL X (CoNLL 2006) format
 */
// Mix of code from http://stackoverflow.com/questions/19429106/how-can-i-integrate-stanford-parser-software-in-my-java-program and http://stackoverflow.com/questions/20813541/parse-sentence-stanford-parser-by-passing-string-not-an-array-of-strings
public class StanfordParserWrapper {

    static private TokenizerFactory<CoreLabel> tokenizerFactory;
    static private LexicalizedParser parser;
    static private TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    static private GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

    //added by aluna
    private String depRootPOS;
    private String depRoot;
    private int predicateRootIndex;
    private List<String> predicateWords;
    private List<String> argument1Words;
    private List<String> argument2Words;
    GrammaticalStructure gramStructure;
    private int recCall = 0;

   

    static {
        parser = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        parser.setOptionFlags(new String[]{"-retainTmpSubcategories"}); // option to get best performance in recognizing temporal dependencies

    }

    private String sentence;
    private Collection<TypedDependency> allDependencies;

    public StanfordParserWrapper() {
        tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

    }


    
    private void computeDependenciesForCurrentSentence() {
        List<CoreLabel> tokens = tokenize(this.sentence);

        Tree parsed_tree = parser.apply(tokens);
        

        gramStructure = gsf.newGrammaticalStructure(parsed_tree);
        this.allDependencies = gramStructure.typedDependenciesCollapsedTree();
        

    }

   
    public void analyzeSentence(String sentence) {
        //set new sentence
        this.sentence = sentence;
        //reset other temporary results 
        this.depRoot = null;
        this.argument1Words = null;
        this.argument2Words = null;
        this.gramStructure = null;
        this.predicateRootIndex = -1;
        this.allDependencies = null;
        this.predicateWords = null;
        this.gramStructure = null;
        //compute dependencies for the new sentence
        computeDependenciesForCurrentSentence();
    }

    /**
     * @return the allDependencies
     */
    public Collection<TypedDependency> getAllDependencies() {
        return allDependencies;
    }

    private List<CoreLabel> tokenize(String str) {
        Tokenizer<CoreLabel> tokenizer = tokenizerFactory.getTokenizer(new StringReader(str));
        return tokenizer.tokenize();
    }

 
}
