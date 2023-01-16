/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package lab02;

public class MatrixTester {
	public static void main(String[] args) {
		Matrix M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });

		Matrix M2 = new Matrix(new int[][] { { 7, 8, 9 }, { 10, 11, 12 } });

		Matrix M3 = new Matrix(new int[][] { { 7, 8 }, { 9, 10 }, { 11, 12 } });
		
		
		
		
		
		

		/*
		 * Note that none of the tests below will be correct until you have implemented
		 * all methods. This is just one example of a test, you must write more tests
		 * and cover all cases.
		 */

		Matrix ComputedAddition = M1.add(M2);

		System.out.println("Computed result for M1 + M2:\n" + ComputedAddition);

		// this is the known correct result of multiplying M1 by M2
		Matrix referenceMultiply = new Matrix(new int[][] { { 58, 64 }, { 139, 154 } });

		// get the matrix computed by your mult method
		Matrix computedMultiply = M1.mult(M3);

		// exercises your toString method
		System.out.println("Computed result for M1 * M3:\n" + computedMultiply);

		// exercises your .equals method, and makes sure that your computed result is
		// the same as the known correct result
		if (!referenceMultiply.equals(computedMultiply))
			System.out.println("Your multiplication is wrong. Should be:\n" + referenceMultiply);

		Matrix referenceAdd = new Matrix(new int[][] { { 8, 10, 12 }, { 14, 16, 18 } });

		if (!referenceAdd.equals(ComputedAddition)) {
			System.out.println("Your add is wrong. Should be:\n" + referenceAdd);
		}

		// Initialize the matrix. The default element is 0
		Matrix M4 = new Matrix(2, 3);
		
		Matrix m1_m4 = M1.add(M4);
		System.out.println("Computed result for M1 + M4:\n" + m1_m4);

		// Compares two matrix objects for equality
		if (M1.equals(m1_m4)) {
			System.out.println("Your add is correctness.\n");
		}

		// get the matrix computed by your add method
		Matrix m1_m3 = M1.add(M3);
		System.out.println("Computed result for M1 + M3:\n" + m1_m3);

		// Compares two matrix objects for equality
		if (M1.equals(m1_m3)) {
			System.out.println("Your add is correctness.\n");
			
			
		}
		System.out.println("Your add is wrong.\n");

		// Initialize the matrix
		Matrix M5 = new Matrix(new int[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } });

		// get the matrix computed by your transpose method
		Matrix M6 = M1.transpose();
		System.out.println("Computed result for transpose(M1):\n" + M6);

		// Compares two matrix objects for equality
		if (M5.equals(M6)) {
			System.out.println("Your transpose is correctness.");
		}
	}
}
