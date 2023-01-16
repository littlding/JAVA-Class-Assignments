package lab05;

public class Tester {
	public static void main(String[] args) {
		SortedBinarySet sbs1 = new SortedBinarySet();
		System.out.println("SBS1: " + sbs1.toString());
		System.out.println("Is Empty: " + sbs1.empty());
		System.out.println("Size: " + sbs1.size());

		sbs1.grow();
		System.out.println("SBS1.grow()" + sbs1);

		sbs1.clear();
		System.out.println("SBS1.clear()" + sbs1);

		SortedBinarySet sbs2 = new SortedBinarySet();
		System.out.println("SBS2: " + sbs2);
		
		sbs2.insert(3.0);
		System.out.println("Insert(3,0): " + sbs2);
		System.out.println("Is Empty: " + sbs2.empty());
		System.out.println("Size: " + sbs2.size());
		
		SortedBinarySet sbs3 = new SortedBinarySet();
		double[] nums = { 2.2, 5.2, 7.1, 8.2, 1.0, 0.0, 0.0, 3.4, 3.1, 105.103, 47.56327, 19.1 };
		for (double num : nums) {
			sbs3.insert(num);

		}
		System.out.println("Big Insert " + sbs3);

		boolean result = sbs3.remove(2.2);

		System.out.println("remove(2.2)" + sbs3);

		result = sbs3.remove(0.0);

		System.out.println("remove(0.0)" + sbs3);

		result = sbs3.remove(105.103);

		System.out.println("remove(0.0)" + sbs3);

		sbs3.usesBinarySearch = true;

		result = sbs3.remove(8.2);

		System.out.println("remove(8.2)" + sbs3);

		result = sbs3.remove(19.1);

		System.out.println("remove(19.1)" + sbs3);

		result = sbs3.remove(1.0);

		System.out.println("remove(1.0)" + sbs3);

		result = sbs3.contains(3.1);

		System.out.println(result == true ? "Success" : "Failed");

		result = sbs3.contains(3.2);

		System.out.println(result == true ? "Success" : "Failed");

		double[] elements = { 3.1, 3.4, 5.2 };

		result = sbs3.containsAll(elements);
		System.out.println(result == true ? "Success" : "Failed");

		double[] elements1 = { 3.1, 3.4, 5.3 };

		result = sbs3.containsAll(elements1);
		System.out.println(result == true ? "Success" : "Failed");

		double[] elements2 = { 3.1, 3.4, 5.3 };
		SortedBinarySet sbs4 = new SortedBinarySet(elements2);
		System.out.println("SortedBinarySet(double[] input):" + sbs4);

		double[] elements3 = { 3.1, 3.4, 5.3, 3.4 };
		SortedBinarySet sbs5 = new SortedBinarySet(elements3);
		System.out.println("SortedBinarySet(double[] input):" + sbs5);

		SortedBinarySet sbs6 = new SortedBinarySet(elements3);
		for (int i = 0; i < 100000; i++) {
			sbs6.insert(i);
		}

		long startTime = System.nanoTime();

		int postion = sbs6.findIndex(80000);

		long endTime = System.nanoTime();

		System.out.println("sequentialFind() "+postion+" Time-consuming:" + (endTime - startTime));

		sbs6.usesBinarySearch = true;

		startTime = System.nanoTime();

		postion = sbs6.findIndex(80000);

		endTime = System.nanoTime();

		System.out.println("binaryFind() "+postion+" Time-consuming:" + (endTime - startTime));
	}

}
