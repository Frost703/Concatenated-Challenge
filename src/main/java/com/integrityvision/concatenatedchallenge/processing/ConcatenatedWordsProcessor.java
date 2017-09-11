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

        for(String w : words){
            if(w == null || w.length() < 1) continue;

            wordsCopy.remove(w);
            if(isConcatenated(w, wordsCopy)){
                concatenatedWords.addConcatenatedWord(w);
            }
            wordsCopy.add(w);

            log.debug("Total amount of concatenated words '{}'", concatenatedWords.getConcatenatedWordsAmount());
        }

        return concatenatedWords;
    }

    /**
     * Recursively checks if the word is composed with other words
     *
     * @param w - String to check
     * @param words - collection of unique words
     * @return true when the word is fully composed with unique words
     */
    private boolean isConcatenated(String w, LinkedList<String> words) {
        int length = w.length();
        if (length == 0) return true;

        int start = 0;
        int end = 1;
        while (end != length + 1) {
            if (words.contains(w.substring(start, end))) {
                if (isConcatenated(w.substring(end, w.length()), words)) {
                    return true;
                } else {
                    end++;
                }
            } else {
                end++;
            }
        }

        return false;
    }
}
