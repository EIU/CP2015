import java.io.*;
import java.util.*;

public class cargame2 {

	public static void main(String[] args) {
		int N = reader.nextInt();
		int M = reader.nextInt();

		long s = System.currentTimeMillis();
		String[] words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = reader.next();
		}

		byte[][] plates = new byte[M][3];
		for (int j = 0; j < M; j++) {
			char[] plate = reader.next(3);
			plates[j][0] = (byte) (plate[0] - 'A');
			plates[j][1] = (byte) (plate[1] - 'A');
			plates[j][2] = (byte) (plate[2] - 'A');
		}

		byte[] lastOccur = new byte[26];
		boolean[] hasFirst = new boolean[26];
		byte[] pairOccur = new byte[32 << 5];
		String[] outBf = new String[M];
		byte invalid = (byte) 127;
		int count = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(lastOccur, (byte) -1);
			Arrays.fill(hasFirst, false);
			for (int k = 0; k < 26; k++) {
				Arrays.fill(pairOccur, invalid);
			}
			String word = words[i];
			for (int j = 0; j < word.length(); j++) {
				int index = (word.charAt(j) - 'a');
				lastOccur[index] = (byte) j;
				for (int k = 0; k < 26; k++) {
					if (hasFirst[k] && pairOccur[(k << 5) + index] == invalid) {
						pairOccur[(k << 5) + index] = (byte) j;
					}
				}
				hasFirst[index] = true;
			}

			for (int j = 0; j < M; j++) {
				if (outBf[j] != null) {
					continue;
				}
				byte[] plate = plates[j];
				count++;
				if (pairOccur[(plate[0] << 5) + plate[1]] < lastOccur[plate[2]]) {
					outBf[j] = word;
				}
			}
		}

		String invalidWord = "No valid word";
		for (int j = 0; j < M; j++) {
			if (outBf[j] == null) {
				outBf[j] = invalidWord;
			}
		}

		// System.out.println(String.join("\r\n", results));
		System.out.println(System.currentTimeMillis() - s + "ms" + count);
	}

	static FastInputReader reader = new FastInputReader(System.in);

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 20];
		int lenbuf = 0, ptrbuf = 0;
		InputStream is;

		public FastInputReader(InputStream stream) {
			is = stream;
		}

		int readByte() {
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

		public boolean hasNext() {
			return ptrbuf + 3 < lenbuf;
		}

		boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public char[] next(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return (char) skip();
		}

		public int nextInt() {
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

		long nextLong() {
			long num = 0;
			int b;
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
	}
}
