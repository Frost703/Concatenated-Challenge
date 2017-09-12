package com.integrityvision.concatenatedchallenge.model;

import java.util.*;

public class AlphabeticListProvider {
    private Map<String, List<String>> alphabet = new HashMap<>();
    private List<String> words = new LinkedList<>();

    public void add(String s){
        if(s == null || s.length() < 1) return;
        String prefix = getPrefix(s);

        if(alphabet.containsKey(prefix)){
            alphabet.get(prefix).add(s);
        }

        else{
            alphabet.put(prefix, new LinkedList<>());
            alphabet.get(prefix).add(s);
        }

        if(!words.contains(s)) words.add(s);
    }

    public List<String> get(String s){
        String prefix = getPrefix(s);

        return alphabet.getOrDefault(prefix, Collections.EMPTY_LIST);
    }

    public List<String> getAll(){
        return words;
    }

    private String getPrefix(String s){
        return s.length() > 1 ? s.substring(0,1) : s;
    }

    public void remove(String w) {
        if(w != null && w.length() > 1) {
            String prefix = getPrefix(w);

            if (alphabet.containsKey(prefix)) {
                alphabet.get(prefix).remove(w);
            }
        }
    }
}
