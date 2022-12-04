import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    private final static File inputFile = new File("day02/input.txt");
    public static void main(String[] args)
    {
        run();
    }

    private static void run()
    {
        int score = 0;
        int score2 = 0;
        try
        {
            Scanner inputScanner = new Scanner(inputFile);
            while (inputScanner.hasNextLine())
            {
                String nextLine = inputScanner.nextLine();
                char opponentPick = nextLine.charAt(0);
                char myPick = nextLine.charAt(2);
                score += score(myPick, opponentPick);

                myPick = findMyPick(opponentPick, myPick);
                score2 += score(myPick, opponentPick);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("Part 1: " + score);
        System.out.println("Part 2: " + score2);
    }
    private static int score(char myPick, char opponentPick)
    {
        myPick -= (16 + 23);
        opponentPick -= 16;

        int score = Character.getNumericValue(myPick);
        int result = Math.floorMod((myPick-opponentPick), 3);

        if (result == 0) {score += 3;}
        else if (result == 1) { score += 6;}
        else if (result == 2) {}
        else {System.out.println("Ungültiges Ergebnis: " + result);}
        return score;
    }

    private static char findMyPick(char opponentPick, char result)
    {
        int opPick, offset;
        opPick = opponentPick - 65;
        offset = result - 89;
        return (char) (Math.floorMod(opPick + offset, 3) + 88);
    }
}