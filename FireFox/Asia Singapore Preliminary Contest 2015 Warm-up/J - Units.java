import java.io.*;
import java.util.*;

public class Main {
	static boolean isMultiTest = false;

	public static void main(String[] args) {
		OutputStream outputStream = System.out;
		InputStream inputStream = System.in;
		InputFast in = new InputFast();
		// Input in = new Input(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		if (isMultiTest) {
			int testNumber = in.nextInt();
			for (int i = 1; i <= testNumber; i++) {
				solver.solve(i, in, out);
			}
		} else {
			solver.solve(1, in, out);
		}
		out.close();
	}
}

class Task {
	final static int MOD = 1000000009;
	final int LIM = 1000001;

	public void solve(int testNumber, InputFast in, PrintWriter out) {
		int n = 0;
		while ((n = in.nextInt()) != 0) {
			String[] units = new String[n];
			for (int i = 0; i < n; i++)
				units[i] = in.next();
			HashMap<String, TreeSet<Convert>> m = new HashMap<String, TreeSet<Convert>>();
			for (int i = 0; i < n - 1; i++) {
				String fromUnit = in.next();
				in.next();
				Convert c = new Convert(in.nextInt(), in.next());
				TreeSet<Convert> l = m.get(fromUnit);
				if (l != null) {
					l.add(c);
					m.put(fromUnit, l);
				} else {
					TreeSet<Convert> tmp = new TreeSet<>();
					tmp.add(c);
					m.put(fromUnit, tmp);
				}
			}

			for (int time = 0; time < 100; time++)
				for (int i = 0; i < n; i++) {
					String key = units[i];
					TreeSet<Convert> canConvert = m.get(key);
					if (canConvert != null) {
						for (Convert to : canConvert) {
							for (Convert from : canConvert) {
								if (to.w > from.w) {
									int k = to.w / from.w;
									String fu = from.unit;
									String tu = to.unit;
									Convert c = new Convert(k, tu);
									TreeSet<Convert> l = m.get(fu);
									if (l != null) {
										l.add(c);
										m.put(fu, l);
									} else {
										TreeSet<Convert> tmp = new TreeSet<>();
										tmp.add(c);
										m.put(fu, tmp);
									}
								}
							}
						}
					}
				}

			int[] last = new int[n];
			for (int i = 0; i < n; i++) {
				String cur = units[i];
				int val = 1;
				TreeSet<Convert> t = m.get(cur);
				if (t != null) {
					Convert f = t.first();
					while (!cur.equals(f.unit)) {
						val *= f.w;
						cur = f.unit;
						t = m.get(cur);
						if (t == null)
							break;
						f = t.first();
					}
					last[i] = val;
				} else {
					last[i] = 1;
				}
				// System.out.println(last[i]);
			}
			Unit[] u = new Unit[n];
			for (int i = 0; i < n; i++) {
				u[i] = new Unit(last[i], units[i]);
			}
			Arrays.sort(u);
			out.print(1 + "" + u[0].unit);
			for (int i = 1; i < n; i++) {
				out.print(" = " + u[0].v / u[i].v + u[i].unit);
			}
			out.println();
		}
	}

	class Unit implements Comparable<Unit> {
		String unit;
		int v;

		public Unit(int v, String unit) {
			this.v = v;
			this.unit = unit;
		}

		@Override
		public int compareTo(Unit o) {
			return o.v - this.v;
		}
	}

	class Convert implements Comparable<Convert> {
		int w;
		String unit;

		public Convert(int w, String unit) {
			this.w = w;
			this.unit = unit;
		}

		@Override
		public int compareTo(Convert o) {
			return this.w - o.w;
		}

	}
}

class InputFast {
	static InputStream is = System.in;
	static private byte[] buffer = new byte[1024];
	static private int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(buffer);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return buffer[ptrbuf++];
	}

	private boolean isSpace(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int read() {
		int b;
		while ((b = readByte()) != -1 && isSpace(b))
			;
		return b;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public char nextChar() {
		return (char) read();
	}

	public String next() {
		int b = read();
		StringBuilder sb = new StringBuilder();
		while (!(isSpace(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public char[] next(int n) {
		char[] buf = new char[n];
		int b = read(), p = 0;
		while (p < n && !(isSpace(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
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

	public long nextLong() {
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

class Input {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public Input(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public String nextLine() {
		String s = "";
		try {
			s = reader.readLine();
		} catch (IOException e) {
			try {
				throw new IOException();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return s;
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}
