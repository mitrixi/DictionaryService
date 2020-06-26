package com.dictionaryservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args)  {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        DictionaryService dictionaryService = context.getBean("dictionaryService", DictionaryService.class);
        dictionaryService.startWork();
    }

}
