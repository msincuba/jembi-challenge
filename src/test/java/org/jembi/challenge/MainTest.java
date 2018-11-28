package org.jembi.challenge;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void savePatients() throws IOException {
        URL patientUrl = getClass().getClassLoader().getResource("patients.txt");
        String fileName = patientUrl.getFile();
        File file = new File(fileName);
        Files.readAllLines(Paths.get(fileName));
        Scanner scanner = new Scanner(file);
        Main main = new Main();
        int patients = main.savePatients(scanner);
        assertEquals("Original file has 7 items with duplicates", 7, Files.readAllLines(Paths.get(fileName)).size());
        assertEquals("Duplicate record was ignored", 5 , patients);
        scanner.close();
    }
}