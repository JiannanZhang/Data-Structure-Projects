/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *  
 *  Student 2 
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *  
 */


//imports

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Color;

public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.<br> 
     *   pre: n >= 0<br>
     *   post: Returns a String that represents N in binary.
     *   All chars in returned String are '1's or '0's. Most significant digit is at position 0
     *   @param n the base 10 int to covnert to base 2
     */
    public String getBinary(int n) {
        if( n < 0) 
            throw new IllegalArgumentException("Failed precondition: getBinary. n must be >= 0. n: " + n);
        return ""; //dummy return, replace as necessary
    }


    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     */
    public String revString(String stringToRev) {
        if( stringToRev == null)
            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
        return ""; //dummy return, replace as necessary
    }


    /**
     * Problem 3: Returns the number of elements in data 
     * that are followed directly by value that is
     * double that element. 
     * pre: data != null
     * post: return the number of elements in data that are followed immidiately by double the value
     */
    public int nextIsDouble(int[] data) {
        return 0; // must change. Need to write a helper method 
    }


    // CS314 students, add your nextIsDouble helper method here


    /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
     *   pre: number != null, all characters in number are digits<br>
     *   post: see tips section of assigment handout
     *   @param number The number to find mnemoics for
     */
    public ArrayList<String> listMnemonics(String number) {
        if( number == null ||  !allDigits(number))
            throw new IllegalArgumentException("Failed precondition: listMnemonics");

        ArrayList<String> result = new ArrayList<String>();
        recursiveMnemonics(result, "", number);
        return result;
    }


    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a paritl (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used from the original number
     */
    private void recursiveMnemonics(ArrayList<String> mnemonics, 
            String mnemonicSoFar, String digitsLeft) {

        // CS314 students, complete this method
    }

    
    // used by method digitLetters
    private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with this digit on a phone keypad
     */
    private String digitLetters(char ch) {
        assert ('0' <= ch) && (ch <= '9') : "Failed precondition: digitLetters";
        int index = (int)(ch - '0');
        return letters[index];
    }


    /* helper method for listMnemonics
     * pre: s != null
     * post: reutrn true if every character in s is a digit ('0' through '9')
     * */
    private boolean allDigits(String s) {
        assert s != null : "Failed precondition: allDigits";
        boolean allDigits = true;
        int i = 0;
        while(i < s.length() && allDigits){
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }


    /**
     * Problem 5: Create a DrawingPanel and place Sierpinski triangles in it. 
     * The lower left corner shall be 20 pixels 
     * from the left edge of the window
     * and 20 pixels from the bottom of the window.
     * @param windowSize > 20
     * @param minSideLength > 4
     * @param startingSideLength > minSideLength
     */
    public void drawTriangles(int windowSize, 
            int minSideLength, int startingSideLength){

        DrawingPanel p = new DrawingPanel(windowSize, windowSize);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLUE);
        drawTriangles(minSideLength, startingSideLength, 20, windowSize - 20, g);
    }

    // helper method for drawTriangles
    private void drawTriangles(int minSideLength, double currentSideLength, 
            double x1, double y1, Graphics g) {

    }


    /**
     * Problem 6: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a sqauer in the carpet.
     */
    public void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }


    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
    	
    	if (size > limit) {
    		int newSize = size / 3;
    		
    		g.fillRect((int)(x + newSize),(int) (y + newSize), newSize, newSize) ;
    		
    		drawSquares(g, newSize, limit, (int) (x), (int)y);
    		drawSquares(g, newSize, limit, (int) (x + newSize), (int)y);
    		drawSquares(g, newSize, limit, (int) (x + 2 * newSize), (int)y);
    		drawSquares(g, newSize, limit, (int) (x), (int)(y + newSize));
    		drawSquares(g, newSize, limit, (int) (x + 2 * newSize), (int)(y + newSize) );
    		drawSquares(g, newSize, limit, (int) (x), (int)(y + 2 * newSize) );
    		drawSquares(g, newSize, limit, (int) (x + newSize), (int)(y + 2 * newSize));
    		drawSquares(g, newSize, limit, (int) (x + 2 * newSize), (int)(y + 2 * newSize));
    		
    	}
    		

    }

    // size of Sudoko board
    /**
     * The size of the Sudoko board. Value will be a perfect square greater than 0.
     */
    public static final int BOARD_SIZE = 9; // must be perfect square
    
    /**
     * The size of a mini marix on the Sodoko board.
     */
    public static final int MINI_SIZE = (int)(Math.sqrt(BOARD_SIZE));


    /** 
     * Problem 7: Find a solution to a Sudoko puzzle. 
     * <br>pre: board != null, board is 9 by 9. 
     * <br>post: return a board that is the solved puzzle 
     * or a copy of the original board if there is no solution
     * @param startBoard The starting board. 
     * Empty values = 0, given values = 1 through 9 may not be changed
     */
    public int[][] getSudokoSolution(int[][] startBoard) {
        if(startBoard == null || startBoard.length != BOARD_SIZE || startBoard[0].length != BOARD_SIZE)
            throw new IllegalArgumentException("Violation of precondition in getSudo");

        // store solution in board so we don't change startBoard
        int[][] board = copyBoard(startBoard); 

        // CS314 students. Call your recursive method here!
        recursivePut(board,0,0);
        
        return board;
    }


    // CS314 students, add your recursive sudoko solver method here
    private boolean recursivePut(int[][] board,int row, int col) {

    	if (row == BOARD_SIZE)
    		return true;
    	else if (board[row][col] != 0) {
    		int newCol = col + 1;
    		int newRow = row;
    		if (newCol == board.length) {
    			newRow = newRow + 1;
    			newCol = 0;
    		}
    		return recursivePut(board,newRow, newCol);
    	}else {
    		for (int i = 1; i <= 9; i++) {
				putDigit(board, i, row, col);
				if (digitsOkay(board, row, col)) {
					int newCol =col + 1;
		    		int newRow = row;
		    		if (newCol == board.length) {
		    			newRow += 1; // move to next row
		    			newCol = 0; //reset col
		    	} 
		    		//return what if the recursive method is true? return true cuz this means we reached base case!!
		    		if (recursivePut(board,newRow,newCol)) { // recursive call on the next position
		    			return true;
		    		}
				}
				removeDigit(board, row, col );;
    		}
    		return false; // where to put this return statement? put it here in order to backtrack!
    	}
    }


    // helper method to make a copy of a sudoko.
    // CS314 students, you DO NOT need to call this method.
    private static int[][] copyBoard(int[][] startBoard) {
        int[][] result = new int[startBoard.length][];
        for(int r = 0; r < result.length; r++)
            result[r] = Arrays.copyOf(startBoard[r], startBoard[r].length);
        return result;
    }



    // Helper method check to ensure no digit other than zero
    // is repeated in a given row, column, or mini matrix.
    private static boolean digitsOkay(int[][] board, int row, int col) {
        return portionOkay(row, 0, 0, 1, board) &&
        portionOkay(0, col, 1, 0, board) &&
        miniMatrixOkay(row, col, board);

    }
    
    private void putDigit(int[][] board, int digit, int row, int col) {
    	board[row][col] = digit;
    }
    
    private void removeDigit(int[][] board, int row, int col) {
    	board[row][col] = 0;
    }



    // helper method to see if no repeat digits (other than 0) in a row or column
    // CS314 students, you don't need to call this method directly
    private static boolean portionOkay(int rowStart, int colStart, int rowChange, int colChange, int[][] board) {
        // check digits in row or column. 
        // for all non zero digits update array of booleans. if digit appears twice then
        // used[digit] is set to true first time and we return false second time
        boolean[] used = new boolean[BOARD_SIZE + 1]; // don't use zero element in array

        // pretty GACKY, change to while loop?
        for(int i = 0, row = rowStart, col = colStart; i < BOARD_SIZE; i++, row += rowChange, col += colChange) {
            int digit = board[row][col];
            if(digit != 0){
                if(used[digit])
                    return false; // duplicate!!
                used[digit] = true; // mark as used
            }
        }
        return true; // no repeats found!
    }

    // helper to check that no digits other than 0 are
    // repeated in the mini matric cell row, col
    // is a part of.
    // CS314 students, you don't need to call this method directly
    private static boolean miniMatrixOkay(int row, 
            int col, int[][] board) {

        boolean[] used = new boolean[BOARD_SIZE + 1];

        // figure out upper left indices for mini matrix
        // we need to check

        // row 0,1,2 -> 0, row 3, 4, 5 -> 3, row 6, 7, 8 -> 6 
        // same logic for column
        row = (row / MINI_SIZE) * MINI_SIZE;
        col = (col / MINI_SIZE) * MINI_SIZE;

        for(int r = 0; r < 3; r++)
            for(int c = 0; c < 3; c++)  {
                int digit = board[row + r][col + c];
                if(digit != 0){
                    if(used[digit])
                        return false; // duplicate!!
                    used[digit] = true; // mark as used
                }
            }
        return true;
    }    


    /**
     * Problem 8: Determine if water at a given point 
     * on a map can flow off the map.
     * <br>pre: map != null, map.length > 0, 
     * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
     * <br>post: return true if a drop of water starting at the location 
     * specified by row, column can reach the edge of the map, false otherwise.
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     */
    public boolean canFlowOffMap(int[][] map, int row, int col) {
        if( map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map))
            throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
        // base case
        if (row == 0 || col == 0 || row == map.length - 1 || col == map[0].length - 1)
        	return true;
        // recursive 4 cases
        else {
        	// north
        	if (map[row][col] > map[row - 1][col] && canFlowOffMap(map, row - 1, col))
        		return true;
        	// south
        	if (map[row][col] > map[row + 1][col] && canFlowOffMap(map, row + 1, col))
        		return true;
        	//west
        	if (map[row][col] > map[row][col - 1] && canFlowOffMap(map, row, col - 1))
        		return true;
        	// east
        	if (map[row][col] > map[row][col + 1] && canFlowOffMap(map, row, col + 1))
        		return true;
        	
        }
        
        return false; //dummy return, replace as necessary
    }

    /* helper method for canFlowOfMap - CS314 stdents you should not have to 
     * call this method,
     * pre: mat != null, 
     */
    private boolean inbounds(int r, int c, int[][] mat) {
        assert mat != null : "Failed precondition: inbounds";
        return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
    }

    /* 
     * helper method for canFlowOfMap - CS314 stdents you should not have to 
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";

        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while( correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }


    /**
     * Problem 9: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the 
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. 
     * @param numTeams the number of teams to form. 
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * The return value will be greater than or equal to 0.
     */
    public int minDifference(int numTeams, int[] abilities) {
        return -1;
    }
}
