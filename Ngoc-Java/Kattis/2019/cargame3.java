import java.io.*;
import java.util.*;

public class cargame3 {

	public static void main(String[] args) {
		int N = reader.nextInt();
		int M = reader.nextInt();

		String[] words = new String[N];

		// long s = System.currentTimeMillis();

		byte[][] lastOccurs = new byte[N][26];
		byte[][][] pairOccurs = new byte[N][26][];
		for (int i = 0; i < N; i++) {
			String word = words[i] = reader.next();
			byte[] lastOccur = lastOccurs[i];
			byte[][] pairOccur = pairOccurs[i];
			for (byte j = 0; j < word.length(); j++) {
				byte index = (byte) (word.charAt(j) - 'a');
				lastOccur[index] = j;
				for (byte k = 0; k < 26; k++) {
					if (pairOccur[k] != null && pairOccur[k][index] == 0) {
						pairOccur[k][index] = j;
					}
				}
				if (pairOccur[index] == null) {
					pairOccur[index] = new byte[26];
				}
			}
		}

		// System.out.println(System.currentTimeMillis() - s + "ms");

		String[] results = new String[M];
		String noValid = "No valid word";
		for (int k = 0; k < M; k++) {
			char[] plate = reader.next(3);
			byte p0 = (byte) (plate[0] - 'A');
			byte p1 = (byte) (plate[1] - 'A');
			byte p2 = (byte) (plate[2] - 'A');
			int i = 0;
			for (i = 0; i < N; i++) {
				byte[] pairOccur = pairOccurs[i][p0];
				if (pairOccur != null && pairOccur[p1] > 0 && pairOccur[p1] < lastOccurs[i][p2]) {
					break;
				}
			}
			if (i < N) {
				results[k] = words[i];
			} else {
				results[k] = noValid;
			}
		}
		System.out.println(String.join("\r\n", results));
		// System.out.println(System.currentTimeMillis() - s + "ms");
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
