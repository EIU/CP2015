import java.io.IOException;
import java.util.*;

public class Stretching {

	final static int MAX = 1000;

	static int charLen;
	static byte[] chars;

	public static void main(String[] args) throws IOException {
		chars = new byte[MAX];
		charLen = System.in.read(chars);
		while (chars[charLen - 1] < 'a' || chars[charLen - 1] > 'z') {
			charLen--;
		}

		HashSet<String> checkedSet = new HashSet<String>();

		for (int subLen = 1; subLen <= charLen; subLen++) {
			if (charLen % subLen != 0) {
				continue;
			}
			for (int i = 0; i <= charLen - subLen; i++) {
				byte[] subChars = Arrays.copyOfRange(chars, i, i + subLen);
				String subString = new String(subChars);
				// || subString.equals("hello")
				if (!checkedSet.contains(subString)) {
					checkedSet.add(subString);
					if (check(subChars)) {
						System.out.println(new String(subChars));
						return;
					}
				}
			}
		}
	}

	static boolean check(byte[] subChars) {
		int subLen = subChars.length;

		@SuppressWarnings({"unchecked"})
		ArrayList<Match>[] matches = new ArrayList[charLen];
		for (int i = 0; i < charLen; i++) {
			matches[i] = new ArrayList<Match>();
		}

		ArrayList<Match> empty = new ArrayList<Match>();

		for (int i = 0; i < charLen; i++) {
			ArrayList<Match> preMatches = i > 0 ? matches[i - 1] : empty;
			boolean addFirst = false;
			for (Match preMatch : preMatches) {
				int currentPos = (preMatch.pos + 1 < subLen) ? (preMatch.pos + 1) : 0;
				if (chars[i] == subChars[currentPos]) {
					if (currentPos + 1 < subLen) {
						matches[i].add(new Match(currentPos, preMatch.len + 1));
						if (currentPos == 0) {
							addFirst = true;
						}
					} else if (i - preMatch.len - 1 >= 0) {
						for (Match prePre : matches[i - preMatch.len - 1]) {
							matches[i].add(new Match(prePre.pos, preMatch.len + prePre.len + 1));
						}
					} else {
						matches[i].add(new Match(subLen - 1, i + 1));
					}
				}
			}
			if (!addFirst && chars[i] == subChars[0]) {
				matches[i].add(new Match(0, 1));
			}
			if (matches[i].size() == 0) {
				return false;
			}
		}

		for (Match match : matches[charLen - 1]) {
			if (match.len == charLen) {
				return true;
			}
		}
		return false;
	}

	static class Match {
		public int pos;
		public int len;

		public Match(int pos, int len) {
			this.pos = pos;
			this.len = len;
		}
	}
}

/* letters = ['a','b','c','d','e']; function gen(n) {var s = ''; for(var i=0;i<n;i++) s += letters[Math.floor(Math.random()*letters.length)]; return s;}; 
 * p = text = gen(4); for(i=0;i<10;i++) {rand = Math.round(Math.random()*text.length); text = text.substring(0,rand) + p + text.substring(rand,text.length);}
 * */
