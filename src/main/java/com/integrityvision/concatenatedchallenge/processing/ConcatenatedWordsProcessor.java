package com.integrityvision.concatenatedchallenge.processing;

import com.integrityvision.concatenatedchallenge.model.ConcatenatedWords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to process all words and find concatenated ones
 */
public class ConcatenatedWordsProcessor {
    private static final Logger log = LoggerFactory.getLogger(ConcatenatedWordsProcessor.class);

    /**
     * Finds concatenated words
     *
     * @param words List of Strings with all words to process
     * @exception IllegalArgumentException when provided list of words is empty
     * @return ConcatenatedWords object with all concatenated strings
     */
    public ConcatenatedWords processConcatenatedWords(List<String> words){
        if(words.size() < 1) {
            IllegalArgumentException iae = new IllegalArgumentException("List of words can't be empty");
            log.error("Empty list of words", iae);
            throw iae;
        }

        ConcatenatedWords concatenatedWords = new ConcatenatedWords();
        LinkedList<String> wordsCopy = new LinkedList<>(words);
        LinkedList<String> uniqueWords = new LinkedList<>();

outer:  for(String w : words){
            if(w == null || w.length() < 1) continue;

            for(String wc : wordsCopy){
                if(wc == null || wc.length() < 1 || w.equals(wc)) continue;

                if(w.contains(wc)) {
                    continue outer;
                }
            }

            uniqueWords.add(w);
            log.debug("Found unique word '{}'", w);
        }

        wordsCopy.removeAll(uniqueWords);

        for(String wc : wordsCopy){
            if(wc == null || wc.length() < 1) continue;

            if(isConcatenated(wc, uniqueWords)) {
                concatenatedWords.addConcatenatedWord(wc);
                log.debug("Found concatenated word '{}'", wc);
            }
        }

        return concatenatedWords;
    }

    /**
     * Recursively checks if the word is composed with only unique words
     *
     * @param w - String to check
     * @param uniqueWords - collection of unique words
     * @return true when the word is fully composed with unique words
     */
    private boolean isConcatenated(String w, LinkedList<String> uniqueWords) {
        if(w.length() == 0) return true;

        for(String s : uniqueWords){
            if(w.contains(s)) {
                return isConcatenated(w.replaceAll(s, ""), uniqueWords);
            }
        }

        return false;
    }


}
