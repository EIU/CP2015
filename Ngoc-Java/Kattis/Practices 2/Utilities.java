import java.io.*;
import java.util.*;

public class Utilities {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "2 2";

	public static void main(String[] args) {
		oj = true;
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		//		long s = System.currentTimeMillis();
		//		int T = ni();
		//		out.flush();
		//		tr(System.currentTimeMillis() - s + "ms");

		testTreeSet();
	}

	static void testPriorityQueue() {
		PriorityQueue<Integer> map = new PriorityQueue<>();
		int MAX = 2000000;
		Random rand = new Random();

		long s = System.currentTimeMillis();

		for (int i = 0; i < MAX; i++) {
			map.add(rand.nextInt());
		}

		System.out.println((System.currentTimeMillis() - s) + " ms");

		long total = 0;
		for (int i = 0; i < MAX; i++) {
			total += map.poll();
		}

		System.out.println((System.currentTimeMillis() - s) + " ms " + total);

	}

	static void testTreeSet() {
		TreeSet<Integer> map = new TreeSet<>();
		int MAX = 2000000;
		Random rand = new Random();

		long s = System.currentTimeMillis();

		for (int i = 0; i < MAX; i++) {
			map.add(rand.nextInt(Integer.MAX_VALUE));
		}

		System.out.println((System.currentTimeMillis() - s) + " ms");

		long total = 0;
		for (int i = 0; i < MAX; i++) {
			total += map.contains(rand.nextInt(Integer.MAX_VALUE)) ? 1 : 0;
		}

		System.out.println((System.currentTimeMillis() - s) + " ms " + total + " " + map.first() + " " + map.last());
	}

	static void testTreeMap() {
		TreeMap<Integer, Boolean> map = new TreeMap<>();
		int MAX = 2000000;
		Random rand = new Random();

		long s = System.currentTimeMillis();

		for (int i = 0; i < MAX; i++) {
			map.put(rand.nextInt(Integer.MAX_VALUE), true);
		}

		System.out.println((System.currentTimeMillis() - s) + " ms");

		long total = 0;
		for (int i = 0; i < MAX; i++) {
			total += map.containsKey(rand.nextInt(Integer.MAX_VALUE)) ? 1 : 0;
		}

		System.out.println((System.currentTimeMillis() - s) + " ms " + total);
	}

	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	static long gcd2(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	static int MAX = 200001;
	static int[] maxBIT = new int[MAX];

	static void setValue(int i, int value) {
		for (; i < MAX; i += (i & -i)) {
			maxBIT[i] += value;
		}
	}

	static int getValue(int i) {
		int sum = 0;
		for (; i > 0; i -= (i & -i)) {
			sum += maxBIT[i];
		}
		return sum;
	}

	static class SumBIT {
		int size;
		long[] sums;

		public SumBIT(int n) {
			size = n;
			sums = new long[n + 1];
		}

		public void set(int i, long value) {
			for (; i <= size; i += (i & -i)) {
				sums[i] += value;
			}
		}

		public long get(int i) {
			long sum = 0;
			for (; i > 0; i -= (i & -i)) {
				sum += sums[i];
			}
			return sum;
		}
	}

	/* ****************************************************************
	 ******************** BASIC READER *******************************
	 **************************************************************** */

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
		while ((b = readByte()) != -1 && isSpaceChar(b));
		return b;
	}

	static double nd() {
		return Double.parseDouble(ns());
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

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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

	static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}

// DFS/BFS:

// BIT: 

// Segment Tree: 

// Tree Set/Map: 

// Dijkstra: VisuAlgoOnline

// Kruskal: Minimum Spanning Tree

// DP: Exact Change

// Hash: SoftwareBugs, PowerStrings

// Hash: ?
/* 
static final long BASE = 256;
static final long MOD = 1000000009;
static void search(char[] pattern, char[] text)
{
	int pLen = pattern.length;
	int tLen = text.length;
	long maxPow = 1;

	for (int i = 0; i < pLen - 1; i++) {
		maxPow = (maxPow * BASE) % MOD;
	}

	long pHash = 0;
	long tHash = 0;
	for (int i = 0; i < pLen; i++) {
		pHash = (BASE * pHash + pattern[i]) % MOD;
		tHash = (BASE * tHash + text[i]) % MOD;
	}

	for (int i = 0; i <= tLen - pLen; i++) {
		if (pHash == tHash) {
			int j = 0;
			for (; j < pLen; j++) {
				if (text[i + j] != pattern[j]) {
					break;
				}
			}
			if (j == pLen) {
				System.out.println("Pattern found at index " + i);
			}
		}

		if (i < tLen - pLen) {
			tHash = (BASE * (tHash - text[i] * maxPow) + text[i + pLen]) % MOD;
			if (tHash < 0) {
				tHash = (tHash + MOD);
			}
		}
	}
}
// */