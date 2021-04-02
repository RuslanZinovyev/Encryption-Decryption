package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShiftCipherMethod implements CipherMethod {

    private static final String FIRST_CIRCLE = "abcdefghijklmnopqrstuvwxyz";
    private static final String SECOND_CIRCLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public void encodeData(String data, int key) {
        for (char symbol : data.toCharArray()) {

           int indexFirstCircle = FIRST_CIRCLE.indexOf(symbol);
            int indexSecondCircle = SECOND_CIRCLE.indexOf(symbol);

           if (indexFirstCircle != -1) {
               indexFirstCircle = FIRST_CIRCLE.indexOf(symbol) + key;
               System.out.print(FIRST_CIRCLE.charAt(indexFirstCircle % FIRST_CIRCLE.length()));
           } else if (indexSecondCircle != -1) {
                indexSecondCircle = SECOND_CIRCLE.indexOf(symbol) + key;
                System.out.print(FIRST_CIRCLE.charAt(indexSecondCircle % SECOND_CIRCLE.length()));
            } else {
               System.out.print(symbol);
           }
        }
    }

    @Override
    public void decodeData(String data, int key) {
        for (char symbol : data.toCharArray()) {

            int indexFirstCircle = FIRST_CIRCLE.indexOf(symbol);
            int indexSecondCircle = SECOND_CIRCLE.indexOf(symbol);
            int newIndex;

            if (indexFirstCircle != -1) {
                indexFirstCircle = FIRST_CIRCLE.indexOf(symbol) - key;
                if (indexFirstCircle < 0) {
                    newIndex = FIRST_CIRCLE.length() - Math.abs(indexFirstCircle % FIRST_CIRCLE.length());
                } else {
                    newIndex = Math.abs(indexFirstCircle % FIRST_CIRCLE.length());
                }
                System.out.print(FIRST_CIRCLE.charAt(newIndex));
            } else if (indexSecondCircle != -1) {
                indexSecondCircle = SECOND_CIRCLE.indexOf(symbol) - key;
                if (indexSecondCircle < 0) {
                    newIndex = SECOND_CIRCLE.length() - Math.abs(indexSecondCircle % SECOND_CIRCLE.length());
                } else {
                    newIndex = Math.abs(indexSecondCircle % SECOND_CIRCLE.length());
                }

                System.out.print(SECOND_CIRCLE.charAt(newIndex));
            } else {
                System.out.print(symbol);
            }
        }
    }

    @Override
    public void encodeToFile(String from, int key, String to) {
        File file = new File(to);
        try(FileWriter writer = new FileWriter(file)) {
            for (char symbol : readFile(from)) {

                int indexFirstCircle = FIRST_CIRCLE.indexOf(symbol);
                int indexSecondCircle = SECOND_CIRCLE.indexOf(symbol);

                if (indexFirstCircle != -1) {
                    indexFirstCircle = FIRST_CIRCLE.indexOf(symbol) + key;
                    writer.append(FIRST_CIRCLE.charAt(indexFirstCircle % FIRST_CIRCLE.length()));
                } else if (indexSecondCircle != -1) {
                    indexSecondCircle = SECOND_CIRCLE.indexOf(symbol) + key;
                    writer.append(SECOND_CIRCLE.charAt(indexSecondCircle % SECOND_CIRCLE.length()));
                } else {
                    writer.append(symbol);
                }
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

                int indexFirstCircle = FIRST_CIRCLE.indexOf(symbol);
                int indexSecondCircle = SECOND_CIRCLE.indexOf(symbol);
                int newIndex;

                if (indexFirstCircle != -1) {
                    indexFirstCircle = FIRST_CIRCLE.indexOf(symbol) - key;
                    if (indexFirstCircle < 0) {
                        newIndex = FIRST_CIRCLE.length() - Math.abs(indexFirstCircle % FIRST_CIRCLE.length());
                    } else {
                        newIndex = Math.abs(indexFirstCircle % FIRST_CIRCLE.length());
                    }
                    writer.append(FIRST_CIRCLE.charAt(newIndex));
                } else if (indexSecondCircle != -1) {
                    indexSecondCircle = SECOND_CIRCLE.indexOf(symbol) - key;
                    if (indexSecondCircle < 0) {
                        newIndex = SECOND_CIRCLE.length() - Math.abs(indexSecondCircle % SECOND_CIRCLE.length());
                    } else {
                        newIndex = Math.abs(indexSecondCircle % SECOND_CIRCLE.length());
                    }
                    writer.append(SECOND_CIRCLE.charAt(newIndex));
                } else {
                    writer.append(symbol);
                }
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    private char[] readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName))).toCharArray();
    }
}
