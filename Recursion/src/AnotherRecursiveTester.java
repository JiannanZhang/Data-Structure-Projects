public class AnotherRecursiveTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recursive r = new Recursive();
//		String puzzle1 = "530070000600195000098000060800060003400803001700020006060000280000419005000080079";
//        int[][] board = stringToBoard(puzzle1);
//        int[][] result = r.getSudokoSolution(board);
//        String actualBoard = boardToString(result);
//        String expectedBoard = "534678912672195348198342567859761423426853791713924856961537284287419635345286179";
//        if(actualBoard.equals(expectedBoard))
//            System.out.println( "Test 22 passed. sudoko solver.");
//        else {
//            System.out.println("Test 22 failed. sudoku solver:");
//            System.out.println("Expected board:");
//            printBoard(stringToBoard(expectedBoard));
//            System.out.println();
//            System.out.println("Actual board:");
//            printBoard(result);
//        }
//
//
//        String puzzle2 = "370002060000000050008073900147000000009301200000000891005120600010000000080600043";
//        board = stringToBoard(puzzle2);
//        result = r.getSudokoSolution(board);
//        actualBoard = boardToString(result);
//        expectedBoard = "371592468294816357568473912147289536859361274623745891735124689416938725982657143";
//        if(actualBoard.equals(expectedBoard))
//            System.out.println( "Test 23 passed. sudoko solver.");
//        else {
//            System.out.println("Test 23 failed. sudoku solver:");
//            System.out.println("Expected board:");
//            printBoard(stringToBoard(expectedBoard));
//            System.out.println();
//            System.out.println("Actual board:");
//            printBoard(result);
//        }
//
//
//        String puzzle3 = "000010300008003500500002001003000007106070403400000200200300004004900600007080000";
//        assert puzzle3.length() == 81;
//        board = stringToBoard(puzzle3);
//        result = r.getSudokoSolution(board);
//        actualBoard = boardToString(result);
//        expectedBoard = "642715389718493526539862741823546917196278453475139268261357894384921675957684132";
//        if(actualBoard.equals(expectedBoard))
//            System.out.println( "Test 24 passed. sudoko solver.");
//        else {
//            System.out.println("Test 24 failed. sudoku solver:");
//            System.out.println("Expected board:");
//            printBoard(stringToBoard(expectedBoard));
//            System.out.println();
//            System.out.println("Actual board:");
//            printBoard(result);
//        }
//
//
//        String puzzle4 = "080700500700008021000002009800100206690824015105009008500400000430200007009007080";
//        assert puzzle4.length() == 81;
//        board = stringToBoard(puzzle4);
//        result = r.getSudokoSolution(board);
//        actualBoard = boardToString(result);
//        expectedBoard = "982716534754938621361542879843175296697824315125369748578493162436281957219657483";
//        if(actualBoard.equals(expectedBoard))
//            System.out.println( "Test 25 passed. sudoko solver.");
//        else {
//            System.out.println("Test 25 failed. sudoku solver:");
//            System.out.println("Expected board:");
//            printBoard(stringToBoard(expectedBoard));
//            System.out.println();
//            System.out.println("Actual board:");
//            printBoard(result);
//        }
//
//
//        String puzzle5 = "000050400080102350400307086050000670100809002038000040590203004047906020003070000";
//        assert puzzle5.length() == 81;
//        board = stringToBoard(puzzle5);
//        result = r.getSudokoSolution(board);
//        actualBoard = boardToString(result);
//        expectedBoard = "000050400080102350400307086050000670100809002038000040590203004047906020003070000";
//        if(actualBoard.equals(expectedBoard))
//            System.out.println( "Test 26 passed. sudoko solver.");
//        else {
//            System.out.println("Test 26 failed. sudoku solver:");
//            System.out.println("Expected board:");
//            printBoard(stringToBoard(expectedBoard));
//            System.out.println();
//            System.out.println("Actual board:");
//            printBoard(result);
//        }
//
//                // try the Sierpinski triangle, uncomment when ready
//                r.drawTriangles(400, 100, 360);
//        
//        //        // try the Sierpinski Carpet, uncomment when ready
//                r.drawCarpet(729, 4);
//
//        // also try this
//             r.drawCarpet(729, 1);
//
//        studentTests(r);
//
//    }
//
//    // pre: r != null
//    // post: run student test
//    private static void studentTests(Recursive r) {
//        // CS314 students put your tests here
//
//    }
//
//    private static String boardToString(int[][] board) {
//        StringBuilder result = new StringBuilder(81);
//        for(int r = 0; r < board.length; r++)
//            for(int c = 0; c < board[r].length; c++)
//                result.append(board[r][c]);
//        return result.toString();
//    }
//
//    private static int[][] stringToBoard(String puzzle) {
//        int[][] result = new int[Recursive.BOARD_SIZE][Recursive.BOARD_SIZE];
//        int index = 0;
//        for(int r = 0; r < result.length; r++)
//            for(int c = 0; c < result.length; c++, index++)
//                result[r][c] = puzzle.charAt(index) - '0';
//        return result;
//    }
//
//    private static void printBoard(int[][] board) {
//        for(int r = 0; r < board.length; r++) {
//            for(int c = 0; c < board[r].length; c++) {
//                System.out.print(board[r][c]);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
//    }     
//    
//
    int[][] world = {{5,5,5,5,5,5},
            {5,5,5,5,5,5},
            {5,5,5,5,5,5},
            {5,5,4,4,5,5},
            {5,5,3,3,5,5},
            {5,5,2,2,5,5},
            {5,5,5,1,5,5},
            {5,5,5,-2,5,5}};


    if( r.canFlowOffMap(world,0,0))
        System.out.println( "Test 11 passed. can flow off map.");
    else
        System.out.println( "Test 11 failed. can flow off map.");

    if( !r.canFlowOffMap(world,1,1))
        System.out.println( "Test 12 passed. can flow off map.");
    else
        System.out.println( "Test 12 failed. can flow off map.");

    if( r.canFlowOffMap(world,3,3))
        System.out.println( "Test 13 passed. can flow off map.");
    else
        System.out.println( "Test 13 failed. can flow off map.");

    if( r.canFlowOffMap(world,1,5))
        System.out.println( "Test 14 passed. can flow off map.");
    else
        System.out.println( "Test 14 failed. can flow off map.");

    world = new int[][]
                      {{10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10,  5, 10, 10, 10},
            {10, 10, 10,  6, 10, 10, 10},
            {10, 10, 10,  7, 10, 10, 10},
            {5,   6,  7,  8,  7,  6, 10},
            {10, 10, 10,  7, 10, 10, 10},
            {10, 10, 10,  6, 10, 10, 10},
            {10, 10, 10,  5, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10},
                      };

    if( !r.canFlowOffMap(world,3,3))
        System.out.println( "Test 15 passed. can flow off map.");
    else
        System.out.println( "Test 15 failed. can flow off map.");

    if( r.canFlowOffMap(world,4,3))
        System.out.println( "Test 16 passed. can flow off map.");
    else
        System.out.println( "Test 16 failed. can flow off map.");

}
}