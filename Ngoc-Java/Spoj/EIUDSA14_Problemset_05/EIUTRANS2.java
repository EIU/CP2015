import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class EIUTRANS2 {

	public static void main(String[] args) {
		// long s = System.currentTimeMillis();

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<WordMap> listWords = new ArrayList<WordMap>();
		for (int i = 0; i < m; i++) {
			String wordA = sc.next();
			String wordB = sc.next();
			if (wordA.length() > wordB.length()) {
				listWords.add(new WordMap(wordA, wordB));
			}
		}

		Collections.sort(listWords);

		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < n; i++) {
			String word = sc.next();
			int index = Collections.binarySearch(listWords, new WordMap(word, ""));
			if (index >= 0) {
				word = listWords.get(index).value;
			}
			bf.append(word + " ");
		}
		System.out.println(bf.toString());
		// System.out.println();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

	static class WordMap implements Comparable<WordMap> {
		public String key;
		public String value;

		public WordMap(String key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(WordMap other) {
			return key.compareTo(other.key);
		}
	}

}
