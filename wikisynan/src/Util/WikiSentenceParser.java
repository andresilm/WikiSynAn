package Util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author aluna
 */
public class WikiSentenceParser {

    Scanner sourceScanner;
    BufferedReader sourceBuff;

    public WikiSentenceParser(Scanner sourceScanner) throws IOException {

        this.sourceScanner = sourceScanner;
        

        sourceScanner.useDelimiter("(?<=[.!?])\\s+|\n");
    }
    
 

    // this method always (tries to) return a valid sentence.
    public String parseNextSentence() throws IOException {
        String sentence;
        do{
            sentence = sourceScanner.next();
        } while (sentence.equals("\n") || sentence.equals(""));
        
        //end of sentence (for stanford parser is needed)
       if (!sentence.endsWith(".")) 
           sentence += ".";
       
       

        return sentence;
    }

    public boolean hasNextSentence() throws IOException {
        return sourceScanner.hasNext();
    }
}
