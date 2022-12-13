package Instructions;

import java.util.ArrayList;
import java.util.List;

public class Cpu {
    private int registerX = 1;
    private int pc = 0;
    private int cycleCount = 0;
    private final List<Instruction> instructions = new ArrayList<>();
    public void addInstruction(Instruction in) {
        instructions.add(in);
    }
    private final List<List<Character>> screen = new ArrayList<>();
    public Cpu() {
        for (int y=0; y<6; y++) {
            List<Character> row = new ArrayList<>();
            for (int x=0; x<40; x++) {
                row.add('.');
            }
            screen.add(row);
        }
    }
    
    public void run() {
        int result = 0;
        while (pc < instructions.size()) {
            if (isInSync()) {
                int y = cycleCount / 40;
                int x = cycleCount % 40;
                List<Character> row = screen.get(y);
                row.remove(x);
                row.add(x, '#');
            }
            cycleCount++;
            if (cycleCount % 40 == 20) {
                result += registerX * cycleCount;
            }
            Instruction in = instructions.get(pc);
            boolean instructionCompleted = in.execute(this);
            if (instructionCompleted) {
                pc++;
            }
        }
        System.out.println("Part 1: " + result);
        print();
    }

    private void print() {
        System.out.println("Part 2:");
        for (List<Character> row : screen) {
            StringBuilder line = new StringBuilder();
            for (Character letter : row) {
                line.append(letter);
            }
            System.out.println(line);
        }
    }

    private boolean isInSync() {
        int x = cycleCount % 40;
        return (x >= registerX - 1 && x <= registerX + 1);
    }
    public int getRegisterX() {
        return registerX;
    }

    public void setRegisterX(int registerX) {
        this.registerX = registerX;
    }
}
