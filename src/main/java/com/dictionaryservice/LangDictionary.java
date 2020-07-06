package com.dictionaryservice;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LangDictionary {
    private RandomAccessFile raf;
    private String template;
    private String displayTemplate;
    private String name;

    public LangDictionary(String name) throws FileNotFoundException {
        setRandomAccessFile(new RandomAccessFile(name.trim() + "Dictionary.txt", "rw"));
    }

    public void setRandomAccessFile(RandomAccessFile raf) {
        this.raf = raf;
    }

    public void readAllWords() throws IOException {
        String line;
        raf.seek(0);
        while ((line = raf.readLine()) != null)
            OutputChannelInformation.displayMessage(line);
    }

    public void deleteByKey(String wordKey) throws IOException {
        String line;
        long readPos;
        long writePos = 0;
        raf.seek(0);
        while ((line = raf.readLine()) != null) {
            if (line.startsWith(wordKey)) {
                readPos = raf.getFilePointer();
                byte[] buff = new byte[1024];
                int n;
                while ((n = raf.read(buff)) != -1) {
                    raf.seek(writePos);
                    raf.write(buff, 0, n);
                    readPos += n;
                    writePos += n;
                    raf.seek(readPos);
                }
                raf.setLength(writePos);
                OutputChannelInformation.displayMessage("Данная запись удалена");
                return;
            }
            writePos = raf.getFilePointer();
        }
        OutputChannelInformation.displayMessage("Такого слова нет в словаре");
    }


//    public void deleteByKey(String wordKey) throws IOException {
//        String line;
//        long pointer = 0;
//        long pointer2 = 0;
//        long pointer3 = 0;
//
//        raf.seek(0);
//        while ((line = raf.readLine()) != null) {
//            if (line.startsWith(wordKey)) {
//                pointer2 = raf.getFilePointer();
//                while ((line = raf.readLine()) != null) {
//                    pointer3 = raf.getFilePointer();
//                    raf.seek(pointer);
//                    raf.write(line.getBytes(StandardCharsets.UTF_8));
//                    pointer = pointer2;
//                    raf.seek(pointer3);
//                    pointer2 = raf.getFilePointer();
//                }
//                raf.setLength(pointer);
////                raf.write("\n".getBytes());
//            }
//            pointer = raf.getFilePointer();
//        }
//    }


    public void findByKey(String wordKey) throws IOException {
        String line;
        raf.seek(0);
        while ((line = raf.readLine()) != null)
            if (line.startsWith(wordKey)) {
                OutputChannelInformation.displayMessage(line);
                return;
            }
        OutputChannelInformation.displayMessage("Такого слова нет в словаре");

    }

    public void addWord(String entry) throws IOException {
        if (this.isValid(entry)) {
            raf.seek(raf.length());
            raf.write(entry.getBytes(StandardCharsets.UTF_8));
            raf.writeChars("\n");
            OutputChannelInformation.displayMessage("Запись добавлена в словарь");
        } else
            OutputChannelInformation.displayMessage("Введенные данные не соответствуют примеру");
    }

    protected boolean isValid(String entry) throws IOException {
        return entry.matches(template);
    }

    public void setRegExp(String regex) {
        template = regex;
    }

    public void setDisplayTemplate(String template) {
        displayTemplate = template;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void displayTemplate() {
        OutputChannelInformation.displayMessage(displayTemplate);
    }

    protected boolean isExistInDictionary(String entry) throws IOException {
        String line;
        raf.seek(0);
        while ((line = raf.readLine()) != null)
            if (line.startsWith(entry.split(" - ")[0]))
                return true;
        return false;
    }

    public void closeResources() {
        try {
            OutputChannelInformation.close();
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
