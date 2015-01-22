import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// use this class to generate new tests
// add testN methods
public class GenerateTests {

    private static final String DICTIONARY_FILE = "dictionary.txt";
    private static final int NUM_TESTS = 7;
    private static final String OUTPUT_FILE = "moreEvilTests.dat";

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            ObjectOutputStream os
                = new ObjectOutputStream(new FileOutputStream(new File(OUTPUT_FILE)));
            HangmanManager hm
            = new HangmanManager(Collections.unmodifiableList(getDictionary()), false);
            os.writeInt(NUM_TESTS); // number of tests
            test1(hm, os);
            test2(hm, os);
            test3(hm, os);
            test4(hm, os);
            test5(hm, os);
            test6(hm, os);
            test7(hm, os);
            os.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private static void test1(HangmanManager hm, ObjectOutputStream os)
    throws IOException {
        int wordLen = 5;
        int numGuesses = 15;
        int diff = HangmanMain.HARD;
        String guesses = "eaiourstyhnb";
        runTest(1, hm, os, wordLen, numGuesses, diff, guesses);
    }

    private static void test2(HangmanManager hm, ObjectOutputStream os)
    throws IOException {
        int wordLen = 10;
        int numGuesses = 12;
        int diff = HangmanMain.HARD;
        String guesses = "xzqukjwyoa";
        runTest(2, hm, os, wordLen, numGuesses, diff, guesses);
    }

    private static void test3(HangmanManager hm, ObjectOutputStream os)
    throws IOException {
        int wordLen = 2;
        int numGuesses = 15;
        int diff = HangmanMain.HARD;
        String guesses = "mnsrtwhlbpfd";
        runTest(3, hm, os, wordLen, numGuesses, diff, guesses);
    }

    private static void test4(HangmanManager hm, ObjectOutputStream os)
    throws IOException {
        int wordLen = 5;
        int numGuesses = 15;
        int diff = HangmanMain.MEDIUM;
        String guesses = "eaiourstyhnb";
        runTest(4, hm, os, wordLen, numGuesses, diff, guesses);
    }

    private static void test5(HangmanManager hm, ObjectOutputStream os)
    throws IOException {
        int wordLen = 5;
        int numGuesses = 15;
        int diff = HangmanMain.EASY;
        String guesses = "eaiourstyhnx";
        runTest(5, hm, os, wordLen, numGuesses, diff, guesses);
    }

    private static void test6(HangmanManager hm, ObjectOutputStream os)
    throws IOException {
        int wordLen = 7;
        int numGuesses = 20;
        int diff = HangmanMain.HARD;
        String guesses = "uoyiaexzwrmnpclkh";
        runTest(6, hm, os, wordLen, numGuesses, diff, guesses);
    }

    private static void test7(HangmanManager hm, ObjectOutputStream os)
    throws IOException {
        int wordLen = 7;
        int numGuesses = 25;
        int diff = HangmanMain.HARD;
        String guesses = "uoyiaexzwrmnpclkhbdfg";
        runTest(7, hm, os, wordLen, numGuesses, diff, guesses);
    }

    private static void runTest(int testNumber, HangmanManager hm,
            ObjectOutputStream os,
            int wordLen, int numGuesses,
            int diff, String guesses)
    throws IOException {

        // file format for one test
        // header
        // testNumber int
        // wordLength int
        // numGuesses int
        // guesses String
        // difficulty int
        // current words int
        // start pattern String

        // Then for each guess
        // map of pattern - frequency Map<String, Integer>
        // current words int
        // pattern String

        // initial data
        os.writeInt(testNumber);
        os.writeInt(wordLen);
        os.writeInt(numGuesses);
        os.writeObject(guesses);
        os.writeInt(diff);

        // get ready to make guesses
        hm.prepForRound(wordLen, numGuesses, diff);
        os.writeInt(hm.numWordsCurrent());
        os.writeObject(hm.getPattern());

        // make guesses and write results
        for(int i = 0; i < guesses.length(); i++) {
            char guess = guesses.charAt(i);
            Map<String, Integer> result = hm.makeGuess(guess);
//            for(String pattern : result.keySet())
//                System.out.println(pattern + " " + result.get(pattern));

            // check no ties
            if(tieForMax(result)) {
                System.out.println("TIE IN TEST CASE!!!!!!");
                System.out.println("test num: " + testNumber);
                System.out.println("word length: " + wordLen);
                System.out.println("guesses: " + guesses);
                System.out.println("guess: " + guess);
                System.out.println("guess number: " + (i + 1));
            }

            os.writeObject(result);
            os.writeInt(hm.numWordsCurrent());
            os.writeObject(hm.getPattern());
        }
    }

    private static boolean tieForMax(Map<String, Integer> result) {
        Collection<Integer> counts = result.values();
        Integer max = Collections.max(counts);
        return Collections.frequency(counts, max) > 1;
    }


    // open the dictionary file. Return a list containing
    // the words in the dicitonary file.
    // If the dictionary file is not found the proram ends
    private static List<String> getDictionary() {
        List<String> dictionary = new ArrayList<String>();
        try {
            Scanner input = new Scanner(new File(DICTIONARY_FILE));

            while (input.hasNext())
                dictionary.add(input.next().toLowerCase());
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: " + e);
            System.out.println("Exiting");
            System.exit(-1);
        }
        return dictionary;
    }

}
