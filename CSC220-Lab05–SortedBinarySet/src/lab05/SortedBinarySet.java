package lab05;

public class SortedBinarySet {
	/** represent the simple array that holds the list values */
	public double[] theData;
	/** holds the length of the storage array */
	private int capacity;
	/** holds the number of elements in the list */
	private int size;

	/** constant for initial storage array capacity */
	private static final int INITIAL_STORAGE_CAPACITY = 11;

	/** keep this FALSE for lab; we will play with this in the assignment **/
	public boolean usesBinarySearch = false;

	/** default constructor */
	public SortedBinarySet() {
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
		this.capacity = INITIAL_STORAGE_CAPACITY;
		this.size = 0;

		// TODO
	}

	public SortedBinarySet(double[] input) {
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
		this.capacity = INITIAL_STORAGE_CAPACITY;

		for (int i = 0; i < input.length; i++) {

			int index = findIndex(input[i]);

			// If the element does not exist, it is inserted
			if (index < 0) {
				insert(input[i]);
			}

		}
	}

	public boolean empty() {
		return size == 0;
	}

	public int size() {
		return size; 
	}

	public void grow() {
		this.capacity *= 2;
		double[] temp = new double[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = theData[i];
		}
		theData = temp;
	}

	public String toString() {
		String cap = "Capacity: " + this.capacity;
		String siz = "Size: " + this.size;
		String dat = "{";
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				dat = dat+ "{"+ theData[i] ;
			} else {
				dat += theData[i] + ",";
			}
		}

		dat += "}";
		return cap + "," + siz + "," + "Data:" + dat;

		// TODO // placeholder
	}

	public void clear() {
		this.capacity = INITIAL_STORAGE_CAPACITY;
		this.theData = new double[INITIAL_STORAGE_CAPACITY];
		this.size = 0;
	}

	public boolean insert(double newVal) {
		int loc = findIndex(newVal);
		if (loc >= 0) {
			return false;
		}
		if (size == capacity) {
			this.grow();
		}
		loc = -(loc + 1);
		for (int i = size; i > loc; i--) {
			theData[i] = theData[i - 1];
		}
		theData[loc] = newVal;
		size++;
		return true;

		// TODO
		// allow to insert
		// have space
		// add number
	}

	public boolean remove(double element) {
		// Query the location of the element
		int index = findIndex(element);
		if (index < 0) {
			// Return False if one does not exist
			return false;
		} else {
			// Declaring a new array
			double[] theDataTemp = new double[theData.length - 1];
			int iTemp = 0;
			// Copies the elements into the new array
			for (int i = 0; i < theData.length; i++) {
				if (i != index) {
					theDataTemp[iTemp] = theData[i];
					iTemp++;
				}
			}

			// Updates the array elements and decreases size by 1
			this.theData = theDataTemp;
			this.size--;

			return true;
		}
	}

	private int sequentialFind(double target) {
		for (int i = 0; i < size; i++) {
			if (target == theData[i]) {
				return i;

			}
			if (target < theData[i]) {
				return -i - 1;
			}
		}
		// TODO
		return -size - 1; // placeholder
	}

	private int binaryFind(double target) {
		// TODO

		int low = 0;
		int high = theData.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (theData[mid] < target) {
				low = mid + 1;
			} else if (theData[mid] > target) {
				high = mid - 1;
			} else if (theData[mid] == target)
				return mid;
		}

		return -1; // placeholder
	}

	public int findIndex(double target) {
		if (usesBinarySearch) {
			return binaryFind(target);
		} else {
			return sequentialFind(target);

		}
	}

	public boolean containsAll(double[] elements) {
		// Loop through all the elements
		for (int i = 0; i < elements.length; i++) {
			int index = binaryFind(elements[i]);

			// Return False if one does not exist
			if (index < 0) {
				return false;
			}
		}

		// Return True if all exist
		return true;
	}

	public boolean contains(double element) {
		int index = binaryFind(element);

		// Check whether the subscript is 1
		return index < 0 ? false : true;
	}

}
