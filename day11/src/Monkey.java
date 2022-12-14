import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monkey {
    private static List<Monkey> monkeys = new ArrayList<>();
    private List<Long> items;
    private final Operator operator;
    private final int operand;
    private final int divisor;
    private final int targetTrueId;
    private final int targetFalseId;
    private Long inspectedItems = (long) 0;
    private static int modulo = 1;
    private static final List<Long> counts = new ArrayList<>();

    public Monkey(List<Long> items, Operator operator, int operand, int divisor, int targetTrueId, int targetFalseId) {
        this.items = items;
        this.operator = operator;
        this.operand = operand;
        this.divisor = divisor;
        this.targetTrueId = targetTrueId;
        this.targetFalseId = targetFalseId;
        modulo *= divisor;
        monkeys.add(this);
    }

    private void turn(int worrydivisor) {
        for (Long item:items) {
            inspectedItems++;
            long newItem;
            long localOperand = (operand == 0) ? item : operand;
            switch (operator) {
                case PLUS -> newItem = ((item + localOperand) / worrydivisor) % modulo;
                case MULTIPLY -> newItem = ((item * localOperand) / worrydivisor) % modulo;
                default -> throw new RuntimeException("Ungültiger Operand");
            }
            boolean test = (newItem % divisor == 0);
            Monkey target = test ? monkeys.get(targetTrueId) : monkeys.get(targetFalseId);
            target.addItem(newItem);
        }
        items = new ArrayList<>();
    }

    private void addItem(long newItem) {
        items.add(newItem);
    }
    
    public static void part1() {
        for (int i=0; i<20; i++) {
            for (Monkey monkey : monkeys) {
                monkey.turn(3);
            }
        }
        
        for (Monkey monkey : monkeys) {
            counts.add(monkey.inspectedItems);
        }
        counts.sort(Collections.reverseOrder());
        System.out.println("Part 1: " + counts.get(0) * counts.get(1));
    }

    public static void part2() {
        for (int i=0; i<10000; i++) {
            for (Monkey monkey : monkeys) {
                monkey.turn(1);
            }
        }

        for (Monkey monkey : monkeys) {
            counts.add(monkey.inspectedItems);
        }
        counts.sort(Collections.reverseOrder());
        System.out.println("Part 2: " + counts.get(0)*counts.get(1));
    }
    static void reset() {
        monkeys = new ArrayList<>();
        modulo = 1;
        counts.clear();
    }

    enum Operator {
        PLUS,
        MULTIPLY,
    }
}
