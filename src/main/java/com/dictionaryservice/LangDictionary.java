package com.dictionaryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class LangDictionary {
    private RandomAccessFile raf;

    public void setRandomAccessFile(RandomAccessFile raf) {
        this.raf = raf;
    }
    
    public void readAllWords() throws IOException {
        String line;
        raf.seek(0);
        while ((line = raf.readLine()) != null)
            System.out.println(line);
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
                System.out.println("Данная запись удалена");
            }
            writePos = raf.getFilePointer();
        }
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
            if (line.startsWith(wordKey))
                System.out.println(line);
    }


    public void addWord(String entry) throws IOException {
        if (this.isValid(entry)) {
            raf.seek(raf.length());
            raf.write(entry.getBytes(StandardCharsets.UTF_8));
            raf.writeChars("\n");
            System.out.println("Запись добавлена в словарь");
        }
    }


    protected abstract boolean isValid(String entry) throws IOException;

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
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
