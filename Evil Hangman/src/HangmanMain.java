import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Class HangmanMain is the driver program for the Hangman program.  It reads a
// dictionary of words to be used during the game and then plays a game with
// the user.

// This is a cheating version of hangman that delays picking a word
// to keep its options open.  You can change the setting for DEBUG to see
// how many options are still left on each turn and what patterns are
// being generated from the guess

// Based on a program by Stuart Reges, modified my Mike Scott

public class HangmanMain  {

    private static final String DICTIONARY_FILE = "dictionary.txt"; // change to smallDictionary.txt for testing
    private static final boolean DEBUG = true;  // if true, program shows statistics of game in progress
    private static final int MAX_GUESSES = 25;
    
    // should this be an Enum??
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    public static void main(String[] args) {
        System.out.println("Welcome to the CS314 hangman game.");
        System.out.println();

        // read in the dictionary and create the Hangman manager
        List<String> dictionary = Collections.unmodifiableList(getDictionary());
        HangmanManager hangman = new HangmanManager(dictionary, DEBUG);
        if(DEBUG)
            showWordCounts(hangman);

        Scanner keyboard = new Scanner(System.in);
        // play games until user wants to quit
        do {
            setGameParameters(hangman, keyboard);
            playGame(keyboard, hangman);
            showResults(hangman);
        } while(playAgain(keyboard));

    }


    // check if the user wants to play another game
    private static boolean playAgain(Scanner keyboard) {
        System.out.println();
        System.out.print("Another game? Enter y for another game, anything else to quit: ");
        String answer = keyboard.nextLine();
        return answer.length() > 0 && answer.toLowerCase().charAt(0) == 'y';
    }


    private static void setGameParameters(HangmanManager hangman,
            Scanner keyboard) {
        int wordLength = 0;
        // determine word length
        do{
            System.out.print("What length word do you want to use? ");
            wordLength = Integer.parseInt(keyboard.nextLine());
        } while(!atLeastOneWord(hangman, wordLength));

        // determine number of wrong guesses
        int numGuesses = 0;
        do{
            System.out.print("How many wrong answers allowed? ");
            numGuesses = Integer.parseInt(keyboard.nextLine());
        } while(!validChoice(numGuesses, 1, MAX_GUESSES, "number of wrong guesses"));

        // determine difficulty level
        int difficulty = 0;
        do {
            System.out.println("What difficulty level do you want?");
            System.out.print("Enter a number between " + EASY + "(EASIEST) " +
                    "and " + HARD + "(HARDEST) : ");
            difficulty = Integer.parseInt(keyboard.nextLine());
        } while(!validChoice(difficulty, EASY, HARD, "difficulty"));
        hangman.prepForRound(wordLength, numGuesses, difficulty);
    }


    private static boolean validChoice(int choice, int min, int max, String parameter) {
        boolean valid = min <= choice && choice <= max;
        if(!valid) {
            System.out.println(choice + " is not a valid number for " + parameter);
            System.out.println("Pick a number between " + min + " and " + max + ".");
        }
        return valid;
    }


    // check to ensure there is at least one word of the given length in the manager
    private static boolean atLeastOneWord(HangmanManager hangman, int wordLength) {
        int numWords = hangman.numWords(wordLength);
        if(numWords == 0) {
            System.out.println();
            System.out.println("I don't know any words with " + wordLength + " letters. Enter another number.");
        }
        return numWords != 0;
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


    // Plays one game with the user
    private static void playGame(Scanner keyboard, HangmanManager hangman) {
        // keep asking for guesses as long as user has guesses left and puzzle not solved
    	System.out.println( hangman.getPattern());
        while (hangman.getGuessesLeft() > 0 && hangman.getPattern().contains("-")) {
            System.out.println("guesses left: " + hangman.getGuessesLeft());

            // debugging
            if (DEBUG) {
                System.out.println("DEBUGGING: words left : " + hangman.numWordsCurrent());
            }
            System.out.println("guessed so far : " + hangman.getGuessesMade());
            System.out.println("current word : " + hangman.getPattern());
            char guess = getLetter(keyboard, hangman);
            Map<String, Integer> results = hangman.makeGuess(guess);
            if(DEBUG) {
                showPatterns(results);
            }

            int count = getCount(hangman.getPattern(), guess);
            if (count == 0) {
                System.out.println("Sorry, there are no " + guess + "'s");
            } else if (count == 1) {
                System.out.println("Yes, there is one " + guess);
            } else {
                System.out.println("Yes, there are " + count + " " + guess + "'s");
            }
            System.out.println();
        }
    }

    // pre, keyboard != null, hangman != null
    private static char getLetter(Scanner keyboard, HangmanManager manager) {
        if(keyboard == null || manager == null)
            throw new IllegalArgumentException("Parameters to method may not be null.");
        boolean alreadyGuessed = true;
        char guess = ' ';
        while(alreadyGuessed) {
            System.out.print("Your guess? ");
            String result = keyboard.nextLine().toLowerCase();
            while(result == null || result.length() == 0 || !isEnglishLetter(result.charAt(0))) {
                System.out.println("That is not an English letter.");
                System.out.print("Your guess? ");
                result = keyboard.nextLine().toLowerCase();
            }
            guess = result.charAt(0);
            alreadyGuessed = manager.alreadyGuessed(guess);
            if(manager.alreadyGuessed(guess))
                System.out.println("You already guessed that! Pick a new letter please.");
        }
        System.out.println("the guess: " + guess + ".");
        assert isEnglishLetter(guess) && !manager.alreadyGuessed(guess) 
            : "something wrong with my logic in getting guess. " + guess;
        return guess;
    }

    // return true if ch is an English letter, A-Z or a-z
    private static boolean isEnglishLetter(char ch) {
        return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z');
    }


    // debugging method to show current patterns and number of words for each 
    // pre: results != null
    private static void showPatterns(Map<String, Integer> results) {
        if(results == null)
            throw new IllegalArgumentException("The map may not be null.");
        System.out.println();
        System.out.println("DEBUGGING: Based on guess here are resulting patterns and number");
        System.out.println("of words in each pattern: ");
        for(String key : results.keySet()) {
            System.out.println("pattern: " + key + ", number of words: " + results.get(key));
        }
        System.out.println("END DEBUGGING");
        System.out.println();
    }


    // pre: pattern != null
    // return the number pf times the guess occurs in the pattern
    private static int getCount(String pattern, char guess) {
        if(pattern == null )
            throw new IllegalArgumentException("Violation of precondition in getCount.");

        int result = 0;
        for(int i = 0; i < pattern.length(); i++)
            if(pattern.charAt(i) == guess)
                result++;
        return result;
    }


    // reports the results of the game, including showing the answer
    public static void showResults(HangmanManager hangman) {
        // if the game is over, get the secret word
        String answer = hangman.getSecretWord();
        System.out.println("answer = " + answer);
        if (hangman.getGuessesLeft() > 0) {
            System.out.println("You beat me");
        } else {
            System.out.println("Sorry, you lose");
        }
    }


    // helper method for debugging. Display number of words of length
    // dictionary.txt has words from length 2 to 25
    private static void showWordCounts(HangmanManager hangman) {
        // why 25? Should this vary with the dictionary??
        final int MAX_LETTERS_PER_WORD = 25;
        for(int i = 2; i < MAX_LETTERS_PER_WORD; i++)
            System.out.println(i + " " + hangman.numWords(i));
    }
}
