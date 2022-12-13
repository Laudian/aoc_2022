import Instructions.Addx;
import Instructions.Cpu;
import Instructions.Instruction;
import Instructions.Noop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static File inFile = new File("day10/input.txt");
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inScanner = new Scanner(inFile);
        Cpu cpu = new Cpu();
        
        while (inScanner.hasNextLine()) {
            String line = inScanner.nextLine();
            Instruction in = createInstruction(line);
            cpu.addInstruction(in);
        }
        cpu.run();
    }
    
    private static Instruction createInstruction(String line) {
        String[] nameValue = line.split(" ");
        String name = nameValue[0];
        switch (name) {
            case "addx" -> {
                String value = nameValue[1];
                return new Addx(value);
            }
            case "noop" -> {
                return new Noop();
            }
            default ->
                throw new RuntimeException("Instruction not found");
        }
    }
}