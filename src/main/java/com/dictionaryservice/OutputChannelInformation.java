package com.dictionaryservice;

import java.io.PrintStream;


public class OutputChannelInformation {
    private static final PrintStream printStream = new PrintStream(System.out);

    public static void displayMessage(String message) {
        printStream.println(message);
    }

    public static void close() {
        printStream.close();
    }

}
