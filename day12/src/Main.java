import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final File inFile = new File("day12/input.txt");
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inScanner = new Scanner(inFile);
        Graph graph = new Graph();
        int y = 0;
        int start_x = 0;
        int start_y = 0;
        int end_x = 0;
        int end_y = 0;
        while (inScanner.hasNextLine()) {
            String row = inScanner.nextLine();
            for (int x=0; x<row.length(); x++) {
                char letter = row.charAt(x);
                if (letter == 'S') {
                    start_x = x;
                    start_y = y;
                    letter = 'a';
                }
                if (letter == 'E') {
                    end_x = x;
                    end_y = y;
                    letter = 'z';
                }
                int height = letter - 'a' + 1;
                graph.addVertex(x, y, height);
            }
            y++;
        }
        graph.setStart(graph.getVertexByCoord(start_x,start_y));
        graph.setExit(graph.getVertexByCoord(end_x, end_y));
        System.out.println("Part 1: " + graph.part1());
        System.out.println("Part 2: " + graph.part2());
    }
}