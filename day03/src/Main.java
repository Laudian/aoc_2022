import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final File inputFile = new File("day03/input.txt");

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        int score = 0;
        try {
            Scanner inputScanner = new Scanner(inputFile);
            while (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                String firstHalf = line.substring(0, line.length() / 2);
                String secondHalf = line.substring(line.length() / 2);
                ArrayList<String> halves = new ArrayList<>(Arrays.asList(firstHalf, secondHalf));

                score += priority(findCommonChar(halves));
            }
            System.out.println("Part 1: " + score);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void part2() {
        int score = 0;
        ArrayList<String> group = new ArrayList<>();
        try {
            Scanner inputScanner = new Scanner(inputFile);
            while (inputScanner.hasNextLine()) {
                group.add(inputScanner.nextLine());
                if (group.size() == 3) {
                    score += priority(findCommonChar(group));
                    group.clear();
                }
            }
            System.out.println("Part 2: " + score);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int priority(char letter) {
        return letters.indexOf(letter) + 1;
    }

    private static char findCommonChar(ArrayList<String> strings) {
        String common = strings.get(0);
        for (String string : strings.subList(1, strings.size())) {
            String newcommon = "";
            for (char letter : common.toCharArray()) {
                if (string.indexOf(letter) != -1) {
                    newcommon += letter;
                }
            }
            common = newcommon;
        }
        return common.charAt(0);
    }
}