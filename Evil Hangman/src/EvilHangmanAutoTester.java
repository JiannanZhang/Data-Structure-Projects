import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


// class to test HangmanManager
public class EvilHangmanAutoTester {

    private static int testCases;
    private static int failedTestCases;
    private static final String DICTIONARY_FILE = "dictionary.txt";
    private static final String TEST_FILE_NAME = "evilTest.eht";
    private static boolean DEBUG = true;


    public static void main(String[] args) {
        testCases = 0;
        failedTestCases = 0;
        List<String> dictionary = Collections.unmodifiableList(getDictionary());
        HangmanManager studentHangmanManager = new HangmanManager(dictionary, true);

        try {

            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File(TEST_FILE_NAME)));
            int numTests = reader.readInt();
            System.out.println("Number of rounds of game to play: " + numTests);
            System.out.println();

            // loop through and perform each test
            for(int i = 0; i < numTests; i++) {
                runTest(studentHangmanManager, reader);
            }

            System.out.println("\ntotal test cases: " + testCases);
            System.out.println("number of test cases failed: " + failedTestCases);

            reader.close();
        }
        catch(IOException e) {
            System.out.println("\nError in trying to read and process automatic tests");
            System.out.println("for the Evil Hangman program.");
            System.out.println("File name: " + TEST_FILE_NAME);
            System.out.println("Error: " + e);
            System.out.println("Ending program.");
        }
        catch(ClassNotFoundException e) {
            System.out.println("\nError in trying to read and process automatic tests");
            System.out.println("Read in a class that could not be found.");
            System.out.println();
            System.out.println("for the Evil Hangman program.");
            System.out.println("File name: " + TEST_FILE_NAME);
            System.out.println("Error: " + e);
            System.out.println("Ending program.");
        }
    }


    private static void runTest(HangmanManager manager,
            ObjectInputStream reader) throws IOException, ClassNotFoundException {

        // read in header data for the test
        // TODO - change to a class to store all this data
        int roundNumber = reader.readInt();
        int wordLength = reader.readInt();
        int numGuessesAllowed = reader.readInt();
        String actualGuesses = (String) reader.readObject();
        int difficulty = reader.readInt();
        int initialNumberOfWords = reader.readInt();
        String initialPattern = (String) reader.readObject();

        if(DEBUG)
            showInitialData(roundNumber, wordLength, numGuessesAllowed,
                    actualGuesses, difficulty, initialNumberOfWords,
                    initialPattern);

        // check valid difficulty, resort to hard if not valid
        if(!validDifficulty(difficulty))
            difficulty = HangmanMain.HARD;

        // get ready for the round!
        manager.prepForRound(wordLength, numGuessesAllowed, difficulty);

        checkInitialConditions(manager, roundNumber, initialNumberOfWords, initialPattern);

        // run the actual guesses
        runRound(manager, reader, roundNumber, actualGuesses);
    }


    // check that initial number of words and pattern match
    private static void checkInitialConditions(HangmanManager manager,
            int testNumber, int initialNumberOfWords, String initialPattern) {

        System.out.println();
        testCases++;
        if(initialNumberOfWords == manager.numWordsCurrent()) {
            if(DEBUG)
                System.out.println("\nRound number " + testNumber +
                        " - passed test - initial number of words");
        }
        else {
            if(DEBUG) {
                System.out.println("\nRound number " + testNumber +
                        " - FAILED TEST - initial number of words");
                System.out.println("Expected: " + initialNumberOfWords);
                System.out.println("Actual: " + manager.numWordsCurrent());
            }
            failedTestCases++;
        }

        testCases++;
        if(initialPattern.trim().equals(manager.getPattern().trim())) {
            if(DEBUG)
                System.out.println("Round number " + testNumber +
                        " - passed test - initial pattern");
        }
        else {
            if(DEBUG) {
                System.out.println("Round number " + testNumber +
                        " - FAILED TEST - initial pattern");
                System.out.println("Expected: " + initialNumberOfWords);
                System.out.println("Actual: " + manager.numWordsCurrent());
            }
            failedTestCases++;
        }
    }


    private static void runRound(HangmanManager manager,
            ObjectInputStream reader,
            int roundNumber, String actualGuesses)
            throws IOException, ClassNotFoundException {

        // TODO THIS METHOD IS TOO LONG. NEEED TO BREAK IT UP

        // run a complete round / game
        // number of guesses = actualGuesses.length()
        // make a guess, compare expected map to actual map,

        for(int i = 0; i < actualGuesses.length(); i++) {
            char ch = actualGuesses.charAt(i);
            System.out.println("\nRound Number: " + roundNumber + ", guess number: " + (i + 1) + ", guessed char: " + ch);

            // read in expected reuslts
            Map<String, Integer> expectedMap
                = buildMapNoSpaces( (Map<String, Integer>) reader.readObject());
            expectedMap = buildMapNoSpaces(expectedMap);
            int expectedWordsLeft = reader.readInt();
            String expectedPattern = removeSpaces((String) reader.readObject());

            // make guess and get actual results;
            Map<String, Integer> actualMap = manager.makeGuess(ch);
            actualMap = buildMapNoSpaces(actualMap);
            int actualWordsLeft = manager.numWordsCurrent();
            String actualPattern = removeSpaces(manager.getPattern());

            if(DEBUG) {
                showResults(ch, expectedMap, actualMap,
                        expectedPattern, actualPattern, expectedWordsLeft, actualWordsLeft);
            }

            testCases++;
            // check map
            if(!expectedMap.equals(actualMap)) {
                failedTestCases++;
                System.out.println("FAILED TEST CASE - MAP OF WORDS PER PATTERN INCORRECT");
                System.out.println("EXPECTED: " + expectedMap);
                System.out.println("ACTUAL: " + actualMap);
            }

            // check new number of words
            testCases++;
            if(expectedWordsLeft != actualWordsLeft ) {
                failedTestCases++;
                System.out.println("FAILED TEST CASE - NEW NUMBER OF CURRENT WORDS AFTER GUESS INCORRECT");
                System.out.println("EXPECTED: " + expectedWordsLeft);
                System.out.println("ACTUAL: " + manager.numWordsCurrent() );
            }

            // check pattern
            testCases++;
            if(!expectedPattern.equals(actualPattern)) {
                failedTestCases++;
                System.out.println("FAILED TEST CASE - NEW PATTERN AFTER GUESS INCORRECT");
                System.out.println("EXPECTED: " + expectedPattern);
                System.out.println("ACTUAL: " + manager.getPattern());
            }
        }
    }


    private static void showResults(char guess, Map<String, Integer> expectedMap,
            Map<String, Integer> actualMap, String expectedPattern,
            String actualPattern, int expectedWordsLeft, int actualWordsLeft) {
        System.out.println("\nDEBUGGGING");
        System.out.println("Guessed char: " + guess);
        System.out.println("\nExpected patterns and frequencies: ");
        showMap(expectedMap);
        System.out.println("\nActual patterns and frequencies:");
        showMap(actualMap);
        System.out.println("\nExpected new pattern: " + expectedPattern);
        System.out.println("Actual new pattern: " + actualPattern);
        System.out.println("\nExpected new number of words: " + expectedWordsLeft);
        System.out.println("Actual new number of words: " + actualWordsLeft);

    }


    private static boolean validDifficulty(int difficulty) {
        return difficulty == HangmanMain.HARD ||
        difficulty == HangmanMain.MEDIUM ||
        difficulty == HangmanMain.EASY ;
    }


    // print condititions for a given test
    private static void showInitialData(int roundNumber, int wordLength,
            int numGuessesAllowed, String actualGuesses, int difficulty,
            int initialNumberOfWords, String initialPattern) {

        System.out.println("Conditions for round number " + roundNumber);
        System.out.println("word length: " + wordLength);
        System.out.println("number of guesses allowed: " + numGuessesAllowed);
        System.out.println("Note, number of guesses allowed is not used.");
        System.out.print("guesses in order made: ");
        for(int i = 0; i < actualGuesses.length(); i++)
            System.out.print(actualGuesses.charAt(i) + " ");
        System.out.println("\nExpected initial number of words: " + initialNumberOfWords);
        System.out.println("Expected initial pattern(should be all dashes): " + initialPattern);
        System.out.print("Difficulty: ");

        // should we change difficulty to an ENUM??
        if(difficulty == HangmanMain.HARD)
            System.out.println("HARD");
        else if (difficulty == HangmanMain.MEDIUM)
            System.out.println("MEDIUM");
        else if (difficulty == HangmanMain.EASY)
            System.out.println("EASY");
        else {
            System.out.println("UNKNOWN DIFFICULTY! - RESORTING TO HARD. TEST MAY FAIL.");
        }
    }

    private static Map<String, Integer> buildMapNoSpaces(
            Map<String, Integer> stu) {
        TreeMap<String, Integer> result = new TreeMap<String, Integer>();
        for(String pattern : stu.keySet()) {
            String newKey = removeSpaces(pattern);
            result.put(newKey, stu.get(pattern));
        }
        return result;
    }


    private static void showMap(Map<String, Integer> map) {
        for(String pattern : map.keySet())
            System.out.println(pattern + " " + map.get(pattern));
    }


    // open the dictionary file. Return a list containing
    // the words in the dicitonary file.
    // If the dictionary file is not found the program ends
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


    private static String removeSpaces(String s) {
        s = s.trim();
        String result = "";
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) != ' ')
                result += s.charAt(i);
        return result;
    }
}
