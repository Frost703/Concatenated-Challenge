package com.integrityvision.concatenatedchallenge.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * Class that contains all concatenated words and also stores the longest and second longest ones
 */
public class ConcatenatedWords {
    private static final Logger log = LoggerFactory.getLogger(ConcatenatedWords.class);

    private String longestConcatenated;
    private String secondLongestConcatenated;

    private int longestConcatenatedLength;
    private int secondLongestConcatenatedLength;

    private HashSet<String> concatenatedWords = new HashSet<>();

    public String getLongestConcatenated() {
        if(longestConcatenated == null){
            getLongestAndSecondLongestConcatenatedWords();
        }

        return longestConcatenated;
    }

    public String getSecondLongestConcatenated() {
        if(secondLongestConcatenated == null){
            getLongestAndSecondLongestConcatenatedWords();
        }

        return secondLongestConcatenated;
    }

    public int getLongestConcatenatedLength() {
        if(longestConcatenatedLength == 0){
            getLongestAndSecondLongestConcatenatedWords();
        }

        return longestConcatenatedLength;
    }

    public int getSecondLongestConcatenatedLength() {
        if(secondLongestConcatenatedLength == 0){
            getLongestAndSecondLongestConcatenatedWords();
        }

        return secondLongestConcatenatedLength;
    }

    public int getConcatenatedWordsAmount() {
        return concatenatedWords.size();
    }

    public void addConcatenatedWord(String word) {
        //log.debug("Found concatenated word - {}", word);
        concatenatedWords.add(word);
    }

    private void getLongestAndSecondLongestConcatenatedWords(){
        int longestLength = 0, secondLongestLength = 0;
        String longest = "", secondLongest = "";
        for(String c : concatenatedWords){
            int length = c.length();
            if(longestLength < length){
                secondLongestLength = longestLength;
                secondLongest = longest;

                longestLength = length;
                longest = c;
            }
            else if(secondLongestLength < length){
                secondLongestLength = length;
                secondLongest = c;
            }
        }

        this.longestConcatenated = longest;
        this.longestConcatenatedLength = longestLength;

        this.secondLongestConcatenated = secondLongest;
        this.secondLongestConcatenatedLength = secondLongestLength;
    }
}
