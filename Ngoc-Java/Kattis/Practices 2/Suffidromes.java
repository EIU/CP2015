import java.util.*;

class Suffidromes {

	public static void main(String[] args) throws Exception {
		byte[] inbuf = new byte[1 << 24];
		int lenbuf = System.in.read(inbuf);

		List<char[]> list = new ArrayList<char[]>();
		StringBuilder cur = new StringBuilder();
		for (int i = 0; i < lenbuf; i++) {
			if (inbuf[i] != '\n') {
				if (inbuf[i] != '\r') {
					cur.appendCodePoint(inbuf[i]);
				}
			} else {
				list.add(cur.toString().toCharArray());
				cur = new StringBuilder();
			}
		}

		for (int i = 0; i < list.size(); i += 2) {
			solve(list.get(i), list.get(i + 1));
		}
	}

	static void solve(char[] s1, char[] s2) {
		if (s2.length < s1.length) {
			char[] temp = s1;
			s1 = s2;
			s2 = temp;
		}

		int nCommon = 0;
		while (nCommon < s1.length && nCommon < s2.length && s1[nCommon] == s2[nCommon]) {
			nCommon++;
		}

		List<Integer> pos1 = palidromePositions(s1);
		List<Integer> pos2 = palidromePositions(s2);

		int i = 0, j = 0;
		while (i < pos1.size() && j < pos2.size()) {
			int p1 = pos1.get(i);
			int p2 = pos2.get(j);
			if (p1 != p2 || p1 > nCommon) {
				String res1 = revert(s1, 0, p1);
				String res2 = revert(s2, 0, p2);
				if (p1 < p2 || p1 == p2 && res1.compareTo(res2) < 0) {
					System.out.println(res1);
				} else {
					System.out.println(res2);
				}
				return;
			} else {
				i++;
				j++;
			}
		}

		if (s1.length < s2.length) { // nCommon == s1.length
			char head = s2[s1.length] == 'a' ? 'b' : 'a';
			System.out.println(head + revert(s1, 0, s1.length));
			return;
		}

		// s1.length == s2.length
		System.out.println("No solution.");
	}

	static String revert(String s) {
		return new StringBuilder(s).reverse().toString();
	}

	static String revert(char[] s, int from, int to) {
		return revert(new String(s, from, to));
	}

	static List<Integer> palidromePositions(char[] s) {
		List<Integer> list = new ArrayList<Integer>();
		int sLen = s.length;
		for (int i = 0; i < sLen; i++) {
			int j = 0;
			int midLen = (sLen - i) / 2;
			while (j <= midLen && s[i + j] == s[sLen - 1 - j]) {
				j++;
			}
			if (j > midLen) {
				list.add(i);
			}
		}
		list.add(sLen);
		return list;
	}
}
