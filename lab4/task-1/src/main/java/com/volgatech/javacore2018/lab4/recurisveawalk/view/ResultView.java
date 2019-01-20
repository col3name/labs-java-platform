package com.volgatech.javacore2018.lab4.recurisveawalk.view;

import java.io.FileWriter;
import java.math.BigInteger;

public interface ResultView {
    void display(FileWriter writer, BigInteger hash, String canonicalPath);
}
