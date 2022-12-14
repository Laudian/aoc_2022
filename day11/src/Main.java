import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final File inFile = new File("day11/testinput.txt");
    public static void main(String[] args) throws FileNotFoundException {
        readInput();
        Monkey.part1();
        Monkey.reset();
        readInput();
        Monkey.part2();
    }
    
    private static void readInput() throws FileNotFoundException {
        Scanner inScanner = new Scanner(inFile);
        while (inScanner.hasNextLine()) {
            String line;
            // skip Monkey ID
            inScanner.nextLine();
            // find items
            line = inScanner.nextLine();
            String[] itemString = line.split(": ")[1].split(", ");
            List<Long> items = new ArrayList<>(Arrays.stream(itemString).map(Long::valueOf).toList());
            // find operator and operand
            line = inScanner.nextLine();
            Monkey.Operator operator = line.contains("+") ? Monkey.Operator.PLUS : Monkey.Operator.MULTIPLY;
            String[] split = line.split(" ");
            String last = split[split.length-1];
            int operand = (last.equals("old")) ? 0 : Integer.parseInt(last);
            // find divisor
            line = inScanner.nextLine();
            split = line.split(" ");
            int divisor = Integer.parseInt(split[split.length-1]);
            // find trueTarget
            line = inScanner.nextLine();
            split = line.split(" ");
            int trueTarget = Integer.parseInt(split[split.length-1]);
            // find falseTarget
            line = inScanner.nextLine();
            split = line.split(" ");
            int falseTarget = Integer.parseInt(split[split.length-1]);
            // Skip empty line
            inScanner.nextLine();

            // create monkey
            new Monkey(items, operator, operand, divisor, trueTarget, falseTarget);
        }
    }
}