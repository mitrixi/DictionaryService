package com.dictionaryservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class DictionaryService {
    static List<LangDictionary> dictionariesList;
    private static LangDictionary langDictionary;

    public DictionaryService(List<LangDictionary> list) {
        dictionariesList = list;
    }

    public void startWork() {
        System.out.println("Введите \"sd\" для выбора словаря или его смены\n" +
                "Для добавления слова в словарь, введите \"aw\" и в новой строке введите слово и перевод в формате 'слово - перевод'\n" +
                "Для удаления слова из словаря, введите \"dw\" и в новой строке введите необходимое слово\n" +
                "Для поиска слова по словарю, введите команду \"fw\", после чего в новой строке введите искомое слово\n" +
                "Для отображения содержимого всех словарей, введите команду \"daw\"");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            String temp;
            while (!(line = reader.readLine()).equals("exit")) {
                switch (line) {
                    case "aw":
                        temp = reader.readLine();
                        langDictionary.addWord(temp);
                        break;
                    case "fw":
                        temp = reader.readLine();
                        langDictionary.findByKey(temp);
                        break;
                    case "daw":
                        displayAllWords();
                        break;
                    case "dw":
                        temp = reader.readLine();
                        langDictionary.deleteByKey(temp);
                        break;
                    case "sd":
                        selectDictionary(reader);
                        break;
                    default:
                        break;
                }
            }
            langDictionary.closeResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void selectDictionary(BufferedReader reader) throws IOException {
        System.out.println("Please select the appropriate dictionary from list below");
        dictionariesList.forEach(System.out::println);

        String selectDict = reader.readLine();

        for (LangDictionary dictionary : dictionariesList) {
            if (selectDict.equals(dictionary.toString())) {
                langDictionary = dictionary;
            }
        }
    }

    private static void displayAllWords() throws IOException {
        for (LangDictionary dictionary : dictionariesList) {
            dictionary.readAllWords();
        }
    }
}
