package Lesson4;

import java.util.ArrayList;
import java.util.List;

public class Homework4 {
    private final Object monitor = new Object();

    private static final int LETTERS_NUMBER = 3;
    private static final int ITERATIONS = 5;

    private static final char LAST_LETTER = 'A' + LETTERS_NUMBER - 1;
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Homework4 printLetters = new Homework4();
        try {
            printLetters.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void run() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < LETTERS_NUMBER; i++) {
            char letter = (char) ('A' + i);
            threads.add(new Thread(() -> print(letter)));
            threads.get(i).start();
        }

        for (int i = 0; i < LETTERS_NUMBER; i++) {
            threads.get(i).join();
        }
    }

    void print(char letter) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < ITERATIONS; i++) {
                    while (currentLetter != letter) {
                        monitor.wait();
                    }
                    System.out.print(letter);

                    if (currentLetter != LAST_LETTER) {
                        currentLetter++;
                    } else {
                        currentLetter = 'A';
                    }
                    monitor.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}