package lab02;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	// default constructor
	public Matrix() {
	}

	// constructor 1 - Constructor for new zero matrix
	public Matrix(int rowDim, int colDim) {
		// Initialize a member variable
		this.numRows = rowDim;
		this.numColumns = colDim;
		this.data = new int[rowDim][colDim];
	}

	// constructor 2 - Constructor with data for new matrix (automatically
	// determines dimensions)
	public Matrix(int d[][]) {
		// Initialize a member variable
		this.numRows = d.length;
		this.numColumns = d[0].length;
		this.data = new int[numRows][numColumns];

		// Assign the input array to data
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = d[i][j];
			}
		}
	}

	// instruct the compiler that we do indeed intend for this method to override
	// the superclass' (Object) version
	@Override
	public String toString() {
		// Mutable strings hold matrix information
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[i].length; j++) {
				builder.append(this.data[i][j] + " ");
			}

			// A newline
			builder.append(System.lineSeparator());
		}

		// Returns the result
		return builder.toString();
	}

	// instruct the compiler that we do indeed intend for this method to override
	// the superclass' (Object) version
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix

		// Compares two matrix objects for consistency
		if (m.numRows == this.numRows && m.numColumns == this.numColumns) {
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numColumns; j++) {
					// If the corresponding elements are not equal, return false
					if (m.data[i][j] != this.data[i][j]) {
						return false;
					}
				}
			}
		}

		// Returns True if all matrix information and elements are consistent
		return true;
	}

	/**
	 * multiply two matrices
	 * @param m
	 * @return
	 */
	public Matrix mult(Matrix m) {
		if((this.data.length==m.numRows)&&(this.data[0].length==m.numColumns)) {
		
		
		// Initialize the new array
		int array[][] = new int[this.data.length][m.data[0].length];

		// Iterate over two arrays
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < m.data[0].length; j++) {
				int sum = 0;
				for (int k = 0; k < m.data.length; k++) {
					// Compute the product of two elements
					sum += this.data[i][k] * m.data[k][j];
				}
				array[i][j] = sum;
			}
		}

		// Returns a new matrix object
		return new Matrix(array);
	}
		return null;
	}

	/**
	 * add two matrices
	 * @param m
	 * @return
	 */
	public Matrix add(Matrix m) {
		// Initialize the new array
		if((this.data.length==m.numRows)&&(this.data[0].length==m.numColumns)) {
//			System.out.print("cannot add");
			

		
		int array[][] = new int[this.data.length][this.data[0].length];

		// Adding matrices
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				// Calculate the sum of the corresponding two elements
				array[i][j] = this.data[i][j] + m.data[i][j];
			}
		}

		// Returns a new matrix object
		return new Matrix(array); // placeholder
	}
		return null;
	}

	/**
	 * matrix transpose
	 * @return
	 */
	public Matrix transpose() {
		// Define the new array after the transpose
		int array[][] = new int[numColumns][numRows];

		// Transpose the array
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				array[j][i] = this.data[i][j];
			}
		}

		// Returns a new matrix object
		return new Matrix(array);
	}
}
