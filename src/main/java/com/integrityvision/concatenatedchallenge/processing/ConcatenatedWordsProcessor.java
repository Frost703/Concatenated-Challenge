package com.integrityvision.concatenatedchallenge.processing;

import com.integrityvision.concatenatedchallenge.model.ConcatenatedWords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
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
            log.debug("Concatenated words length is " + concatenatedWords.getConcatenatedWordsAmount());

            Iterator<String> wordsCopyIterator = wordsCopy.iterator();
            while(wordsCopyIterator.hasNext()){
                String wc = wordsCopyIterator.next();
                if(wc != null && wc.contains(w)) {
                    if(wc.equals(w)) continue;

                    concatenatedWords.addConcatenatedWord(wc);
                    wordsCopyIterator.remove();

                    log.debug("Word '" + wc + "' contains '" + w + "'");
                }
            }
        }

        return concatenatedWords;
    }
}
