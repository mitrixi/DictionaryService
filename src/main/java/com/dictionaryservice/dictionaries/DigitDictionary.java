package com.dictionaryservice.dictionaries;

import com.dictionaryservice.LangDictionary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.RandomAccessFile;
@Component
public class DigitDictionary extends LangDictionary {

    public DigitDictionary(String path) throws IOException {
        setRandomAccessFile(new RandomAccessFile(path, "rw"));
    }

    @Override
    protected boolean isValid(String entryKey) throws IOException {
        return entryKey.matches("^\\d{5}\\s-\\s.+") && !super.isExistInDictionary(entryKey);
    }

    @Override
    public void displayTemplate() {
        System.out.println("Напишите слово и перевод в формате 'слово - перевод', где 'слово' состоит из 5-и цифр");
    }

    @Override
    public String toString() {
        return "Digit dictionary";
    }
}
