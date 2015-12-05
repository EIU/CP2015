import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinaryTree {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 25);
		int T = Integer.parseInt(reader.readLine());

		for (int t = 0; t < T; t++) {
			solve(t + 1, reader.readLine(), reader.readLine());
		}
	}

	static final int MOD = 21092013;

	static void solve(int testId, String s, String t) {

		int tLen = t.length();
		int[] counts = new int[tLen + 1];
		int[] countLefts = new int[tLen + 1];
		int[] countRights = new int[tLen + 1];

		int nextLeft = tLen;
		int nextRight = tLen;

		for (int i = tLen - 1; i >= 0; i--) {
			countLefts[i] = counts[nextLeft];
			countRights[i] = counts[nextRight];
			counts[i] = (1 + counts[nextLeft] + counts[nextRight]) % MOD;
			char c = t.charAt(i);
			if (c == 'L') {
				nextLeft = i;
			}
			if (c == 'R') {
				nextRight = i;
			}
		}

		long result = 0;
		result = 1 + counts[nextLeft] + counts[nextRight];
		int sPos = s.length() - 1;
		for (int i = 0; i < tLen && sPos >= 0; i++) {
			if (t.charAt(i) != 'U') {
				continue;
			}
			int sU = 0;
			while (sPos >= 0 && (s.charAt(sPos) == 'U' || sU > 0)) {
				if (s.charAt(sPos) == 'U') {
					sU++;
				} else {
					sU--;
				}
				sPos--;
			}
			if (sPos >= 0) {
				if (s.charAt(sPos) == 'L') {
					result += countRights[i] + 1;
					sPos--;
				}
				else {
					result += countLefts[i] + 1;
					sPos--;
				}
			}
		}
		result %= MOD;

		System.out.println("Case " + testId + ": " + result);
	}
}
