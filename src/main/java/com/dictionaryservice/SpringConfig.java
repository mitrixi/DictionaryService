package com.dictionaryservice;

import com.dictionaryservice.dictionaries.DigitDictionary;
import com.dictionaryservice.dictionaries.LatinDictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Configuration
@ComponentScan("com.dictionaryservice")
public class SpringConfig {

    @Bean
    public DigitDictionary digitDictionary() throws IOException {
        return new DigitDictionary(DigitDictionary.class.getSimpleName() + ".txt");
    }
    @Bean
    public LatinDictionary latinDictionary() throws IOException {
        return new LatinDictionary(LatinDictionary.class.getSimpleName() + ".txt");
    }
}
