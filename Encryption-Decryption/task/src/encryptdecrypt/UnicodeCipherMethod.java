package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UnicodeCipherMethod implements CipherMethod {

    @Override
    public void encodeData(String data, int key) {
        for (char symbol : data.toCharArray()) {
            char item = (char) (symbol + key);
            System.out.print(item);
        }
    }

    @Override
    public void decodeData(String data, int key) {
        for (char symbol : data.toCharArray()) {
            char item = (char) (symbol - key);
            System.out.print(item);
        }
    }

    @Override
    public void encodeToFile(String from, int key, String to) {
        File file = new File(to);
        try(FileWriter writer = new FileWriter(file)) {
            for (char symbol : readFile(from)) {
                char item = (char) (symbol + key);
                writer.append(item);
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    @Override
    public void decodeToFile(String from, int key, String to) {
        File file = new File(to);
        try(FileWriter writer = new FileWriter(file)) {
            for (char symbol : readFile(from)) {
                char item = (char) (symbol - key);
                writer.append(item);
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    private char[] readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName))).toCharArray();
    }
}
