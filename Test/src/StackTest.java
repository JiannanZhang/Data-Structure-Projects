import java.util.Stack;


public class StackTest {

	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>(); // put stuff in stack
		for(int i = 0; i < 5; i++)
		s.push( i );
		// Print out contents of stack
		// while emptying it.
		/*
		for(int i = 0; i < s.size(); i++)
		    System.out.print( s.pop() + " "); //output is 4 3 2 not as we expect
		*/
		
		int limit = s.size();
		for (int i = 0; i < limit; i++) {
			System.out.println(s.pop() + " ");
		}
	}

}
