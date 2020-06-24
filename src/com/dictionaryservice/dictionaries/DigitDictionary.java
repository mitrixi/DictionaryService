package com.dictionaryservice.dictionaries;

import com.dictionaryservice.LangDictionary;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DigitDictionary extends LangDictionary {

    public DigitDictionary(String path) throws IOException {
        setRandomAccessFile(new RandomAccessFile(path, "rw"));
    }

    @Override
    protected boolean isValid(String entryKey) throws IOException {
        return entryKey.matches("^\\d{5}\\s-\\s.+") && !super.isExistInDictionary(entryKey);
    }

    @Override
    public String toString() {
        return "Digit dictionary";
    }
}
