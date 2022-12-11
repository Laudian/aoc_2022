import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static File inFile = new File("day08/input.txt");

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inScanner = new Scanner(inFile);
        List<List<Tree>> grid = new ArrayList<>();
        int y = 0;
        while (inScanner.hasNextLine()) {
            String line = inScanner.nextLine();
            ArrayList<Tree> row = new ArrayList<>();
            for (int x=0; x<line.length(); x++) {
                int height = Integer.parseInt(line.substring(x, x+1));
                row.add(new Tree(x, y, height, grid));
            }
            grid.add(row);
            y++;
        }
        int count = 0;
        int maxscore = 0;
        for (List<Tree> trees : grid) {
            for (Tree tree : trees) {
                count += tree.isVisible() ? 1 : 0;
                maxscore = Integer.max(tree.score(), maxscore);
            }
        }
        System.out.println("Part 1: " + count);
        System.out.println("Part 2: " + maxscore);

    }
    private static class Tree {
        int x;
        int y;
        int height;
        List<List<Tree>> grid;

        public Tree(int pX, int pY, int pHeight, List<List<Tree>> pGrid) {
            x = pX;
            y = pY;
            height = pHeight;
            grid = pGrid;
        }

        public boolean isVisible() {
            // checking north
            boolean obscured = false;
            for (int dY = 0; dY < y; dY++) {
                if (grid.get(dY).get(x).height >= height) {
                    obscured = true;
                    break;
                }
            }
            if (!obscured) {
                return true;
            }
            obscured = false;
            // checking south
            for (int dY = y + 1; dY < grid.size(); dY++) {
                if (grid.get(dY).get(x).height >= height) {
                    obscured = true;
                    break;
                }
            }
            if (!obscured) {
                return true;
            }
            obscured = false;
            // checking west
            for (int dX = 0; dX < x; dX++) {
                if (grid.get(y).get(dX).height >= height) {
                    obscured = true;
                    break;
                }
            }
            if (!obscured) {
                return true;
            }
            // checking east
            for (int dX = x + 1; dX < grid.size(); dX++) {
                if (grid.get(y).get(dX).height >= height) {
                    return false;
                }
            }
            return true;
        }

        public int score() {
            int north = 0;
            for (int dY = y; dY > 0; dY--) {
                int nextHeight = grid.get(dY - 1).get(x).height;
                north++;
                if (! (nextHeight < height)) {
                    break;
                }
            }
            int south = 0;
            for (int dY = y; dY < grid.size() - 1; dY++) {
                int nextHeight = grid.get(dY + 1).get(x).height;
                south++;
                if (! (nextHeight < height)) {
                    break;
                }
            }
            int west = 0;
            for (int dX = x; dX > 0; dX--) {
                int nextHeight =grid.get(y).get(dX - 1).height;
                west++;
                if (! (nextHeight < height)) {
                    break;
                }
            }
            int east = 0;
            for (int dX = x; dX < grid.size() - 1; dX++) {
                int nextHeight = grid.get(y).get(dX + 1).height;
                east++;
                if (! (nextHeight < height)) {
                    break;
                }
            }
            return north * south * west * east;
        }
    }
}