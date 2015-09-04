import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class AutocompleteStrikesBack2 {
	static InputStream is;
	static PrintWriter out;
	// static String INPUT = "5 6 4 tin tiny tinny gigantic tilt tilts 3 2 apple apricot cherry 5 3 a aa aaa aaaa aaaaa 5 3 the quick brown fox jumped 8 7 cork work card ward font front word sword ";
	static String INPUT = "5 6 4 tin tiny tinny gigantic tilt tilts 3 2 apple apricot cherry 5 3 a aa aaa aaaa aaaaa 5 3 the quick brown fox jumped 8 7 cork work card ward font front word sword ";

	// static String INPUT = "1 6 4 tin tiny tinny gigantic tilt tilts";

	public static void main(String[] args) throws Exception {
		oj = false;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	static void solve() {
		int T = ni();
		for (int t = 0; t < T; t++) {
			int N = ni();
			int K = ni();
			String[] words = new String[N];
			for (int i = 0; i < N; i++) {
				words[i] = ns() + "\2";
			}
			Arrays.fill(selectedLevels, 0);
			Arrays.fill(selectedChars, 0);
			parseRange(words, 0, N, 0, 0);
			int result = findSelected(K);

			System.out.println("Case #" + (t + 1) + ": " + result);
		}
	}

	static final int MAX = 20000;
	static final int[] selectedLevels = new int[MAX];
	static final int[] selectedChars = new int[MAX];

	static void parseRange(String[] words, int start, int end, int level, int preLeveled) {
		int j = start;
		int nNode = 0;
		for (int i = start + 1; i < end; i++) {
			if (words[i].charAt(level) != words[i - 1].charAt(level)) {
				nNode++;
				if (j + 1 < i) {
					parseRange(words, j, i, level + 1, level);
				}
				j = i;
			}
		}
		if (j > start) {
			selectedLevels[level] += nNode - 1;
			selectedChars[level] += level - preLeveled;
		}
		if (j + 1 < end) {
			parseRange(words, j, end, level + 1, j == start ? preLeveled : level);
		}
	}

	static int findSelected(int K) {
		int result = 0;
		int selected = 0;
		for (int i = 0; i < MAX; i++) {
			if (selected + selectedLevels[i] >= K) {
				result += selectedChars[i] + (K - selected) * i;
				break;
			}
			else {
				result += selectedChars[i] + selectedLevels[i] * i;
			}
		}
		return result;
	}

	/**
	 * @Solution: Let figure out the sorted words form a tries, with root has value "\1"
	 * @Solution: This algorithm do BFS through all nodes => each while loop traverse all nodes of one level
	 * @Define: single-node is node of one one word
	 * @Define: multi-node is node of more than one words
	 * @Solution: at each level we will select single nodes (word), and calculate how many chars we need to type to achieve them
	 * @Solution: --> then re-arrange remain multi-nodes (words)
	 */
	static int solveOne(String[] words, int K) {
		Arrays.sort(words);
		int lastIndex = words.length;
		int level = 1, selectedWord = 0, selectedChar = 0;

		while (selectedWord < K) { // lastIndex > 0
			int curIndex = 0, nSingle = 0, nMulti = 0, nEmpty = 0, count = 1;
			for (int i = 1; i <= lastIndex; i++) {
				String preWord = words[i - 1];
				if (i < lastIndex) {
					String curWord = words[i];
					if (preWord.charAt(level - 1) == curWord.charAt(level - 1)
							&& preWord.charAt(level) == curWord.charAt(level)) {
						count++;
						words[curIndex++] = words[i - 1];
						continue;
					}
				}
				if (count == 1) {
					if (preWord.length() == level + 1) {
						nEmpty++;
					}
					nSingle++;
				}
				else {
					nMulti++;
					words[curIndex++] = words[i - 1];
				}
				count = 1;
			}
			if (selectedWord + nSingle + nMulti >= K) {
				return selectedChar + (K - selectedWord) * level - nEmpty;
			}
			else {
				selectedChar += nSingle * level - nEmpty;
				selectedWord += nSingle;
			}
			level++;
			lastIndex = curIndex;
		}
		return words.length; // Never happen!
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/
	static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static char nc() {
		return (char) skip();
	}

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		// if (!oj) {
		System.out.println(Arrays.deepToString(o));
		// }
	}
}
