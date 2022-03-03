package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        FileInputStream fr = null;

        System.out.println("Enter filename to read:");
        while (true) {
            try {
                String filename = scan.nextLine();
                fr = new FileInputStream(filename);
                break;
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. Enter filename once more:");
            }
        }

        ////
        int size = 'z' + 1;
        int[] frequency = new int[size];
        for (int i = 0; i < size; i++) {
            frequency[i] = 0;
        }

        int symb = 0;
        try {
            while ((symb = fr.read()) != -1) {
                if (symb >= 'A' && symb <= 'Z' || symb >= 'a' && symb <= 'z') {
                    frequency[symb]++;
                }
            }
        } catch (IOException ex) {
            System.out.println("can't read file");
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                System.out.println("cannot close file");
            }
        }

        // A - Z  65 - 90
        // a - z 97 - 122
        FileOutputStream fw;

        System.out.println("Enter filename to write:");
        while(true) {
            try {
                String out_filename = scan.nextLine();
                fw = new FileOutputStream(out_filename);
                break;
            } catch (FileNotFoundException ex) {
                System.out.println("file not found. enter filename once more:");
            }
        }

        try {
            for (char i = 'A'; i < size; ++i) {
                if (i <= 'Z' || i >= 'a') {
                    fw.write(i);
                    fw.write(' ');
                    fw.write(Integer.toString(frequency[i]).getBytes(StandardCharsets.UTF_8));
                    fw.write('\n');
                }
            }
        } catch (IOException ex) {
            System.out.println();
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                System.out.println("cannot close file");
            }
        }

    }
}