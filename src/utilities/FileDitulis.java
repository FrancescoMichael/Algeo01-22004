package utilities;

import utilities.*;

import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileDitulis {
    public static void Codot(String kodok, String path) {
        File file = new File("../test/output/" + path);
        try {

            FileWriter fileWriter = new FileWriter(file);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(kodok);
            bufferedWriter.close();

            System.out.println("succes write to file.");

        } catch (IOException e) {
            System.out.println("Error write file.");
        }
    }
}
