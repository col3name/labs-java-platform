package com.volgatech.javacore2018.lab4.supermarket.applicationhost.utill;

import java.util.Random;

public class RandomSource {
    private static Random random;

    private RandomSource() {
    }

    public static Random getInstance() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }
}
