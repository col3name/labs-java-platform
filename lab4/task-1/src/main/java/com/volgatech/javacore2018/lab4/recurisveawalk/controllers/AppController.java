package com.volgatech.javacore2018.lab4.recurisveawalk.controllers;

import com.volgatech.javacore2018.lab4.recurisveawalk.util.HashCalculator;
import com.volgatech.javacore2018.lab4.recurisveawalk.view.ResultView;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class AppController {
    private static final Logger LOG = Logger.getLogger(AppController.class.getName());

    private final String inputFileName;
    private final String outputFileName;
    private final HashCalculator hashCalculator;

    public AppController(String inputFileName, String outputFileName, HashCalculator hashCalculator) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.hashCalculator = hashCalculator;
    }

    public void execute(ResultView resultView) {
        try (FileWriter writer = new FileWriter(outputFileName);
             Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
            stream.forEach(line -> recursiveWalk(line, writer, resultView));
            writer.flush();
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
    }

    private void recursiveWalk(String fileDescriptor, FileWriter writer, ResultView resultView) {
        File file = new File(fileDescriptor);

        if (!file.isDirectory()) {
            try {
                BigInteger hash = hashCalculator.calculate(new FileInputStream(fileDescriptor));
                resultView.display(writer, hash, file.getAbsolutePath());
            } catch (FileNotFoundException e) {
                LOG.info(e.getMessage());
            }
        } else {
            scanSubdirectory(writer, file, resultView);
        }
    }

    private void scanSubdirectory(FileWriter writer, File file, ResultView resultView) {
        for (File fileDescriptor : Objects.requireNonNull(file.listFiles())) {
            try {
                if (!fileDescriptor.isDirectory()) {
                    BigInteger hash = hashCalculator.calculate(new FileInputStream(fileDescriptor));
                    resultView.display(writer, hash, file.getAbsolutePath());
                } else {
                    recursiveWalk(fileDescriptor.getPath(), writer, resultView);
                }
            } catch (Exception e) {
                LOG.info(e.getMessage());
            }
        }
    }
}
