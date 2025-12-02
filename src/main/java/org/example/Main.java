package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        List<Long> firstID = new ArrayList<>();
        List<Long> lastID = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/IDs.txt"));
            String line = reader.readLine();

            String[] idsRange = null;
            while (line != null) {
                idsRange = line.split(",");
                line = reader.readLine();
            }
            reader.close();
            for (String full : idsRange) {
                String[] parts = full.split("-");
                firstID.add(Long.parseLong(parts[0]));
                lastID.add(Long.parseLong(parts[1]));
            }
            //Add up all of the invalid IDs!
            long totalInvalidIDs = 0;
            for (int i = 0; i < firstID.size(); i++) {
                long min = firstID.get(i);
                long max = lastID.get(i);
                for (long x = min; x <= max; x++) {
                    int digits = (int) Math.log10(x) + 1;
                    // Only even-digit numbers can be invalid
                    if (digits % 2 != 0) continue;
                    int half = digits / 2;
                    long divisor = (long) Math.pow(10, half);
                    long left = x / divisor;
                    long right = x % divisor;
                    if (left == right) {
                        totalInvalidIDs += x;
                    }
                }
            }
            //Show answer!
            System.out.println("Total Added up Invalid IDs: " + totalInvalidIDs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
