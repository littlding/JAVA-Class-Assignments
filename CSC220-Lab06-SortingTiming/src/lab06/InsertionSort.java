package lab06;

public class InsertionSort<E extends Comparable<E>> {

    /** The constant in the formula t = c * O() */
    private double c;

    /** The order O() of the implementation.
        If your implementation is in O(n^2), use Math.pow().
	    @param n index
	    @return the function of n inside the O()
	 */
    public double O(int n) {
         return Math.pow(n, 2);
    }

    /** Calculates the constant c using a given input array of type E.
        Units of time should be converted to microseconds
    */
    public void fit(E[] array) {
    	long start = System.nanoTime();
    	sort(array);
    	long stop = System.nanoTime();
    	
    	double time = (stop-start)/1000;
    	double O = O(array.length);
    	c= time/O;
    }

    /** Predicts the running time of an insertion sort for some index n
        @param n
        @return the estimated amount of time in unit microseconds
    */
    public double predict(int n) {
        // t = c*o()
        return c * O(n); 
    }

    /** Performs an insertion sort using a given input array
        @param array the (unsorted) array
        @return the sorted array
    */
    public E[] sort(E[] array) {

        /* Handle case where length of array is 1 or less */
    	if (array.length<=1) {
    		return array;
    	}

        /* make a copy of the array to sort */
        E[] sorted = array.clone();

        for (int i = 0; i <sorted.length; i++) {
            E tmp = sorted[i];
            int j = i-1;
            for ( ;j >=0; j--) {
                if (sorted[j].compareTo(tmp) > 0){
                	sorted[j+1]=sorted[j];
                }else
                {
                    break;
                }
            }
            sorted[j+1]=tmp;
        }


        return sorted;
    }

}
