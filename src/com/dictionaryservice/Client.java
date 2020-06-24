package com.dictionaryservice;

import com.dictionaryservice.DictionaryService;
import com.dictionaryservice.LangDictionary;
import com.dictionaryservice.dictionaries.DigitDictionary;
import com.dictionaryservice.dictionaries.LatinDictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException {
        List<LangDictionary> dictionariesList = new ArrayList<>();
        dictionariesList.add(new LatinDictionary("LatinDictionary.txt"));
        dictionariesList.add(new DigitDictionary("DigitDictionary.txt"));


        DictionaryService dictionaryService = new DictionaryService(dictionariesList);
        dictionaryService.startWork();
    }

}
