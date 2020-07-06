package com.dictionaryservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class DictionaryService {
    private static LangDictionary langDictionary;
    private static List<LangDictionary> dictionariesList;

    public DictionaryService(List<LangDictionary> list) {
        dictionariesList = list;
    }

    public void startWork() {
        System.out.println("Введите \"sd\" для выбора словаря или его смены\n" +
                "Для добавления слова в словарь, введите \"aw\" и в новой строке введите слово и перевод\n" +
                "Для удаления слова из словаря, введите \"dw\" и в новой строке введите необходимое слово\n" +
                "Для поиска слова по словарю, введите команду \"fw\", после чего в новой строке введите искомое слово\n" +
                "Для отображения содержимого всех словарей, введите команду \"daw\"");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            String temp;

            while (true) {
                if (reader.readLine().equals("sd")) {
                    selectDictionary(reader);
                    break;
                }
            }

            while (!(line = reader.readLine()).equals("exit")) {
                switch (line) {
                    case "aw":
                        langDictionary.displayTemplate();
                        temp = reader.readLine();
                        langDictionary.addWord(temp);
                        break;
                    case "fw":
                        System.out.println("Введите слово для поиска");
                        temp = reader.readLine();
                        langDictionary.findByKey(temp);
                        break;
                    case "daw":
                        displayAllWords();
                        break;
                    case "dw":
                        System.out.println("Введите слово, запись к которому желаете удалить");
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
        System.out.println("Выберите номер необходимого словаря из списка ниже");
        int incCount = 0;
        for (LangDictionary dictionary : dictionariesList) {
            System.out.println(++incCount + " : " + dictionary.getName());
        }

        int selectDict;

        while (true) {
            selectDict = Integer.parseInt(reader.readLine());
            if (selectDict <= dictionariesList.size())
                break;
            System.out.println("Введите корректное значение");
        }

        for (int i = 0; i < dictionariesList.size(); i++) {
            if (selectDict == i + 1) {
                langDictionary = dictionariesList.get(i);
                System.out.println("Вы выбрали " + dictionariesList.get(i).getName());
            }
        }
    }

    private static void displayAllWords() throws IOException {
        for (LangDictionary dictionary : dictionariesList) {
            dictionary.readAllWords();
        }
    }
}
