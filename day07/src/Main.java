import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Dir root = new Dir();
        File inputFile = new File("day07/input.txt");
        Scanner inputScanner = new Scanner(inputFile);
        
        while (inputScanner.hasNextLine()) {
            String line = inputScanner.nextLine();
            if (line.startsWith("$ cd ")) {
                String name = line.substring(5);
                root.changeDir(name);
            } else if (line.startsWith("dir ")) {
                String name = line.substring(4);
                root.addDir(name);
            } else if (line.startsWith("$ ls")) {
                continue;
            } else {
                String[] sizeName = line.split(" ");
                int size = Integer.parseInt(sizeName[0]);
                root.addFile(sizeName[1], size);
            }
        }
        root.part1();
        root.part2();
    }
}