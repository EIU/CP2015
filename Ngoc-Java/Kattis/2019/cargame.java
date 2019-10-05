import java.io.*;
import java.util.*;

public class cargame {

	public static void main(String[] args) {
		int N = reader.nextInt();
		int M = reader.nextInt();

		String[] words = new String[N];

		@SuppressWarnings("unchecked")
		List<Byte>[][] charMaps = new ArrayList[N][26];
		for (int i = 0; i < N; i++) {
			String word = words[i] = reader.next();
			List<Byte>[] charMap = charMaps[i];
			for (byte j = 0; j < word.length(); j++) {
				byte index = (byte) (word.charAt(j) - 'a');
				if (charMap[index] == null) {
					charMap[index] = new ArrayList<Byte>();
				}
				charMap[index].add(j);
			}
		}

		StringBuilder outBf = new StringBuilder();
		for (int k = 0; k < M; k++) {
			char[] plate = reader.next().toCharArray();
			byte p0 = (byte) (plate[0] - 'A');
			byte p1 = (byte) (plate[1] - 'A');
			byte p2 = (byte) (plate[2] - 'A');
			int i = 0;
			for (i = 0; i < N; i++) {
				List<Byte>[] charMap = charMaps[i];
				List<Byte> list = charMap[p0];
				if (list == null) {
					continue;
				}
				byte f0 = list.get(0);
				list = charMap[p2];
				if (list == null) {
					continue;
				}
				byte f2 = list.get(list.size() - 1);
				if (f0 > f2) {
					continue;
				}
				list = charMap[p1];
				if (list == null) {
					continue;
				}
				byte f1 = 0;
				for (int j = 0; j < list.size(); j++) {
					f1 = list.get(j);
					if (f0 < f1) {
						break;
					}
				}
				if (f0 < f1 && f1 < f2) {
					break;
				}
				/*
				 * int index = Collections.binarySearch(list, (byte) (f0 + 1)); if (index < 0) {
				 * index = ~index; } byte f1 = index < list.size() ? list.get(index) : (byte)
				 * 0xFF; if (f0 < f1 && f1 < f2) { break; }
				 */
			}
			if (i < N) {
				outBf.append(words[i] + "\r\n");
			} else {
				outBf.append("No valid word\r\n");
			}
		}
		System.out.println(outBf);
	}

	static FastInputReader reader = new FastInputReader(System.in);

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 25];
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
