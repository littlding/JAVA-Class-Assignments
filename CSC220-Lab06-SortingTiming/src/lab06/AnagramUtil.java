package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AnagramUtil {

    // Reads words from a file (assumed to contain one word per line)
    // Returns the words as an array of strings.
    public static SortedString[] readFile(String filename)
    {
        ArrayList<SortedString> results = new ArrayList<SortedString>();
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while(input.ready())
            {
            	String content = input.readLine();
                results.add(new SortedString(content));
            }
            input.close();
        }
        catch(Exception e)
        {e.printStackTrace();}
        return results.toArray(new SortedString[0]);
    }

    public static String[] getLargestAnagramGroup(String filename){
        SortedString[] words = readFile(filename);
        String[] toReturn = getLargestAnagramGroup(words);
        return toReturn;
    }

    public static String[] getLargestAnagramGroup(SortedString[] stringList){

        /* Initialize a sorting algorithm of type SortedString using either
         MergeSort or InsertionSort */
    	InsertionSort<SortedString> insertionSort = new InsertionSort<SortedString>();
    	//MergeSort<SortedString> insertionSort = new MergeSort<SortedString>();

        /* sort the input string list */
    	stringList = insertionSort.sort(stringList);

        /* The case where stringList is of size 1 or less */
    	if(stringList.length <= 1) {
    		String[] result = new String[stringList.length];
    		for(int i=0;i<stringList.length;i++) {
    			result[i] = stringList[i].getUnsorted();
    		}
    		return result;
    	}


        /* The variables for the logic following */
        int end = 0, length = 1, i = 0, maxLength = 0;
        /* Loop through stringList.

           If stringList[i] and stringList[i + 1] are anagrams, increment the
           length of this anagram group.

           Otherwise, check to see if the length of this anagram group is longer
           than the currently known maximum group length. Update variables
           accordingly (see end, length, i, and MaxLength).

        */
        
        for(;i<stringList.length-1;i++) {
        	if(stringList[i].compareTo(stringList[i+1]) == 0) {
        		length++;
        		if(length > maxLength) {
        			end = i+1;
        			maxLength = length;
        			
        			/* Don't forget one last check for the end:
        	           if the longest list is the last group.
        	           As before, update variables accordingly.
        	        */
        			if((i+1) == (stringList.length-1)) {
        				end = i+1;
        			}
        		}
        	}else {
        		if(length > maxLength) {
        			end = i;
        		}
        		length = 1;
        	}
        }

        /* Prepare to return. The following is almost the answer except
           for one thing...
        */
        String[] toReturn = new String[maxLength];
        for (int j = 0; j < maxLength; j++)
            toReturn[j] = stringList[j+end-maxLength+1].getUnsorted();

        return toReturn; // return the right thing
    }

    public static boolean areAnagrams(SortedString str1, SortedString str2){
    	
         return str1.compareTo(str2) == 0;

}
}
