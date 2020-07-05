package com.dictionaryservice.dictionaries;

import com.dictionaryservice.LangDictionary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.RandomAccessFile;
@Component
public class LatinDictionary extends LangDictionary {

    public LatinDictionary(String path) throws IOException {
        setRandomAccessFile(new RandomAccessFile(path, "rw"));
    }

    @Override
    protected boolean isValid(String entryKey) throws IOException {
        return entryKey.matches("^[a-zA-Z]{4}\\s-\\s.+") && !super.isExistInDictionary(entryKey);
    }

    @Override
    public void displayTemplate() {
        System.out.println("Напишите слово и перевод в формате 'слово - перевод', где 'слово' состоит из 4-х букв");
    }

    @Override
    public String toString() {
        return "Latin dictionary";
    }
}
