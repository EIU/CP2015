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
		int k = in.nextInt();
		while (k-- > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			int index = 0;
			HashMap<Integer, Integer> mappingValue = new HashMap<>();
	
			List<Integer>[] sets = new List[m];
			for (int i = 0; i < m; ++i) {
				String[] num = in.nextLine().split(" ");
				sets[i] = new ArrayList<>();
				for (String str : num) {

					if (str.length() > 0) {
						int value = Integer.parseInt(str);
						Integer mm = mappingValue.get(value);
						sets[i].add(mm == null ? index : mm);
						if (mm == null) {
							mappingValue.put(value, index++);
						}
					}
				}

			}
			int max = 1 << n;
			boolean ok = true;
			for (int boo = 0; boo < max; ++boo) {
				ok = true;
				for (int j = 0; j < m; ++j) {
					int count = 0;
					for (Integer aa : sets[j]) {
						count += (boo & (1 << aa)) > 0 ? 1 : 0;
					}
					if (count == 0 || count == sets[j].size()) {
						ok = false;
						break;
					}
				}
				if (ok) {
					break;
				}
			}
			out.print(ok ? "Y" : "N");
		}
		//out.println();
	}

	public int getDigitCount(BigInteger number) {
		double factor = Math.log(2) / Math.log(10);
		int digitCount = (int) (factor * number.bitLength() + 1);
		if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
			return digitCount - 1;
		}
		return digitCount;
	}

	public int[] generatePrimes(int n) {
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, 2, n + 1, true);
		for (int i = 2; i * i <= n; i++)
			if (prime[i])
				for (int j = i * i; j <= n; j += i)
					prime[j] = false;
		int[] primes = new int[n + 1];
		int cnt = 0;
		for (int i = 0; i < prime.length; i++)
			if (prime[i])
				primes[cnt++] = i;

		return Arrays.copyOf(primes, cnt);
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

	public String nextLine() {

		try {
			return reader.readLine();
		} catch (IOException e) {
		}
		return "";
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}
}