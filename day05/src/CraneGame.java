import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CraneGame {
    private final List<Deque<Character>> stacks;
    private final List<Deque<Character>> stacks2;
    private final Scanner inScanner;
    private static final Pattern p = Pattern.compile("\\d+");
    public CraneGame(File inFile)
    {
        try {
            inScanner = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        // Find out, how many stacks there are. Every Stack adds 4 Characters
        String line = inScanner.nextLine();
        int stackCount = (line.length() - 3) / 4 + 1;
        
        // Create the stacks
        stacks = new ArrayList<>();
        stacks2 = new ArrayList<>();
        for (int i=0; i<stackCount; i++) {
            stacks.add(new ArrayDeque<>());
            stacks2.add(new ArrayDeque<>());
        }
        
        // Add the boxes in reverse order to the stacks
        do {for (int i=0; i<stackCount; i++) {
            char box = line.charAt(1+i*4);
            if (box != ' ') {
                stacks.get(i).add(box);
                stacks2.get(i).add(box);
            }
        }
            line = inScanner.nextLine();
        } while (line.charAt(1) != '1');
        
        // Skip empty line after stacks in input
        inScanner.nextLine();
    }
    
    /**
    Reads the next move from the Scanner and executes it.
     */
    private Boolean move() {
        if (! inScanner.hasNextLine()) {
            return false;
        }
        String line = inScanner.nextLine();
        int[] move = new int[3];
        Matcher m = p.matcher(line);
        for (int i=0; i<3; i++) {
            m.find();
            move[i] = Integer.parseInt(m.group());
        }
        Deque<Character> stack = new ArrayDeque<>(); // collect boxes for part 2
        for (int i=0; i<move[0]; i++) {
            Character box = stacks.get(move[1]-1).pop();
            Character box2 = stacks2.get(move[1]-1).pop();
            stacks.get(move[2]-1).push(box);
            stack.addFirst(box2);
        }
        while (stack.peek() != null) {
            stacks2.get(move[2]-1).push(stack.pop());
        }
        return true;
    }
    
    public void run() {
        while (move()) {//print();
            }
        
        StringBuilder string = new StringBuilder();

        for (Deque<Character> stack : stacks) {
            string.append(stack.peek());
        }
        System.out.println(string);

        string = new StringBuilder();

        for (Deque<Character> stack : stacks2) {
            string.append(stack.peek());
        }
        System.out.println(string);
    }
    
    public void print(List<Deque<Character>> target) {
        System.out.println();
        for (Deque<Character> stack : target) {
            StringBuilder string = new StringBuilder();
            for (Character box : stack) {
                string.append(box);
            }
            System.out.println(string);
        }
        System.out.println();
    }
}
