/**
 * Classname: Main
 *
 * @version 22.06.2020
 * @author Kopach Daria
 * <p>
 * Home task. Files management.
 * <p>
 * 1. Parse the file logs.txt (see the attachment).
 * Extract to a separate file all the logs.
 * <p>
 * 2. Calculate the total number of logs (lines).
 * <p>
 * 3. Calculate the total  number of  ERROR logs.
 * Use previous skills - String.split
 * <p>
 * 4. Calculate the total number of ERROR logs. Use Files.lines.
 * <p>
 * 5. Compare two results.
 */

package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    /**
     * Main method to execute
     * @param args String params.
     * @throws IOException throws error if there is no file exists.
     */

    public static void main(String[] args) throws IOException {

        //  1. Parse the file logs.txt (see the attachment).
        //  Extract to a separate file all the logs.

        System.out.println
                ("\n ------------------- TASK 1 -------------------");

        // parse the file

        String text = new String(Files.readAllBytes(Paths.get
                ("/Users/Nastya/Desktop/logs.txt")));

        // create a list with lines which contaning errors from file

        List<String> allLogs =
                Files.lines(Paths.get("/Users/Nastya/Desktop/logs.txt"))
                        .filter(line -> line.contains("ERROR"))
                        .collect(Collectors.toList());

        // @param octoberLogs return empty line and filling

        String log = "";
        for (String line : allLogs) {
            log += line + System.lineSeparator();
        }

        // Creation file alllogs.txt and recording data about all logs

        Path path = Paths.get("/Users/Nastya/Desktop/alllogs.txt");
        Files.write(path, log.getBytes());

        System.out.println
                ("\n File with all logs are sucessfully created on your Desktop");

        System.out.println
                ("\n ------------------- TASK 2 -------------------");

        // Calculate the number of lines in all file using split

        String[] lines = text.split("\\n");

        System.out.println("\n The file logs.txt contains: " +
                lines.length + " lines");

        System.out.println
                ("\n ------------------- TASK 3 -------------------");

        // calculate the number of lines containing error using String.split

        LocalDateTime startString = LocalDateTime.now();

        int counterErrors = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("ERROR")) {
                counterErrors++;
            }
        }

        System.out.println
                ("\n This file logs.txt contains: "
                        + counterErrors + " ERROR logs");

        LocalDateTime finishString = LocalDateTime.now();

        // print out on console time for process above

        System.out.println("Using String.split for finding ERRORS needs "
                + ChronoUnit.MILLIS.between(startString, finishString)
                + " milliseconds");


        System.out.println
                ("\n ------------------- TASK 4 -------------------");


        // 4. Calculate the total number of ERROR logs. Use Files.lines.

        LocalDateTime startFiles = LocalDateTime.now();

        // calculate the number of lines contains error using Files.lines

        final long counterErrors2 =
                Files.lines(Paths.get("/Users/Nastya/Desktop/logs.txt"))
                        .filter(line -> line.contains("ERROR"))
                        .count();

        // print out on console the number of lines contains error

        System.out.println("\n This file logs.txt contains: "
                + counterErrors2 + " ERROR logs");

        LocalDateTime finishFiles = LocalDateTime.now();

        // print out on console time for process above

        System.out.println("Using Files.lines for finding ERRORS needs "
                + ChronoUnit.MILLIS.between(startFiles, finishFiles)
                + " milliseconds");


        /**  ------------ MY OUTPUT -------------
         ------------------- TASK 1 -------------------

         File with all logs are sucessfully created on your Desktop

         ------------------- TASK 2 -------------------

         The file logs.txt contains: 2845607 lines

         ------------------- TASK 3 -------------------

         This file logs.txt contains: 361 ERROR logs
         Using String.split for finding ERRORS needs 1119 milliseconds

         ------------------- TASK 4 -------------------

         This file logs.txt contains: 361 ERROR logs
         Using Files.lines for finding ERRORS needs 18066 milliseconds

         Process finished with exit code 0

        */
    }
}
