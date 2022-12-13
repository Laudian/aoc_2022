package Instructions;

public class Addx extends Instruction {
    private int remainingCycles = 2;
    private final int value;

    public Addx(String pValue) {
        value = Integer.parseInt(pValue);
    }
    @Override
    public boolean execute(Cpu cpu) {
        if (remainingCycles > 1) {
            remainingCycles--;
            return false;
        }
        cpu.setRegisterX(cpu.getRegisterX() + value);
        return true;
    }
}