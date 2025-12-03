package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Long> firstID = new ArrayList<>();
        List<Long> lastID = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/IDs.txt"))) {
            String line = reader.readLine();
            String[] idsRange = null;
            while (line != null) {
                idsRange = line.split(",");
                line = reader.readLine();
            }
            for (String full : idsRange) {
                String[] parts = full.split("-");
                firstID.add(Long.parseLong(parts[0]));
                lastID.add(Long.parseLong(parts[1]));
            }
            // Part 1 (ABAB only)
            long totalInvalidIDs = 0;
            for (int i = 0; i < firstID.size(); i++) {
                long min = firstID.get(i);
                long max = lastID.get(i);
                for (long x = min; x <= max; x++) {
                    int digits = (int) Math.log10(x) + 1;
                    if (digits % 2 != 0) continue; // only even-digit numbers possible
                    int half = digits / 2;
                    long divisor = (long) Math.pow(10, half);
                    long left = x / divisor;
                    long right = x % divisor;
                    if (left == right) {
                        totalInvalidIDs += x;
                    }
                }
            }
            System.out.println("Total Added up Invalid IDs: " + totalInvalidIDs);
            // Part 2 (any repeated pattern)
            long totalInvalidIDs2 = 0;
            for (int i = 0; i < firstID.size(); i++) {
                long min = firstID.get(i);
                long max = lastID.get(i);
                for (long x = min; x <= max; x++) {
                    if (isRepeatedPattern(x)) {
                        totalInvalidIDs2 += x;
                    }
                }
            }
            System.out.println("Total Added up Invalid IDs for Part 2: " + totalInvalidIDs2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isRepeatedPattern(long x) {
        String s = Long.toString(x);
        // double-string trick
        String doubled = s + s;
        String middle = doubled.substring(1, doubled.length() - 1);
        return middle.contains(s);
    }
}
