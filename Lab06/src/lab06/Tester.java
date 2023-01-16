package lab06;

import java.util.Arrays;

public class Tester {
	public static void main(String[] args) {
		SortedString zeb = new SortedString("zebra");
		System.out.println("SortedString zebra: " + zeb);

		SortedString joy = new SortedString("joy");
		SortedString ski = new SortedString("ski");
		SortedString bel = new SortedString("below");
		SortedString elb = new SortedString("elbow");
		SortedString Elb = new SortedString("Elbow");

		System.out.println("joy.compareTo(ski): " + joy.compareTo(ski));
		System.out.println("below.compareTo(Elbow): " + bel.compareTo(Elb));
		System.out.println("below.compareTo(elbow): " + bel.compareTo(elb));
		System.out.println("zebra.compareTo(ski): " + zeb.compareTo(ski));
		System.out.println("-------------------------------------------");

		SortedString empty = new SortedString("");
		System.out.println("AnagramUtil.areAnagrams(, zebra):" + AnagramUtil.areAnagrams(empty, zeb));// f
		System.out.println("AnagramUtil.areAnagrams(zebra, zebra):" + AnagramUtil.areAnagrams(zeb, zeb));// t
		System.out.println("AnagramUtil.areAnagrams(below, elbow):" + AnagramUtil.areAnagrams(bel, elb));// t
		System.out.println("AnagramUtil.areAnagrams(zebra, elbow):" + AnagramUtil.areAnagrams(zeb, elb));// f
		System.out.println("AnagramUtil.areAnagrams(below, Elbow):" + AnagramUtil.areAnagrams(bel, Elb));// t

		System.out.println("-------------------------------------------");
		InsertionSort<Integer> intIS = new InsertionSort<Integer>();
		Integer[] one = { 1 };
		Integer[] two = { 8, 7 };
		Integer[] alreadySorted = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Integer[] random = new Integer[10];
		for (int i = 0; i < random.length; i++) {
			random[i] = (int) (Math.random() * 1000);

		}
		Integer[] sortedInts = intIS.sort(one);
		String str = "[";
		for (Integer i : sortedInts) {
			str += i + " ";
		}
		str += "]";
		System.out.println("Sorted one: " + str);

		sortedInts = intIS.sort(two);
		str = "[";
		for (Integer i : sortedInts) {
			str = str + i + " ";
		}
		str += "]";
		System.out.println("Sorted two: " + str);

		sortedInts = intIS.sort(alreadySorted);
		str = "[";
		for (Integer i : sortedInts) {
			str += i + " ";
		}
		str += "]";
		System.out.println("Sorted alreadySorted: " + str);

		sortedInts = intIS.sort(random);
		str = "[";
		for (Integer i : sortedInts) {
			str += i + " ";
		}
		str += "]";
		System.out.println("Sorted random: " + str);

		InsertionSort<SortedString> sortedStringIS = new InsertionSort<SortedString>();
		SortedString[] sortedStrings = { new SortedString("joy"), new SortedString("ski"), new SortedString("fed"),
				new SortedString("cat") };

		SortedString[] sortedStrings2 = sortedStringIS.sort(sortedStrings);

		str = "[";
		for (SortedString i : sortedStrings2) {
			str += i.getUnsorted() + " ";
		}
		str += "]";
		System.out.println("Sorted SortedString: " + str);

		System.out.println("-------------------------------------------");

		Integer[] longRandom = new Integer[100000];
		for (int i = 0; i < longRandom.length; i++) {
			longRandom[i] = (int) (Math.random() * 1000000);
		}

		intIS.fit(longRandom);

		System.out.println("InsertionSort will take " + intIS.predict(longRandom.length)
				+ " microsecond to sort an array of 100000 elements");

		System.out.println("-------------------------------------------");

		String[] s3 = AnagramUtil.getLargestAnagramGroup("sample-word-list.txt");
		System.out.println("AnagramUtil.getLargestAnagramGroup"+Arrays.toString(s3));
		
		System.out.println("-------------------------------------------");
		
		MergeSort<Integer> mergeSort = new MergeSort<Integer>();
		Integer[] unAlreadySorted = { 7, 3, 1, 6, 2 };
		sortedInts = mergeSort.sort(unAlreadySorted);
		
		sortedInts = mergeSort.sort(one);
		str = "[";
		for (Integer i : sortedInts) {
			str += i + " ";
		}
		str += "]";
		System.out.println("MergeSorted one: " + str);

		sortedInts = mergeSort.sort(two);
		str = "[";
		for (Integer i : sortedInts) {
			str = str + i + " ";
		}
		str += "]";
		System.out.println("MergeSorted two: " + str);

		sortedInts = mergeSort.sort(alreadySorted);
		str = "[";
		for (Integer i : sortedInts) {
			str += i + " ";
		}
		str += "]";
		System.out.println("MergeSorted alreadySorted: " + str);

		sortedInts = mergeSort.sort(random);
		str = "[";
		for (Integer i : sortedInts) {
			str += i + " ";
		}
		str += "]";
		System.out.println("MergeSorted random: " + str);

		MergeSort<SortedString> mergeSort2 = new MergeSort<SortedString>();

		SortedString[] sortedStrings3 = mergeSort2.sort(sortedStrings);

		str = "[";
		for (SortedString i : sortedStrings3) {
			str += i.getUnsorted() + " ";
		}
		str += "]";
		System.out.println("MergeSorted SortedString: " + str);

		System.out.println("-------------------------------------------");
		

		mergeSort.fit(longRandom);

		System.out.println("MergeSort will take " + mergeSort.predict(longRandom.length)
				+ " microsecond to sort an array of 100000 elements");

		System.out.println("-------------------------------------------");
	}

}
