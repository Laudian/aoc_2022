import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static final File inFile = new File("day09/testinput2.txt");
    static List<Point> knots = new ArrayList<>();
    static List<Map<Point, Boolean>> boards = new ArrayList<>();
    static {
        for (int i=0; i<10; i++) {
            knots.add(new Point(0,0));
            Map<Point, Boolean> board = new HashMap<>();
            board.put(new Point(0,0), true);
            boards.add(board);
        }
    }
    private static final Map<Character, Point> dirs = Map.of(
            'U', new Point(0, -1),
            'D', new Point(0, 1),
            'L', new Point(-1, 0),
            'R', new Point(1, 0),
            '0', new Point(0,0)
    );
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inScanner = new Scanner(inFile);
        while (inScanner.hasNextLine()) {
            String line = inScanner.nextLine();
            String[] split = line.split(" ");
            char dir = split[0].charAt(0);
            int moves = Integer.parseInt(split[1]);
            for (int i=0; i<9; i++) {
                move(dirs.get(dir), moves, knots.get(i), knots.get(i+1), boards.get(i+1));
                moves = 1;
                dir ='0';
            }
        }
        
        System.out.println("Part 1: " + boards.get(1).size());
        System.out.println("Part 2: " + boards.get(9).size());
    }
    
    private static void move(Point dir, int moves, Point head, Point tail, Map<Point, Boolean> board){
        for (int i=0; i<moves; i++) {
            head.setLocation(head.x+dir.x, head.y+dir.y);
            if (! isConnected(head, tail)) {
                int dx = head.x - tail.x;
                int dy = head.y - tail.y;
                int adx = Math.abs(dx);
                int ady = Math.abs(dy);
                int moveX = 0;
                int moveY = 0;
                
                if (dy != 0) {
                    moveY = dy / ady;
                }
                if (dx != 0) {
                    moveX = dx / adx;
                }
                
                tail.setLocation(tail.x+moveX, tail.y+moveY);
                board.put(tail, true);
            }
        }
    }
    private static boolean isConnected(Point head, Point tail) {
        return Math.abs(head.x-tail.x) <= 1 && Math.abs(head.y-tail.y) <= 1;
    }
}