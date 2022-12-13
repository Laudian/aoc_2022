package Instructions;

public class Noop extends Instruction {
    @Override
    public boolean execute(Cpu cpu) {
        return true;
    }
}
