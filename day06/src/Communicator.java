import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Communicator {
    private final Reader inputBuffer;
    private int pos = 0;
    private final List<Character> letters = new ArrayList<>();
    public static void main(String[] args) {
        Communicator device = new Communicator();
        device.sync(4);
        System.out.println("Part 1: " + device.pos);
        device.sync(14);
        System.out.println("Part 2: " + device.pos);
    }
    
    public Communicator(){
        InputStream inputStream;
        try {
            File inputFile = new File("day06/input.txt");
            inputStream = new FileInputStream(inputFile);
            Reader inputReader = new InputStreamReader(inputStream, Charset.defaultCharset());
            inputBuffer = new BufferedReader(inputReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void sync(int length){
        char letter;
        while (true) {
            try {
                letter = (char) inputBuffer.read();
                pos++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            letters.add(letter);
            
            if (letters.size() == length+1) {
                letters.remove(0);
                Set<Character> letterSet = new HashSet<>(letters);
                if (letterSet.size() == length) {
                    return;
                }
            }
        }
    }
}