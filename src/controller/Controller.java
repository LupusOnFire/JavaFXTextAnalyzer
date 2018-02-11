package controller;

import model.DataMapper;
import model.Reader;
import view.ObservableData;

import java.util.List;

public class Controller {
    private Reader reader;
    private DataMapper dataMapper;
    public Controller() {
        dataMapper = new DataMapper();
    }

    public void setFile(String filepath) throws Exception {
        reader = new Reader(filepath);
    }

    public List<ObservableData> getWordDataList() {
        System.out.println(reader.getTotalWordCount());
        return dataMapper.wordMapToDataList(reader.getWordMap(), reader.getTotalWordCount());
    }

    public List<ObservableData> getCharDataList() {
        return dataMapper.charMapToDataList(reader.getCharMap(), reader.getTotalCharCount());
    }
}
