package com.integrityvision.concatenatedchallenge.model;

import java.util.HashSet;

/**
 * Class that contains all concatenated words and also stores the longest and second longest ones
 */
public class ConcatenatedWords {
    private String longestConcatenated;
    private String secondLongestConcatenated;

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

    private void setLongestConcatenated(String word, int wordLength){
        secondLongestConcatenated = longestConcatenated;
        secondLongestConcatenatedLength = longestConcatenatedLength;

        longestConcatenated = word;
        longestConcatenatedLength = wordLength;
    }

    private void setSecondLongestConcatenated(String word, int wordLength){
        secondLongestConcatenated = word;
        secondLongestConcatenatedLength = wordLength;
    }

    public void addConcatenatedWord(String word) {
        int wordLength = word.length();

        if(wordLength > longestConcatenatedLength){
            setLongestConcatenated(word, wordLength);
        }

        else if(wordLength > secondLongestConcatenatedLength){
            setSecondLongestConcatenated(word, wordLength);
        }

        concatenatedWords.add(word);
    }

    public int getConcatenatedWordsAmount(){
        return concatenatedWords.size();
    }

    //TODO What if there are 2+ longest concatenated word with the same length?
}
