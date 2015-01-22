import java.util.Arrays;

//  MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Unique section number:
 *  Number of slip days I am using:
 */


/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 *
 * @version Skeleton file for students
 */
public class MathMatrix
{
    // instance variables
    /*CS314 STUDENTS: ADD YOUR CODE HERE*/
	int[][] mat;
    

    
    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        // check the precondition. rectangularMatrix is a private method at end of Matrix class
        if((mat == null) || (mat.length == 0) || (mat[0].length == 0)
                || !rectangularMatrix(mat)) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"int[][] Matrix constructor");
                
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        int numRows = mat.length;
        int numCols = mat[0].length;
        this.mat = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++){
        	this.mat[i] = Arrays.copyOf(mat[i], numCols);
        }
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
    	if(numRows <= 0 || numCols <= 0)
    		throw new IllegalArgumentException("Violation of precondition: " +
            		"numRows > 0 and numCols > 0");
    	
    	mat = new int[numRows][numCols];
    	for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				mat[r][c] = initialVal;			//initializing the matrix with an initial value
			}
		}
    }


    /**
     * change the value of one of the cells in this MathMatrix.
     * <br>pre: 0 <= row < numRows(), 0 <= col < numCols()
     * <br>post: getVal(row, col) = newValue
     * @param row 0 <= row < numRows()
     * @param col 0 <= col < numCols()
     */
    public void changeElement(int row, int col, int newValue) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	mat[row][col] = newValue;
    }


    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int numRows() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        return mat.length; // alter as necessary
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int numCols() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        return mat[0].length; // alter as necessary
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < numRows(), col  0 <= col < numCols()
     * @param  row  0 <= row < numRows()
     * @param  col  0 <= col < numCols()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        return mat[row][col]; // alter as necessary
    }


   /**
    * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
    * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * <br>post: This method does not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
    * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
    */
    public MathMatrix add(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// is this ok? this?
    	if(this.numRows() != rightHandSide.numRows() || this.numCols() != rightHandSide.numCols())
    		throw new IllegalArgumentException("Violation of the precondition:" + 
    	"rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()");
    		
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	final int ROWS = this.numRows();
    	final int COLS = this.numCols();
    	int [][] resultMat = new int[ROWS][COLS];
    	for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				//changing each element in the matrix by adding the value each matrix at (r, c)
				resultMat[r][c] = this.mat[r][c] + rightHandSide.mat[r][c];
			}
		}
    	
    	//? why do I need new?
        return new MathMatrix(resultMat); // alter as necessary
    }



   /**
    * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
    * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * <br>post: This method does not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
    * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
    */
    public MathMatrix subtract(MathMatrix rightHandSide) {
    	if(numRows() != rightHandSide.numRows() || numCols() != rightHandSide.numCols())
    		throw new IllegalArgumentException("Violation of the precondition:" + 
    	"rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()");
    	
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// using the add method to implement substract, but way more inefficient than implementing directly
    	rightHandSide.scale(-1);					  // made the right hand side negative
    	MathMatrix result = this.add(rightHandSide);
    	rightHandSide.scale(-1);			// change the right hand side back to the original
        return result; // alter as necessary
    }



   /**
    * implements matrix multiplication, (this MathMatrix) * rightHandSide.
    * <br>pre: rightHandSide.numRows() = numCols()
    * <br>post: This method should not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numCols()
    * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
    * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
    */
    public MathMatrix multiply(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	if (this.numCols() != rightHandSide.numRows())
    		throw new IllegalArgumentException("violation of the precondition");
    	int[][] newMatrix = new int[this.numRows()][rightHandSide.mat[0].length];
    	for (int i = 0; i < this.numRows(); i++){
    		for (int j = 0 ; j < rightHandSide.mat[0].length; j++){
    			newMatrix[i][j] = dotProduct(i, j, rightHandSide);
    		}
    	}
        return new MathMatrix(newMatrix); // alter as necessary
    } 
    
    private int dotProduct(int rowIndex, int colIndex, MathMatrix rightHandSide){
    	int result = 0;
    	for (int i = 0; i < numCols(); i++)
    	result += this.mat[rowIndex][i] * rightHandSide.mat[i][colIndex];
    	return result;
    }


   /**
    * Multiply all elements of this MathMatrix by factor.
    * <br>pre: none
    * <br>post: all elements in this matrix have been multiplied by factor. 
    * In other words after this method has been called getVal(r,c) = old getVal(r, c) * factor
    * for all valid r and c.
    * @param factor the value to multipy every cell in this Matrix by.
    */
    public void scale(int factor) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	for (int i = 0; i < this.numRows(); i++){
    		for (int j = 0; j < this.numCols(); j++){
    			this.mat[i][j] = factor * this.mat[i][j];
    		}
    	}
    }


    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	int newNumRows = this.mat[0].length;
    	int newNumCols = this.mat.length;
    	int[][] newMatrix = new int[newNumRows][newNumCols];
    	for (int i = 0; i < newNumRows; i++){
    		for (int j = 0; j < newNumCols; j++){
    			newMatrix[i][j] = this.mat[j][i]; 
    		}
    	}		
    	return new MathMatrix(newMatrix);
    }
    	
    

    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide) {
        /* CS314 Students. The following is standard equals
         * method code. We will learn about it in a few weeks.
         */
        boolean result = false;
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix)rightHandSide;
            for (int r = 0; r < numRows(); r++) {
				for (int c = 0; c < numCols(); c++) {
					if(this.mat[r][c] != otherMatrix.getVal(r, c))
						return false;
				}
			}
        }
        return true;
    }

    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a seperate line.
     * Spacing based on longest element in this Matrix.
     * Each row starts and ends with a vertical bar: '|'
     */
    public String toString(){
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	StringBuilder ret = new StringBuilder();
    	int length = getLongestLength(mat);			//find out the max length
    	for (int r = 0; r < numRows(); r++) {
    		ret.append("|");
			for (int c = 0; c < numCols(); c++) {
				addPaddings(ret, length, mat[r][c]);
			}
			ret.append("|\n");
		}
        return ret.toString(); // alter as necessary
    }
    
    private void addPaddings(StringBuilder builder,int length,int number) {
    	int padding = length + 1 - (number + "").length();
    	for (int i = 0; i < padding; i++) {
			builder.append(" ");
		}
		builder.append(number);
    }
    
    // get the longest number in a 2d array
    // finding the largest number and the minimum number, and return the length of the longer one
    // pre: array != null
    // post: the longest number's length
    private int getLongestLength(int [][] array) {
    	int maxNum = 0;
    	int minNum = 0;
    	for (int r = 0; r < array.length; r++) {
    		for (int c = 0; c < array[r].length; c++) {
    			// find the max and min
    			maxNum = Math.max(maxNum,array[r][c]);
    			minNum = Math.min(minNum,array[r][c]);
			}
		}
    	return Math.max((maxNum + "").length(), (minNum + "").length());
    }
    
    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. numRows() == numCols()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < r; c++) {
				if(mat[r][c] != 0)
					return false;
			}
		}
        return true; // alter as necessary
    }
    // method to ensure mat is rectangular
    // pre: mat != null
    public static boolean rectangularMatrix(int[][] mat) {
    	if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null");
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;

        while( isRectangular && row < mat.length ) {
            isRectangular = ( mat[row].length == COLUMNS );
            row++;
        }

        return isRectangular;
    }
}