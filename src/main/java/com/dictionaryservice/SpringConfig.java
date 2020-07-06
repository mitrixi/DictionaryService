package com.dictionaryservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.dictionaryservice")
public class SpringConfig {

    @Bean
    public LangDictionary digitDictionary() throws IOException {
        LangDictionary digitDictionary = new LangDictionary("Digit");
        digitDictionary.setRegExp("^\\d{5}\\s-\\s.+");
        digitDictionary.setDisplayTemplate("Напишите слово и перевод в формате 'слово - перевод', где 'слово' состоит из 5-х цифр");
        digitDictionary.setName("DigitDictionary");

        return digitDictionary;
    }

    @Bean
    public LangDictionary latinDictionary() throws IOException {
        LangDictionary latinDictionary = new LangDictionary("Latin");
        latinDictionary.setRegExp("^[a-zA-Z]{4}\\s-\\s.+");
        latinDictionary.setDisplayTemplate("Напишите слово и перевод в формате 'слово - перевод', где 'слово' состоит из 4-х букв");
        latinDictionary.setName("LatinDictionary");

        return latinDictionary;
    }

    @Bean
    public List<LangDictionary> dictionariesList() throws IOException {
        List<LangDictionary> dictionariesList = new ArrayList<>();
        dictionariesList.add(digitDictionary());
        dictionariesList.add(latinDictionary());
        return dictionariesList;
    }

    @Bean
    public DictionaryService dictionaryService() throws IOException {
        return new DictionaryService(dictionariesList());
    }
}
