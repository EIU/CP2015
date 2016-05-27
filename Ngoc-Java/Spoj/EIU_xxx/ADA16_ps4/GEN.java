import java.io.FileWriter;

public class GEN {

	static int id = 16;

	public static void main(String[] args) throws Exception {
		//		gen_DP6(20, 0, 20);
		//		gen_DP6(20, 0, 20);
		//		gen_DP6(100, 0, 10);
		//		gen_DP6(100, 0, 50);
		//		gen_DP6(100, 100, 1000);
		//
		//		gen_DP6(1000, 0, 10);
		//		gen_DP6(1000, 0, 1000);
		//		gen_DP6(1000, 0, 10000000);
		//		gen_DP6(1000, 0, 1000000000);
		//
		//		gen_DP6(100000, 100000, 1000000000);
		//		gen_DP6(100000, 0, 1000000000);

		//		gen_DP6(1000000, 0, 1000);
		gen_DP6(1000000, 0, 1000);
		gen_DP6(1000000, 0, 1000);
	}

	static String randString(int minLen, int maxLen) {
		int len = (int) randBetween(minLen, maxLen);
		char[] chars = new char[len];
		for (int i = 0; i < len; i++) {
			chars[i] = (char) randBetween('a', 'z' + 1);
		}
		return new String(chars);
	}

	static long randBetween(long start, long end) {
		if (start == end) {
			end++;
		}
		return start + (long) Math.floor(Math.random() * (end - start - 0.000001));
	}

	static void appendInteger(int n, long min, long max, StringBuffer buffer) {
		for (int i = 0; i < n; i++) {
			buffer.append(randBetween(min, max) + " ");
		}
	}

	public static void gen_DP6(int n, long minRange, long maxRange) throws Exception {
		FileWriter in = new FileWriter(".\\bin\\" + (id++) + ".in");

		//int[] ks = new int[]{5, 10, n / 20, n / 5, n * 2 / 5};
		//int[] ks = new int[]{3, 10, n / 5};
		int[] ks = new int[]{(int) randBetween(n / 50, n / 30)};
		// int[] ks = new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
		// 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7,
		// 7, 7, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10};

		StringBuffer inBuffer = new StringBuffer();
		int T = ks.length;
		inBuffer.append(T + " \r\n");

		for (int i = 0; i < T; i++) {
			inBuffer.append(n + " " + ks[i] + "\r\n");
			// inBuffer.append(n + " " + 3 + "\r\n");
			appendInteger(n, minRange, maxRange, inBuffer);
			inBuffer.append("\r\n");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();
	}
}
