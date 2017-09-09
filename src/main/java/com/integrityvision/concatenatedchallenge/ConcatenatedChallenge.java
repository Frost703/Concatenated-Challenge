package com.integrityvision.concatenatedchallenge;

import com.integrityvision.concatenatedchallenge.input.FileStringsLoader;
import com.integrityvision.concatenatedchallenge.model.ConcatenatedWords;
import com.integrityvision.concatenatedchallenge.processing.ConcatenatedWordsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

/**
 * Main class. Start the application
 */
public class ConcatenatedChallenge {
    private static final Logger log = LoggerFactory.getLogger(ConcatenatedChallenge.class);

    /**
     * Outputs total amount of concatenated words + longest and second longest concatenated words
     */
    public static void main(String[] args) {
        LocalTime start = LocalTime.now();
        log.info("Initializing application");

        ConcatenatedWordsProcessor processor = new ConcatenatedWordsProcessor();
        ConcatenatedWords words = processor.processConcatenatedWords(new FileStringsLoader().load());

        log.info("Total amount of concatenated words is " + words.getConcatenatedWordsAmount());
        log.info("Longest concatenated word is '" + words.getLongestConcatenated() + "' with length " + words.getLongestConcatenated().length());
        log.info("Second longest concatenated word is '" + words.getSecondLongestConcatenated() + "' with length " + words.getSecondLongestConcatenated().length());

        LocalTime end = LocalTime.now();

        log.debug("This run took " + (end.toSecondOfDay() - start.toSecondOfDay()) + " seconds");
    }
}
