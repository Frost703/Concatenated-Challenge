package com.integrityvision.concatenatedchallenge.processing;

import com.integrityvision.concatenatedchallenge.model.AlphabeticListProvider;
import com.integrityvision.concatenatedchallenge.model.ConcatenatedWords;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConcatenatedWordsProcessorTest {
    private List<String> testWords;

    @Before
    public void populateListWithTestWords(){
        testWords = new LinkedList<>();
        testWords.addAll(Arrays.asList("aa","aah","aahed","aahing","aahs","aal","aalii","aaliis","aals","aardvark"
                ,"aardvarks","aardwolf","aardwolves","aargh","aarrgh","aarrghh","aas","aasvogel","aasvogels","ab", "ba"
                ,"aaab","abaaab", "aahaal", "baab",
                null, null, "", ""));
    }

    @Test
    public void hasCorrectProcessingOutput(){
        ConcatenatedWordsProcessor processor = new ConcatenatedWordsProcessor();
        AlphabeticListProvider provider = new AlphabeticListProvider();
        for(String s : testWords){
            provider.add(s);
        }

        ConcatenatedWords words = processor.processConcatenatedWords(provider);

        assertEquals(4, words.getConcatenatedWordsAmount());
        assertEquals(6, words.getLongestConcatenatedLength());
        assertEquals(4, words.getSecondLongestConcatenatedLength());
    }
}
