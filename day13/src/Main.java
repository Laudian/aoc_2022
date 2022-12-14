import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final File inFile = new File("day13/josua.txt");
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inScanner = new Scanner(inFile);
        List<String> packets = new ArrayList<>();
        int index = 0;
        int count = 0;
        while (inScanner.hasNextLine()) {
            index++;
            List<String> pair = readPair(inScanner);
            String left = pair.get(0);
            String right = pair.get(1);
            packets.add(left);
            packets.add(right);
            
            int inOrder = compare(left, right);
            if (inOrder < 0) {
                count += index;
            }
        }
        System.out.println("Part 1: " + count);
        packets.add("[[2]]");
        packets.add("[[6]]");
        packets.sort(Main::compare);
        int decoder1 = packets.indexOf("[[2]]") + 1;
        int decoder2 = packets.indexOf("[[6]]") + 1;
        System.out.println("Part 2: " + decoder2 * decoder1);
    }

    private static int compare(String left, String right) {
        //System.out.println(left + " " + right);
        if (left.length() == 0) {
            return -1;
        }
        if (right.length() == 0) {
            return 1;
        }
        if (left.startsWith(",")) {left = left.substring(1);}
        
        if (right.startsWith(",")) {right = right.substring(1);}
        char l = left.charAt(0);
        char r = right.charAt(0);
        
        if (l == ']' && r != ']') {
            return -1;
        }
        if (r == ']' && l != ']') {
            return 1;
        }
        // Both are lists
        if (l == '[' && r == '[') {
            return compare(left.substring(1), right.substring(1));
        }
        if (l == ']' && r == ']') {
            return compare(left.substring(1), right.substring(1));
        }
        // Both are Integers
        if (l != '[' && r != '[') {
            int leftIndex = findIntIndex(left);
            int rightIndex = findIntIndex(right);
            int leftInt = Integer.parseInt(left.substring(0,leftIndex));
            int rightInt = Integer.parseInt(right.substring(0, rightIndex));
            if (leftInt < rightInt) {
                return -1;
            }
            if (rightInt < leftInt) {
                return 1;
            } else {
                int leftRestIndex = (left.charAt(leftIndex) == ',') ? leftIndex+1 : leftIndex;
                int rightRestIndex = (right.charAt(rightIndex) == ',') ? rightIndex+1 : rightIndex;
                return compare(left.substring(leftRestIndex), right.substring(rightRestIndex));
            }
        }
        // One is an Integer and one is a List
        return compare(makeList(left), makeList(right));
    }

    private static int findIntIndex(String string) {
        for (int i=0; i<string.length(); i++) {
            if (string.charAt(i) == ',' || string.charAt(i) == ']') {
                return i;
            }
        }
        throw new RuntimeException("Integer has no end: " + string);
    }

    private static List<String> readPair(Scanner scanner) {
        List<String> pair = new ArrayList<>();
        pair.add(scanner.nextLine());
        pair.add(scanner.nextLine());
        if (scanner.hasNextLine()) {scanner.nextLine();}
        return pair;
    }
    
    private static String makeList(String string) {
        if (string.startsWith("[")) {
            return string;
        }
        int index = findIntIndex(string);
        return "[" + string.substring(0, index) + "]" + string.substring(index);
    }
}