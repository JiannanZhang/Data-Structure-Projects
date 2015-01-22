import java.util.Arrays;
import java.util.List;
import java.util.Random;

//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 * replace <NAME> with your name.
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:Jiannan Zhang
 *  email address:
 *  UTEID: jz7674
 *  Section 5 digit ID: 
 *  Grader name:
 *  Number of slip days used on this assignment:
 */


public class CodeCamp {
  
    /**
     * Determine the Hamming distance between two arrays of ints. 
     * Neither the parameter <tt>aList</tt> or
     * <tt>bList</tt> are altered as a result of this method.<br>
     * @param aList != null, aList.length == bList.length
     * @param bList != null, bList.length == aList.length
     * @return the Hamming Distance between the two arrays of ints.
     */    
    public static int hammingDistance(int[] aList, int[] bList){
        // check preconditions
        if (aList == null || bList == null || aList.length != bList.length) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"hammingDistance. neither parameter may equal null, arrays" +
            		" must be equal length.");
        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/
        int dist = 0;
    	for (int i = 0;i < aList.length;i++){
    		if(aList[i] != bList[i])
    			dist++;
    	}     
        return dist; //must change
    }
    
    
    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>listA</tt> or 
     * the parameter <tt>listB</tt> are altered as a result of this method.<br>
     * @param listA != null
     * @param listB != null
     * @return <tt>true</tt> if listB is a permutation of listA, 
     * <tt>false</tt> otherwise
     * 
    */
    public static boolean isPermutation(int[] listA, int[] listB) {
        // check preconditions
        if (listA == null || listB == null) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isPermutation. neither parameter may equal null.");
        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/

        if (listA.length != listB.length) return false;
        
        int[] listACopy;
        int[] listBCopy;
//        int[] listACopy = new int[listA.length];
//        int[] listBCopy = new int[listA.length];
        listACopy = Arrays.copyOf(listA, listA.length);
        listBCopy = Arrays.copyOf(listB, listB.length);
        sortlist(listACopy);
        sortlist(listBCopy);
//        Arrays.sort(listACopy);
//        Arrays.sort(listBCopy); 

        
        for (int i = 0;i < listACopy.length;i++){
        	if (listACopy[i] != listBCopy[i])
        		return false;
        }
        return true;
    }
    
    private static void sortlist(int[] list){
    	for (int i = 0; i < list.length; i++){
        	int min = list[i];
        	int index = 0;
        	for (int j = i + 1; j < list.length; j++){
        		if (list[j] < min) {
        			index = j;
        	    	min = list[j];
        		}
        	}
        	list[index] = list[i];
    	    list[i] = min;
        }
    }
    
    /**
     * Determine the index of the String that 
     * has the largest number of vowels. 
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
     * 'U', and 'u'</tt>.
     * The parameter <tt>list</tt> is not altered as a result of this method.
     * <p>pre: <tt>list != null</tt>, <tt>list.length > 0</tt>, there is an least 1 non null element in list
     * <p>post: return the index of the non-null element in list that has the 
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero. 
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param list the array to check
     * @return the index of the non-null element in list that has 
     * the largest number of vowels.
     */
    public static int mostVowels(String[] list) {
        // check preconditions
        if (list == null || list.length == 0 || !atLeastOneNonNull(list))  
            throw new IllegalArgumentException("Violation of precondition: " +
            		"mostVowels. parameter may not equal null and must contain " +
            		"at least one none null value.");
       

        // CS314 STUDENTS: ADD YOUR CODE HERE
        //  You can use all methods from the String class and native arrays.
        int maxIndex = 0;
        for(int i = 1; i < list.length; i++){
        	if (numVowels(list[i]) > numVowels(list[maxIndex]))
        		maxIndex = i;
        }

        return maxIndex; //must change
    }
    
    private static int numVowels (String str){
    	int count = 0;
    	if (str == null)
    		return -1;
    	String vowels = "AEIOUaeiou";
//    	String strCopy = str.toLowerCase();
//    	for (int i = 0;i < str.length();i++){
//    		if (str[i] == 'a' || str[i] == "e" || str[i] == "i" || str[i] == "o" || str[i] == "u")
//    			count++;
//    	return count;
    	for (int i = 0; i < str.length(); i++) {
    		if (vowels.contains("" + str.charAt(i)))
    			count++;
    	}
    	return count;
    }
    	
    

    
    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people. 
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday 
     * after running the simulation.
     */
    private static int getDay(int N){
		Random r = new Random();
	    int max = N;
	    int random_x = r.nextInt(max);
	    return random_x;
	    
	}     
    
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople + 
                    ", numDaysInYear: " + numDaysInYear);
        
        //CS314 STUDENTS: ADD YOUR CODE HERE
        if (numPeople == 1)
        	return 0;
        int[] listDay = new int[numPeople];
        for (int i = 0; i < numPeople; i++){
        	listDay[i] = (int) (Math.random()*numDaysInYear);
         }
        
        int numOfSameBirthdays = 0;
        for (int i = 0; i < numPeople; i++){
        	for (int j = i + 1; j < numPeople; j++){
        		if (listDay[i] == listDay[j])
        			numOfSameBirthdays++;
        	}
        }
        return numOfSameBirthdays; //must change
        
    }
//    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
//        // check preconditions
//        if (numPeople <= 0 || numDaysInYear <= 0)
//            throw new IllegalArgumentException("Violation of precondition: " +
//            		"sharedBirthdays. both parameters must be greater than 0. " +
//                    "numPeople: " + numPeople + 
//                    ", numDaysInYear: " + numDaysInYear);
//        
//        //CS314 STUDENTS: ADD YOUR CODE HERE
//        Random random = new Random();
//        int [] birthdays = new int[numPeople];
//        for (int i = 0; i < birthdays.length; i++) {
//			birthdays[i] = random.nextInt(numDaysInYear);
//		}
//        
//        int [] birthdaysTally = new int[numDaysInYear];
//        
//        for (int i = 0; i < birthdays.length; i++) {
//			birthdaysTally[birthdays[i]]++;
//		}
//        int pairs = 0;
//        for (int i = 0; i < birthdaysTally.length; i++) {
//			pairs += getPairNumbers(birthdaysTally[i]);
//		}
//        return pairs;
//    }
    
//    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
//        // check preconditions
//        if (numPeople <= 0 || numDaysInYear <= 0)
//            throw new IllegalArgumentException("Violation of precondition: " +
//            		"sharedBirthdays. both parameters must be greater than 0. " +
//                    "numPeople: " + numPeople + 
//                    ", numDaysInYear: " + numDaysInYear);
//        
//        int pairs;
//        
//        if (numPeople < numDaysInYear) {
//            int[] people = new int[numPeople];
//            
//            for (int i = 0; i < people.length; i++)
//                people[i] = (int) (Math.random()*numDaysInYear);
//                
//            pairs = 0;    
//            for (int i = 0; i < people.length; i++)
//                for (int j = i+1; j < people.length; j++) 
//                    if (people[i] == people[j]) pairs++;    
//        } else {
//            int[] daysInYear = new int[numDaysInYear];
//            int birthday;
//            
//            for (int i = 0; i < numPeople; i++) {
//                birthday = (int) (Math.random()*numDaysInYear);
//                daysInYear[birthday]++;
//            }
//            
//            pairs = 0;
//            for (int day : daysInYear)
//                pairs += (day*day)/2;                 
//        } 
//        
//        return pairs;
//    }
    
    
    /**
     * return the pairs according to the given number
     * @author SkySource
     * @param int value: it specifies the number for combination
     * @return int : pairs 
     */
//    private static int getPairNumbers(int value) { 	
//    	return (value - 1) * value / 2;
//    }
    
    // this is for the experiment 
    public static int numCount(int numPeople, int numDaysInYear, int numRun) {
    	int count = 0; // count is the num of expericment with one or more paris
    	for (int i = 0; i < numRun; i++){
    		if (sharedBirthdays(numPeople,numDaysInYear) > 0){
    			count++;
    		}
    	}
    	return count;   	
    }


	
	
	
	// this is for the birthday experiment
    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of 
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.*/
    
    
    public static boolean queensAreSafe(char[][] board) {
    	//board.length;
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board) 
                || !onlyContains(board, validChars))
            throw new IllegalArgumentException("Violation of precondition: " +
            		"queensAreSafe. The board may not be null, must be square, " +
            		"and may only contain 'q's and '.'s");        
                
      //CS314 STUDENTS: ADD YOUR CODE HERE
        // find all the q's indices
        int size = board.length;
        for (int row = 0; row < size; row++){
        	for (int col = 0; col < size; col++){
        		if (board[row][col] == 'q'){
        			if (safe(board,row,col) == false)
        				return false;      			
        		}
        	}
        }
        return true;
    }
        
    private static boolean safe(char[][] board, int row, int col)
    {
        int i,j;
        int size = board.length;
        // Check column
        int counter = 0;
        for (i=0; i<size; i++)
            if (board[i][col] == 'q') counter++;
        		if (counter > 1)
        			return false;
        counter = 0;
        // Check row
        		
        for (j=0; j<size; j++)
            if (board[row][j] == 'q')counter++;
            	if (counter > 1)
        			return false;
        counter = 0;
        // Check diagonal down and right
        for (i=row, j=col; i < size && j < size; i++, j++)
            if (board[i][j] == 'q') counter++;
            	if (counter > 1)
        			return false;
        counter = 0;
        // Check diagonal up and left
        for (i=row, j=col; i >=0 && j >=0 ; i--, j--)
            if (board[i][j] == 'q') counter++;
            	if (counter > 1)
        			return false;
        counter = 0;
        // Check diagonal up and right
        for (i=row, j=col; i >=0 && j < size; i--, j++)
            if (board[i][j] == 'q') counter++;
            	if (counter > 1)
        			return false;
        counter = 0;
        // Check diagonal down and left
        for (i=row, j=col; i < size && j >=0 ; i++, j--)
            if (board[i][j] == 'q') counter++;
            	if (counter > 1)
        			return false;
        
        return true;
    }
   
        
//    private static boolean rowCheck(char[][] board1, int row1){
//    	int counter = 0;
//    	for (int colIndex = 0; colIndex < size; colIndex++){
//    		if (board1[row1][colIndex] == 'q')
//    			counter++;
//    	}
//    	if (counter > 1)
//    		return false;
//    	return true;
//    	  	
//    }
//    
//    private static boolean colCheck(char[][] board2, int col2){
//    	int counter = 0;
//    	for (int rowIndex = 0; rowIndex < size; rowIndex++){
//    		if (board1[rowIndex][col2] == 'q')
//    			counter++;
//    	}
//    	if (counter > 1)
//    		return false;
//    	return true;
//    	  	
//    }
//    
      
    
    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1. 
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0 
                || !isRectangular(city) )
            throw new IllegalArgumentException("Violation of precondition: " +
            		"getValueOfMostValuablePlot. The parameter may not be null," +
            		" must value at least one row and at least" +
                    " one column, and must be rectangular."); 
        

        //CS314 STUDENTS: ADD YOUR CODE HERE
        int max = Integer.MIN_VALUE;
        int width,height;
        int cityHeight = city.length;
        int cityWidth = city[0].length;
        for (int i = 0; i < cityHeight; i++){
        	for (int j = 0; j < cityWidth; j++){
        		height = 1;
        		while (i + height <= cityHeight){
        			width = 1;
        			while (j + width <= cityWidth){
        				int plotValue = getValue(city,i,j,width,height);
                		if (plotValue > max){
                			max = plotValue;
                		}
                		width++;
        			}
        			height++;
        		}
        	}
        }
     
        return max; //must change
    }
    
    private static int getValue(int[][] city, int x, int y, int width, int height){
    	int value = 0;
    	for (int i = x; i < x + height; i++){
    		for (int j = y; j < y + width; j++){
    			value += city[i][j];
    		}
    	}   		
    	return value;
    }
    
    
    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    // CS314 STUDENTS: Put your birthday problem experiement code here:
    
    
    // pre: list != null
    // post: return true if at least one element in list is null
    // otherwise return false.
    private static boolean atLeastOneNonNull(String[] list) {
        // check precondition
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"atLeastOneNonNull. parameter may not equal null.");
        
        boolean foundNonNull = false;
        int i = 0;
        while( !foundNonNull && i < list.length ){
            foundNonNull = list[i] != null;
            i++;
        }
        return foundNonNull;
    }
    
    
    /* pre: mat != null
    post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isSquare. paremeter may not be null.");

        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while( isSquare && row < numRows ) {
            isSquare = ( mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }
    
    
    /* pre: mat != null, valid != null
    post: return true if all elements in mat are one of the characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if(mat == null || valid == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"onlyContains. paremeters may not be null.");
        
        int row = 0;
        boolean correct = true;
        while( correct && row < mat.length) {
            int col = 0;
            while(correct && col < mat[row].length) {
                correct = contains(valid, mat[row][col]);
                col++;
            }
            row++;
        }
        return correct;
    }
    
    
    /*  pre: list != null
        post: return true if c is in list
     */
    private static boolean contains(char[] list, char tgtChar) {
        // check preconditions
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"contains. paremeter may not be null.");

        boolean found = false;
        int index = 0;
        while( !found && index < list.length) {
            found = list[index] == tgtChar;
            index++;
        }
        return found;
    }
   
    
     // pre: mat != null, mat.length > 0
     // post: return true if mat is rectangular
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if(mat == null || mat.length == 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isRectangular. paremeter may not be null and must contain" +
            		" at least one row.");

        boolean correct = mat[0] != null;
        int row = 0;
        while(correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }
    
    // make constructor pirvate so no instances of CodeCamp can be created
    private CodeCamp() {
        
    }
}