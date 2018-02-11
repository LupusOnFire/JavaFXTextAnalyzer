package model;

import view.ObservableData;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataMapper {
    public List<ObservableData> charMapToDataList(Map<Character, Integer> charMap, int totalCharCount){
        List<ObservableData> charList = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            ObservableData word = new ObservableData(""+entry.getKey(), entry.getValue(), (float)(entry.getValue()*100)/totalCharCount);
            charList.add(word);
        }
        return charList;
    }

    public List<ObservableData> wordMapToDataList(Map<String, Integer> wordMap, int totalWordCount) {
        List<ObservableData> wordList = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            ObservableData word = new ObservableData(entry.getKey(), entry.getValue(), (float)(entry.getValue()*100)/totalWordCount);
            wordList.add(word);
        }
        return wordList;
    }
}
