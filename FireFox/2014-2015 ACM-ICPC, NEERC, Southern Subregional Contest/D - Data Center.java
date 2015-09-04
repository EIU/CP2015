import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task {

	public void solve(int caseNum, InputReader in, PrintWriter out) {
		int n = in.nextInt();
		long m = in.nextLong();
		List<Server> low = new ArrayList<Server>();
		List<Server> hi = new ArrayList<Server>();
		for (int i = 0; i < n; i++) {
			Server a = new Server(i + 1, in.nextLong());
			boolean isLow = in.nextInt() == 1;
			if (isLow) {
				low.add(a);
			} else {
				hi.add(a);
			}
		}
		Collections.sort(low);
		Collections.sort(hi);

		long[] sum = new long[hi.size()];
		if (hi.size() > 0) {
			sum[0] = hi.get(0).value;
			for (int i = 1; i < hi.size(); i++) {
				sum[i] = hi.get(i).value;
				sum[i] += sum[i - 1];
			}
		}
		int best = search(sum, m) != -1 ? search(sum, m) + 1 : n;
		int secondBest = 0;
		long current = 0;
		for (int l = 0; l < low.size(); l++) {
			current += low.get(l).value;
			long need = m - current;
			if (need <= 0) {
				int total = l + 1;
				if (total <= best) {
					best = total;
					secondBest = l + 1;
				}
			} else {
				int s = search(sum, need);
				if (s == -1) {
					continue;
				}
				int total = l + 1 + s + 1;
				if (total <= best) {
					best = total;
					secondBest = l + 1;
				}
			}
		}
		out.println(best + " " + secondBest);
		for (int i = 0; i < secondBest; i++) {
			out.print(low.get(i).index + " ");
		}
		for (int i = 0; i < best - secondBest; i++) {
			out.print(hi.get(i).index + " ");
		}
		out.println();
	}

	int search(long[] a, long key) {
		int res = -1;
		int left = 0;
		int right = a.length - 1;
		while (right >= left) {
			int mid = (left + right) >> 1;
			if (a[mid] >= key) {
				res = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return res;
	}
}

class Server implements Comparable<Server> {
	int index;
	long value;

	public Server(int index, long value) {
		this.index = index;
		this.value = value;
	}

	@Override
	public int compareTo(Server o) {
		return -Long.compare(this.value, o.value);
	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
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

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}
}
