import java.io.File;

public class Main {
    private static final File inputFile = new File("day05/input.txt");
    public static void main(String[] args) {
        CraneGame game = new CraneGame(inputFile);
        game.run();
    }
}