import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Solves day 01 of AdventOfCode 2022
 */
public class Main {
    public static void main(String[] args)
    {
        ArrayList<Integer> input = parseInput();
        input.sort(Collections.reverseOrder());
        System.out.println("Part 1: " + input.get(0));
        System.out.println("Part 1: " + input.stream().mapToInt(Integer::intValue).limit(3).sum());
    }

    /**
     * Parses the input file.
     * @return Returns a List of Integers. Each Integer is the sum of the calories an Elf carries.
     */
    private static ArrayList<Integer> parseInput()
    {
        ArrayList<Integer> result = new ArrayList<>();
        int temp = 0;
        try
        {
            File inputFile = new File("day01/input.txt");
            Scanner inputReader = new Scanner(inputFile);
            while (inputReader.hasNextLine())
            {
                String next = inputReader.nextLine();
                if (next.isBlank())
                {
                    result.add(temp);
                    temp = 0;
                }
                else
                {
                    temp += Integer.parseInt(next);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Input file not found.");
            throw new RuntimeException();
        }

        return result;
    }
}