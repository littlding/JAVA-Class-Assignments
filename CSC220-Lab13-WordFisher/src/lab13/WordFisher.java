package lab13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordFisher {

	// Please note these variables. they are the state of the object.
	public HashMap<String, Integer> vocabulary;
	public List<String> stopwords; // User ArrayList for initialization
	private String inputTextFile;
	private String stopwordsFile;

	WordFisher(String inputTextFile, String stopwordsFile) {
		this.inputTextFile = inputTextFile;
		this.stopwordsFile = stopwordsFile;

		buildVocabulary();
		getStopwords();
	}

	public void buildVocabulary() {
		vocabulary = new HashMap<String, Integer>();

		// TODO: load in each word from inputTextFile into the vocabulary.
		// By the end of this method, vocabulary should map each word to the number of
		// times it occurs in inputTextFile.
		// Therefore, as you iterate over words, increase the value that the word maps
		// to in vocabulary by 1.
		// If it's not in the vocabulary, then add it with an occurrence of 1.
		// Use getStopwords as an example of reading from files.
		try {
			String reader = new String(Files.readAllBytes(Paths.get(this.inputTextFile)));
			reader = reader.toLowerCase();
			reader = reader.replaceAll("[^a-zA-Z0-9 ]", "");
			String[] allWords = reader.split("\\s+");
			for(String word:allWords){
				word = word.toLowerCase();
				if(vocabulary.containsKey(word)){
					vocabulary.put(word, vocabulary.get(word) + 1);
				}else{
					vocabulary.put(word, 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getStopwords() {
		stopwords = new ArrayList<String>();
		String word;

		try {
			BufferedReader input = new BufferedReader(new FileReader(stopwordsFile));
			while ((word = input.readLine()) != null) {
				stopwords.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWordCount() {
		// TODO: Return the total number of words in inputTextFile.
		// This can be calculated using vocabulary.
		int res = 0;
		for(String word:this.vocabulary.keySet()){
			res += this.vocabulary.get(word);
		}
		return res;
	}

	public int getNumUniqueWords() {
		// TODO: Return the number of unique words.
		// This should be the same as the number of keys in vocabulary.
		return this.vocabulary.keySet().size();
	}

	public int getFrequency(String word) {
		if(vocabulary.containsKey(word)){
			// TODO: Return the number of times word occurs. 
			// (Should be one simple line of code.)
			// Think about what vocabulary stores.
			return this.vocabulary.get(word);
		}
		
		return -1;
	}

	public void pruneVocabulary() {
		// TODO: remove stopwords from the vocabulary.
		for(String word:this.stopwords){
			if(this.vocabulary.containsKey(word)){
				this.vocabulary.remove(word);
			}
		}
	}

	class WordNode {
		private String word;
		private Integer count;
		public WordNode(String word, Integer count) {
			this.word = word;
			this.count = count;
		}

		public String getWord() {
			return word;
		}

		public Integer getCount() {
			return count;
		}
	}

	class WordNodeComparator implements Comparator<WordNode> {

		@Override
		public int compare(WordNode o1, WordNode o2) {
			return Integer.compare(o2.getCount(), o1.getCount());
		}
	}

	public ArrayList<String> getTopWords(int n) {
		ArrayList<String> topWords = new ArrayList<String>();
		
		// TODO: get the top n words.
		PriorityQueue<WordNode> que = new PriorityQueue<>(new WordNodeComparator());
		for(String word:this.vocabulary.keySet()) {
			que.add(new WordNode(word, this.vocabulary.get(word)));
		}
		for(int i=0;i<n&&!que.isEmpty();i++) {
			topWords.add(que.poll().getWord());
		}
		return topWords;
	}

	public ArrayList<String> commonPopularWords(int n, WordFisher other) {
		ArrayList<String> commonPopWords = new ArrayList<String>();
		
		// TODO: get the common popular words.
		ArrayList<String> topWords1 = this.getTopWords(n);
		ArrayList<String> topWords2 = other.getTopWords(n);

		for(String word:topWords1){
			if (topWords2.contains(word)){
				commonPopWords.add(word);
			}
		}

		return commonPopWords;
	}

}
