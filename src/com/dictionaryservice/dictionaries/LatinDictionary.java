package com.dictionaryservice.dictionaries;

import com.dictionaryservice.LangDictionary;

import java.io.IOException;
import java.io.RandomAccessFile;

public class LatinDictionary extends LangDictionary {

    public LatinDictionary(String path) throws IOException {
        setRandomAccessFile(new RandomAccessFile(path, "rw"));
    }

    @Override
    protected boolean isValid(String entryKey) throws IOException {
        return entryKey.matches("^[a-zA-Z]{4}\\s-\\s.+") && !super.isExistInDictionary(entryKey);
    }

    @Override
    public String toString() {
        return "Latin dictionary";
    }
}
