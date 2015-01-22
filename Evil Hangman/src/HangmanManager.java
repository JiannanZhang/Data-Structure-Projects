/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:
 *  email address:
 *  UTEID:
 *  Section 5 digit ID: 
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

// add imports as necessary

import java.io.CharConversionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class HangmanManager{

    // instance vars
	private static Character CHAR = '-';
	private ArrayList<String> currentWordFamily;
	private int diff;
	private int numGuessLeft;
	private String pattern;
	private int numOfPlayerTurn;
	ArrayList<Character> alreadyGuessed;
	Map<Integer,ArrayList<String>> dictionary;
	TreeMap<String,Integer> patternDic;
    
    // pre: words != null, words.size() > 0
    // if debugOn = true, debuggin output is added
    public HangmanManager(List<String> words, boolean debugOn) {
    	this.dictionary = new TreeMap<Integer,ArrayList<String>>();
    	if (words == null)
    		throw new IllegalArgumentException("words cannot be null");
    	for (String word : words){
    		int len = word.length();
    		if (!dictionary.containsKey(len))
    			dictionary.put(len,new ArrayList<String>());
    		dictionary.get(len).add(word);
    	}
    }
    // pre: words != null, words.size() > 0
    // debuggin output is not added
    public HangmanManager(List<String> words) {
    	this(words,false);

    }
    
    // pre: none
    // post: return the number of words in the original Dictionary with the given length
    public int numWords(int length) {
    	if(dictionary.containsKey(length))
    		return dictionary.get(length).size();
    	return 0;
    }

//
    // pre: numWords(wordLen) > 0, numGuesses >= 1, diff = HangmanMain.EASY, HangmanMain.MEDIUM, or HangmanMain.HARD
    public void prepForRound(int wordLen, int numGuesses, int diff) {
    	if (numWords(wordLen) <= 0 || numGuesses < 1 || !(diff == 1 || diff == 2 || diff == 3))
    		throw new IllegalStateException("precondition is not met : numWords(wordLen) > 0 or numGuesses >= 1 "
    				+ "or diff = HangmanMain.EASY, HangmanMain.MEDIUM, or HangmanMain.HARD");
    	this.currentWordFamily = dictionary.get(wordLen);
    	this.diff = diff;
    	this.numGuessLeft = numGuesses;
    	this.alreadyGuessed = new ArrayList<Character>();
    	pattern = new String();
    	for (int i = 0; i < wordLen; i++) {
    		this.pattern += CHAR;
		} 
    		
    }
    
    
    // pre: none
    // return the number of words that are still posibilities
    public int numWordsCurrent() {
        return currentWordFamily.size();
    }
    
    
    // pre: none
    // return number of wrong guesses left in this game
    public int getGuessesLeft() {
        return numGuessLeft;
    }
    
    
    // pre: none
    // post: return a String version of the guesses made so far
    public String getGuessesMade() {
    	Collections.sort(alreadyGuessed);
    	return alreadyGuessed.toString();
    }
    
    
    // pre: none
    // post: return true if guess has already been used, false otherwise
    public boolean alreadyGuessed(char guess) {
    	if (alreadyGuessed.contains(guess))
    		return true;
        return false;
    }
    
    
    // get the current pattern. (In other words the revealed characters and '-'s
    // for characters not yet revealed.
    public String getPattern() {
        return pattern;
    }
    
    
    // pre: !alreadyGuessed(ch)
    // post: return a tree map with the resulting patterns and the number of
    // words in each of the new patterns.
    // the return value is for testing and debugging purposes
    public TreeMap<String, Integer> makeGuess(char guess) {
    	if (alreadyGuessed(guess))
    		throw new IllegalArgumentException("pre is not met: " + guess + "is already used");
    	TreeMap<String,ArrayList<String>> tempPatternDic = new TreeMap<String,ArrayList<String>>();
    	for (String word : currentWordFamily) {
    		String tempPattern = getPatternOfWord(word,guess);
    		if (!tempPatternDic.containsKey(tempPattern)){
    			tempPatternDic.put(tempPattern, new ArrayList<String>());
    			tempPatternDic.get(tempPattern).add(word);
    		}
    		else
    			tempPatternDic.get(tempPattern).add(word);
    	}
    	// use helper method to transfer this map to desired map - map<String,Integer>
    	patternDic = getPatternDic(tempPatternDic);
    	// now update instance vars
    	alreadyGuessed.add(guess);
    	Collections.sort(alreadyGuessed);
    	
    	// determine the pattern by diff
    	pattern = getPatternByDiff(patternDic, guess);
    	
    	//update the currentWordFamily
    	currentWordFamily = tempPatternDic.get(pattern);
    	//determine the numGeussleft
    	if (pattern.indexOf(guess) < 0)
    		numGuessLeft--;
    	
        return patternDic;
    }
    
    // this is used to transfer map from Map<String,ArrayList<String>> to  TreeMap<String, Integer>
    private TreeMap<String, Integer> getPatternDic(Map<String,ArrayList<String>> tempPatternDic){
    	TreeMap<String, Integer> result = new TreeMap<String, Integer>();
    	Set<String> keySet = new TreeSet<String>();
    	keySet = tempPatternDic.keySet();
    	for (String key : keySet){
    		result.put(key, tempPatternDic.get(key).size());
    	}
    	return result;
    }
    
    
   // helper method to 
    private String getPatternOfWord(String word, char guess) {
    	if (word.length() != pattern.length())
    		throw new IllegalArgumentException("Pre is not met");
    	StringBuilder tempPattern = new StringBuilder();
    	for (int i = 0; i < word.length(); i++) {
    		if (word.charAt(i) == guess)
    			tempPattern.append(guess);
    		else
    			tempPattern.append (CHAR);
			
		}
    	return tempPattern.toString();
    }
    
    private String getPatternByDiff(TreeMap<String,Integer> patternDic, int diff) {
    	if (patternDic == null)
    		throw new IllegalArgumentException("pattern should not be null");
    	if (diff == 1) {
    		if (numOfPlayerTurn % 2 == 0)
    			return getSecondHardestpattern(patternDic);
    		else
    			return getHardestpattern(patternDic);
    	}
    	
    	else if (diff == 2){
    		if (numOfPlayerTurn % 4 == 0)
    			return getSecondHardestpattern(patternDic);
    		else
    			return getHardestpattern(patternDic);
    	}
    	
    	else {
    		return getHardestpattern(patternDic);
    	}
    }
    	
    
    // my former version
//    private String getHardestpattern(TreeMap<String,Integer> patternDic) {
////    	ArrayList<String> keySet = new ArrayList<String>();
////    	keySet = patternDic.keySet();
//    	ArrayList<String> patternList = new ArrayList<String>();
//    	String pattern = new String();
//    	int maxInteger = 0;
//    	for (String pat : patternDic.keySet()) {
//			if (patternDic.get(pat) > maxInteger){
//				maxInteger = patternDic.get(pat);
//				pattern = pat;
//			}
//		}
//    	
//    	for (String pat : patternDic.keySet()) {
//    		if (patternDic.get(pat) == maxInteger)
//    			patternList.add(pat);
//		}
//    	
//    	if (patternList.size() == 1)
//    		return patternList.get(0);
//    	
//    	// get the AL of st by the min revealed letters in a patternDic
//    	else{
//    		ArrayList<String> PatternListByMinletters = getPatternByMinRevealedLetters(patternList);
//    		if (PatternListByMinletters.size() == 1)
//    			return PatternListByMinletters.get(0);
//    		else{
//    			Collections.sort(PatternListByMinletters);
//    			if (PatternListByMinletters.size() == 1)
//    				return PatternListByMinletters.get(0);
//    			else{
//    				Collections.sort(PatternListByMinletters,new stcomparator());
//    				return PatternListByMinletters.get(0);
//    			}
//    				
//    		}
//    	}
//    }
    
    
    // new version 
    
    private String getHardestpattern(TreeMap<String,Integer> patternDic) {
    	ArrayList<Map.Entry<String,Integer>> patternDicList = new ArrayList<Map.Entry<String,Integer>>(patternDic.entrySet());
    	Collections.sort(patternDicList, new EntryComparator());
    	int index = patternDicList.size() - 1;
    	return patternDicList.get(index).getKey();
    }
    
    private String getSecondHardestpattern(TreeMap<String,Integer> patternDic) {
    	ArrayList<Map.Entry<String,Integer>> patternDicList = new ArrayList<Map.Entry<String,Integer>>(patternDic.entrySet());
    	Collections.sort(patternDicList, new EntryComparator());
    	int index = patternDicList.size() - 2;
    	return patternDicList.get(index).getKey();
    }
    
    
    
    
    // comopare to method 
    
//    private static class stcomparator implements Comparator <String>  {
//    	public int compare(String st1, String st2) {
//		// TODO Auto-generated method stub
//    		return st1.compareTo(st2); //the order????
//    	}
//    }
//    
    // this method returns AL of St by the min revealed letters in a patternDic
//    private ArrayList<String> getPatternByMinRevealedLetters(ArrayList<String> pattern) {
//    	ArrayList<String> patternListByMinRevealedLetters = new ArrayList<String>();
//    	int minNumOfLetters = numOfLetterRevealed(pattern.get(0));
//    	int size = pattern.size();
//    	for (int i = 1; i < size; i++) {
//    		if (numOfLetterRevealed(pattern.get(i)) < minNumOfLetters)
//			minNumOfLetters = numOfLetterRevealed(pattern.get(i));
//		}
//    	
//    	for (int i = 0; i < size; i++) {
//			if (numOfLetterRevealed(pattern.get(i)) == minNumOfLetters)
//				patternListByMinRevealedLetters.add(pattern.get(i));
//		}
//    	return patternListByMinRevealedLetters;
//    }
//    
//    // this method is for how many letters revealed for a string "pattern"
//    private int numOfLetterRevealed(String pattern) {
//    	int size = pattern.length();
//    	int count = 0;
//    	for (int i = 0; i < size; i++) {
//			if (pattern.charAt(i) != CHAR)
//				count++;
//		}
//    	return count;
//    }
//    
//    
//    private String getSecondHardestpattern(TreeMap<String,Integer> patternDic) {
////    	ArrayList<String> keySet = new ArrayList<String>();
////    	keySet = patternDic.keySet();
//    	String pattern = new String();
//    	String hardestPattern = new String();
//    	int maxInteger;
//    	int secondMaxInteger;
//    	TreeSet<String> keySet = (TreeSet<String>) patternDic.keySet();
//    	maxInteger = patternDic.get(keySet.last());//??// how to access the keySet since it is a set
//    	secondMaxInteger = patternDic.get(keySet.first());
//    	
//    	// if no hardest one, all the values are same
//    	if (maxInteger == secondMaxInteger)
//    		// the assignment asked no order? 
//    		return keySet.first();
//    	else
//    		//delete the hardest pattern
//    		hardestPattern = getHardestpattern(patternDic);
//    		patternDic.remove(hardestPattern);
//    		pattern = getHardestpattern(patternDic);
//	    	if (maxInteger < secondMaxInteger){
//	    		maxInteger = secondMaxInteger;
//	    		secondMaxInteger = maxInteger;
//	    	}	
//	    	for (String pat : patternDic.keySet()) {
//				if (patternDic.get(pat) > maxInteger){
//					maxInteger = patternDic.get(pat);
//				if (patternDic.get(pat) > secondMaxInteger)
//					secondMaxInteger = patternDic.get(pat);
//					pattern = pat;
//				}
//			}
//	    	return pattern;
//    }
//    
//    
//    // another great way to do the make guess method! use comparator!!!!!!!
//    
//    
//    //hardest method, always gives the hardest word list.
//    public void hardest(TreeMap<String, Integer> result, TreeMap<String, ArrayList<String>> families) {
//
//    	//put the entry set of the result TreeMap<String, Integer> into a ArrayList
//    	ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(result.entrySet()); 
//    	Collections.sort(list, new MapComparator());
//    	String newPattern = "";
//    	newPattern = list.get(list.size() - 1).getKey(); // find the hardest in the list
//    	activeList = families.get(newPattern); //update the active list
//
//    	if(newPattern.equals(pattern)){
//    		occurredGuesses++; //update number of the wrong guesses if the pattern doesn't change
//    	} else{
//    		pattern = newPattern; //update the pattern
//    	}
//
//    }

    




//nested class of custom comparater class for sorting method.
    private static class EntryComparator implements Comparator<Map.Entry<String, Integer>> {
    	public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {

			if (entry1.getValue() == entry2.getValue()) {
    			int counter1 = 0; // counter for '-'
    			int counter2 = 0;
    			for (int i = 0; i < entry1.getKey().length(); i++) {
					if (entry1.getKey().charAt(i) == CHAR)
						counter1++;
					if (entry2.getKey().charAt(i) == CHAR)
						counter2++;
				}
			
    			// what if the list of the Map.Entry has more than one strings that have same revealed letters
    			if (counter1 == counter2) {
    				return (-1) * entry1.getKey().compareTo(entry2.getKey());
    			}
    			return counter1 - counter2;
			}
    		
    		return entry1.getValue() - entry2.getValue();
    	}
    }
    
    
    
    
    
    
    
    //jonney's version
    
//    private static class MapComparator implements Comparator<Map.Entry<String,Integer>>{
//
//    	public int compare(Entry<String, Integer> map1,	Entry<String, Integer> map2) {
//
//    		//check if the value are equal (number of members of that pattern)
//    		//if they have equal amount of numbers, then compare number of '-'s
//    		if(map1.getValue() == map2.getValue()){
//    			int count1 = 0;
//    			int count2 = 0;
//    			for(int i = 0; i < map1.getKey().length(); i++){
//    				if(map1.getKey().charAt(i) == '-')
//    					count1++;
//    				if(map2.getKey().charAt(i) == '-')
//    					count2++;
//    			}
//    			//if amount of '-'s still equal, compare two strings lexigraphically 
//    			if(count1 == count2){
//    				return (-1) * map1.getKey().compareTo(map2.getKey()); // -1 means reverse the order
//    			} else {
//    				return count1 - count2;
//    			}
//    		}
//    		return map1.getValue() - map2.getValue();
//    	}  
//

    

    
    // pre: numWordsCurrent() > 0
    // return the secret word the manager picked 
    // if there is more than one word left, pick one at random
    public String getSecretWord() {
    	if (currentWordFamily.size() == 0)
    		throw new IllegalArgumentException("pre is not met: the number of active list of words should be greater than 0");
    	
    	
       return currentWordFamily.get(new Random().nextInt(currentWordFamily.size()));//???new random(???)
    }
}
