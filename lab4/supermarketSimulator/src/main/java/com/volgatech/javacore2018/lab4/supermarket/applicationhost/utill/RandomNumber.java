package com.volgatech.javacore2018.lab4.supermarket.applicationhost.utill;

public class RandomNumber {

    private RandomNumber() {
    }

    public static int getNumber(Integer min, Integer max) {
        return min + RandomSource.getInstance().nextInt(max);
    }

    public static int getNumber(Integer max) {
        return getNumber(0, max);
    }
}
