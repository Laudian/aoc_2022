import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final File inputFile = new File("day04/input.txt");
    public static void main(String[] args) {
        part1();
    }

    private static void part1() {
        int pairs = 0;
        int overlaps = 0;
        try {
            Scanner inputScanner = new Scanner(inputFile);
            while (inputScanner.hasNextLine()) {
                String next = inputScanner.nextLine();
                String[] strings = next.split(",");
                String[] first = strings[0].split("-");
                String[] second = strings[1].split("-");
                int b1, b2, e1, e2;
                b1 = Integer.parseInt(first[0]);
                e1 = Integer.parseInt(first[1]);
                b2 = Integer.parseInt(second[0]);
                e2 = Integer.parseInt(second[1]);

                if ((b1 <= b2 && e1 >= e2) || (b2 <= b1 && e2 >= e1)) {
                    pairs += 1;
                }
                if (! (b1 > e2 || b2 > e1)) {
                    overlaps += 1;
                }
            }
            System.out.println("Part 1: " + pairs);
            System.out.println("Part 2: " + overlaps);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}