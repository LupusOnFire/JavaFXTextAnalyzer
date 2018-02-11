package model;

import view.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataMapper {
    public List<Data> charMapToDataList(Map<Character, Integer> charMap, int totalCharCount){
        List<Data> charList = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            Data word = new Data(""+entry.getKey(), entry.getValue(), (float)(entry.getValue()*100)/totalCharCount);
            charList.add(word);
        }
        return charList;
    }

    public List<Data> wordMapToDataList(Map<String, Integer> wordMap, int totalWordCount) {
        List<Data> wordList = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            Data word = new Data(entry.getKey(), entry.getValue(), (float)(entry.getValue()*100)/totalWordCount);
            wordList.add(word);
        }
        return wordList;
    }
}
