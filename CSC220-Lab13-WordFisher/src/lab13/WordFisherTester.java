package lab13;

public class WordFisherTester {
	
	public static void main(String[] args) {
		
		WordFisher alice = new WordFisher("texts/carroll-alice.txt", "stopwords.txt");
		
		WordFisher moby = new WordFisher("texts/moby-dick.txt", "stopwords.txt");

		System.out.println("Total Words (Moby Dick): " + moby.getWordCount());
		System.out.println("Unique Words (Moby Dick): " + moby.getNumUniqueWords());
		System.out.println("Total Words (Alice): " + alice.getWordCount());
		System.out.println("Unique Words (Alice): " + alice.getNumUniqueWords());

		System.out.println("Frequency (whale) : " + moby.getFrequency("whale"));

		moby.pruneVocabulary();
		alice.pruneVocabulary();

		System.out.println("Total Words (Moby Dick) after pruning: " + moby.getWordCount());
		System.out.println("Unique Words (Moby Dick) after pruning: " + moby.getNumUniqueWords());
		System.out.println("Total Words (Alice) after pruning: " + alice.getWordCount());
		System.out.println("Unique Words (Alice) after pruning: " + alice.getNumUniqueWords());

		System.out.println("Top 10 words: " + moby.getTopWords(10));

		System.out.println("Top 20 common popular words: " + alice.commonPopularWords(20, moby));
	}
}
