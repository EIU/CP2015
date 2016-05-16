import java.util.*;
import java.io.*;

public class Main_Phuc {
	static boolean readFile = true;

	public static void main(String[] args) throws IOException {
		InputStream inputStream;
		OutputStream outputStream;
		Task solver = new Task();
		if (readFile) {
			solver.writeTestCases(new PrintWriter(new FileOutputStream("0.INP")));
			inputStream = new FileInputStream("0.INP");
			outputStream = new FileOutputStream("0.OUT");
		} else {
			inputStream = System.in;
			outputStream = System.out;
		}
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);

		solver.solve(in, out);
		out.close();
	}
}

class Task {

	public void writeTestCases(PrintWriter out) {
		out.println(10);
		out.println("3 100\r\r\n2 3\r\n1 10\r\n3 20");
		out.println("5 10\r\n2 3\r\n1 20\r\n3 20\r\n30 100\r\n30 500");
		out.println("5 1050\r\n2 3\r\n1 20\r\n3 20\r\n20 100\r\n30 500");
		out.println("5 250000\r\n2 3\r\n1 20\r\n2 20\r\n1000 100\r\n30 500");
		out.println("3 1000000000000\r\n1 3\r\n1 20\r\n100000000 20");
		writeTestCase(out, 10, 1000, 2, 500, 10);
		writeTestCase(out, 1000, 1000l * 1000 * 1000, 500, 1000000000, 1000);
		writeTestCase(out, 300, 0, 1, 1000000000, 300);
		writeTestCase(out, 1700, 1000l * 1000 * 1000 * 1000, 1, 1000000000, 1700);
		//writeTestCase(out, 100000, 1000l * 1000 * 1000 * 1000, 1, 100000000, 5);
		writeTestCase(out, 90000, 1000l * 1000 * 1000 * 1000, 1, 10000, 8000);
		out.close();

		//out.println(1);
		//writeTestCase(out, 90000, 1000l * 1000 * 1000 * 1000, 1, 1000);
		out.close();
	}

	private void writeTestCase(PrintWriter out, int n, long m, int min, int max, int invPerUnit) {
		out.println(n + " " + m);
		for (int i = 0; i < n; ++i) {
			out.println((min + nxL(max - min + 1)) + " " + (nxL(invPerUnit) + 1));
		}
	}

	private long nxL(long max) {
		return Math.abs(new Random().nextLong()) % max;
	}

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int t = in.nextInt();
		while (t-- > 0) {
			solveTestCase(in, out);
		}
	}

	public void solveTestCase(InputReader in, PrintWriter out) {
		int n = in.nextInt();
		long m = in.nextLong();
		Map<Long, Long> costs = new HashMap<Long, Long>();
		List<Long> ps = new ArrayList<Long>();
		for (int i = 0; i < n; ++i) {
			long p = in.nextLong();
			Long c = in.nextLong();
			Long total = costs.get(p);
			if (total == null) {
				total = c;
				ps.add(p);
			} else {
				total += c;
			}
			costs.put(p, total);
		}
		ps.add(Long.MAX_VALUE);
		Collections.sort(ps);
		int index = 0;
		long need = 0;
		if (m == 0) {
			out.println(ps.get(0));
			return;
		}
		while (m > 0) {
			long current = ps.get(index);
			need += costs.get(current);
			long nextP = ps.get(index + 1);
			long max = m / need;

			if (max > nextP - current) {
				m -= (nextP - current) * need;
			} else {
				out.println(current + max);
				break;
			}
			index++;
		}

	}
}

class Robot implements Comparable<Robot> {
	public long p;
	public long c;

	@Override
	public int compareTo(Robot r) {
		return Long.compare(this.p, r.p);
	}
}

class InputReader {
	StringTokenizer tokenizer;
	BufferedReader reader;
	String token;

	public InputReader(InputStream stream) {
		tokenizer = null;
		reader = new BufferedReader(new InputStreamReader(stream));
	}

	public InputReader(FileInputStream stream) {
		tokenizer = null;
		reader = new BufferedReader(new InputStreamReader(stream));
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}
}