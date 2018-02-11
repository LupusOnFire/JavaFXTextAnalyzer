package model;

import java.io.File;
import java.util.*;

public class Reader {
    File inputFile;
    Map<Character, Integer> charMap;
    Map<String, Integer> wordMap;
    int totalCharCount;
    int totalWordCount;
    public Reader(String filePath) throws Exception {
        inputFile = new File(filePath);
        setFileCharacterMap();
        setWordMap();
    }

    public Map<Character, Integer> getCharMap() {
        return charMap;
    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    public int getTotalCharCount() {
        return totalCharCount;
    }

    public int getTotalWordCount() {
        totalWordCount = 0;
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            totalWordCount += entry.getValue();
        }
        return totalWordCount;
    }

    private void setFileCharacterMap() throws Exception {
        charMap = new TreeMap<>();
        totalCharCount = 0;
        Scanner scanner = new Scanner(inputFile, "utf-8");
        while (scanner.hasNext()) {
            char[] chars = scanner.nextLine().toUpperCase().toCharArray();
            for (Character c : chars) {
                if (charMap.containsKey(c)) {
                    charMap.put(c, charMap.get(c) +1);
                } else {
                    charMap.put(c, 1);
                }
                totalCharCount++;
            }
        }
    }

    private void setWordMap() throws Exception {
        wordMap = new TreeMap<>();
        Scanner scanner = new Scanner(inputFile, "utf-8");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            //Split words into a string array
            String[] words = line.split(" ");

            // for every word
            for (int i = 0; i < words.length; i++) {

                // change word to lower case and remove special chars
                words[i] = removeSpecialChars(words[i]).toLowerCase();

                if (wordMap.containsKey(words[i])) {
                    wordMap.put(words[i], wordMap.get(words[i])+1);
                } else {
                    wordMap.put(words[i], 1);
                }
            }
        }
        //remove special chars that has already been replaced
        wordMap.remove("");
    }
    private String removeSpecialChars(String s) {
        return s.replaceAll("[^A-Za-z0-9]", "");
        //return s.replaceAll("[\\W&&[^']]", "");
    }

    public void printCharCountAndPercentage() {
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            System.out.printf("\'%c\' : %-7d %6.2f%s\n", entry.getKey(), entry.getValue(), (float)(entry.getValue()*100)/totalCharCount, "%");
        }
    }

    public void printWordCount() {
        totalWordCount = 0;
        for (Map.Entry<String, Integer> entry : sortByValue(wordMap).entrySet()) {
            System.out.println("'"+ entry.getKey() +"' : " + entry.getValue());
            totalWordCount += entry.getValue();
        }
        System.out.println("Word count: " + totalWordCount);
    }

    //https://www.mkyong.com/java/how-to-sort-a-map-in-java/
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap) {

        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        Collections.sort(list, Comparator.comparing(o -> (o.getValue())));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


}
