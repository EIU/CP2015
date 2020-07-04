import java.util.*;

public class TheResistance {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] L = in.next().toCharArray();
		int N = in.nextInt();

		int maxLen = 80;
		ArrayList<ArrayList<String>> wordSets = new ArrayList<ArrayList<String>>();
		for (int i = 0; i <= maxLen; i++) {
			wordSets.add(new ArrayList<String>());
		}

		for (int i = 0; i < N; i++) {
			String word = convertMorse(in.next());
			wordSets.get(word.length()).add(word);
		}

		for (int i = 0; i < maxLen; i++) {
			wordSets.get(i).sort((w1, w2) -> w1.compareTo(w2));
		}

		long[] counts = new long[L.length + 1];
		counts[0] = 1;
		for (int i = 0; i < L.length; i++) {
			String word = "";
			for (int j = 1; j <= maxLen && i + j - 1 < L.length; j++) {
				String preWord = word;
				char nextChar = L[i + j - 1];
				word += nextChar;
				ArrayList<String> wordSet = wordSets.get(j);
				if (wordSet.size() == 0) {
					continue;
				}
				int index = Collections.binarySearch(wordSet, word);
				if (index >= 0) {
					int nextIndex = ~Collections.binarySearch(wordSet, word + 'A');
					if (nextChar == '.') {
						preWord += "-A";
					} else if (nextChar == '-') {
						preWord += "+A";
					}
					int preIndex = ~Collections.binarySearch(wordSet, preWord);
					counts[i + j] += counts[i] * (nextIndex - preIndex);
				}
			}
		}

		System.out.println(counts[L.length]);
	}

	static String[] mapMorse = new String[] { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
			"--.." };

	static String convertMorse(String text) {
		StringBuilder result = new StringBuilder();
		for (char c : text.toCharArray()) {
			result.append(mapMorse[c - 'A']);
		}
		return result.toString();
	}
}
