package utilities;
import utilities.*;

import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileDitulis {
    public static void Codot(String[] kodok, String path) {
      File file = new File(path);

      try {
        if (file.exists()) {
            System.err.println("File e wis onok sam, gaweno sek anyar kono");
        } else {
            FileWriter fileWriter = new FileWriter(path);

            // Create a BufferedWriter for efficient writing
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < kodok.length; i++) {
                bufferedWriter.write(kodok[i]);
            }
            bufferedWriter.newLine(); // Add a newline character
            bufferedWriter.write("Tes cok");

            // Close the BufferedWriter to flush and close the file
            bufferedWriter.close();

            System.out.println("Data has been written to the file.");
        }
        }
        catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
    }   
}
}
