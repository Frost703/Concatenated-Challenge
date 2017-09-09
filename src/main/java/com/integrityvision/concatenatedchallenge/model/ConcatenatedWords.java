package com.integrityvision.concatenatedchallenge.model;

import java.util.HashSet;

/**
 * Class that contains all concatenated words and also stores the longest and second longest ones
 */
public class ConcatenatedWords {
    private String longestConcatenated = "";
    private String secondLongestConcatenated = "";

    private int longestConcatenatedLength;
    private int secondLongestConcatenatedLength;

    private HashSet<String> concatenatedWords = new HashSet<>();

    public String getLongestConcatenated() {
        return longestConcatenated;
    }

    public String getSecondLongestConcatenated() {
        return secondLongestConcatenated;
    }

    public int getLongestConcatenatedLength() {
        return longestConcatenatedLength;
    }

    public int getSecondLongestConcatenatedLength() {
        return secondLongestConcatenatedLength;
    }

    public int getConcatenatedWordsAmount(){
        return concatenatedWords.size();
    }

    public void addConcatenatedWord(String word) {
        int wordLength = word.length();

        if(wordLength > longestConcatenatedLength){
            setLongestConcatenated(word, wordLength);
        }

        else if(wordLength > secondLongestConcatenatedLength && wordLength != longestConcatenatedLength){
            setSecondLongestConcatenated(word, wordLength);
        }

        concatenatedWords.add(word);
    }

    private void setLongestConcatenated(String word, int wordLength){
        if(wordLength > longestConcatenatedLength) setSecondLongestConcatenated(longestConcatenated, longestConcatenatedLength);

        longestConcatenated = word;
        longestConcatenatedLength = wordLength;
    }

    private void setSecondLongestConcatenated(String word, int wordLength){
        if(secondLongestConcatenatedLength != wordLength) {

            secondLongestConcatenated = word;
            secondLongestConcatenatedLength = wordLength;
        }
    }
}
