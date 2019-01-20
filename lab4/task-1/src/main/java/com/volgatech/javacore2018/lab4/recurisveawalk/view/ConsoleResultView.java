package com.volgatech.javacore2018.lab4.recurisveawalk.view;


import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Logger;

public class ConsoleResultView implements ResultView {
    private static final Logger LOG = Logger.getLogger(ConsoleResultView.class.getName());

    @Override
    public void display(FileWriter writer, BigInteger hash, String canonicalPath) {
        try {
            writer.append(hash.toString(16))
                    .append(" : ").append(canonicalPath).append("\n");
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
    }
}
