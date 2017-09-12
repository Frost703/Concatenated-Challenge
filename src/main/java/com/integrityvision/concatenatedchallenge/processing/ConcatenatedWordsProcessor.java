package com.integrityvision.concatenatedchallenge.processing;

import com.integrityvision.concatenatedchallenge.model.AlphabeticListProvider;
import com.integrityvision.concatenatedchallenge.model.ConcatenatedWords;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;
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
     * @param provider Alphabetic List Provider with all words to process
     * @exception IllegalArgumentException when provided list of words is empty
     * @return ConcatenatedWords object with all concatenated strings
     */
    public ConcatenatedWords processConcatenatedWords(AlphabeticListProvider provider){
        List<String> words = provider.getAll();
        if(words.size() < 1) {
            IllegalArgumentException iae = new IllegalArgumentException("List of words can't be empty");
            log.error("Empty list of words", iae);
            throw iae;
        }

        ConcatenatedWords concatenatedWords = new ConcatenatedWords();

        for(String w : words){
            if(w == null || w.length() < 1) continue;

            List<String> initWords = new LinkedList<>(provider.get(w));
            initWords.remove(w);

            if(isConcatenated(w, provider, initWords)){
                concatenatedWords.addConcatenatedWord(w);
            }

            initWords.add(w);
        }

        return concatenatedWords;
    }

    /**
     * Recursively checks if the word is composed with other words
     *
     * @param w - String to check
     * @param provider - Object with all words grouped by first letter
     * @return true when the word is fully composed with unique words
     */
    private boolean isConcatenated(String w, AlphabeticListProvider provider, List<String> words) {
        int length = w.length();
        if (length == 0) return true;

        int start = 0;
        int end = 1;
        while (end != length + 1) {
            if (words.contains(w.substring(start, end))) {
                String nextIteration = w.substring(end, w.length());
                if (isConcatenated(nextIteration, provider, provider.get(nextIteration))) {
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
