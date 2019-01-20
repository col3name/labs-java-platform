package com.volgatech.javacore2018.lab4.recurisveawalk;

import com.volgatech.javacore2018.lab4.recurisveawalk.controllers.AppController;
import com.volgatech.javacore2018.lab4.recurisveawalk.exceptions.ApplicationException;
import com.volgatech.javacore2018.lab4.recurisveawalk.util.FNVHash;
import com.volgatech.javacore2018.lab4.recurisveawalk.view.ConsoleResultView;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new ApplicationException("Invalid argument count\nUsage java -jar target/task1-1.0.SNAPSHOT.jar <input file> <output file>");
            }

            String inputFileName = args[0];
            String outputFileName = args[1];
            AppController recursiveWalkController = new AppController(inputFileName, outputFileName, new FNVHash());
            recursiveWalkController.execute(new ConsoleResultView());

        } catch (Exception e) {
            LOG.warning(e.getMessage());
        }
    }
}
