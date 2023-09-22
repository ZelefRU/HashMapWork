package org.example;

import static org.example.Main.*;

public class test {
    public static void main(String[] args) {

        long start = startTimer();

        long[] number = new long[1000000];
        for (int i = 0; i < number.length - 1; i++) {
            number[i] = generateRandomPhoneNumber(8,777);
        }
        short s = 1;
        stopTimer(start);
    }
}
