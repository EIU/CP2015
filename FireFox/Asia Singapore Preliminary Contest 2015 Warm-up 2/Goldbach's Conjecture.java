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
		// int n = in.nextInt();
		int[] primes = generatePrimes(40000);
		int n = in.nextInt();
		int length = primes.length;
		StringBuilder str;
		for (int i = 0; i < n; ++i) {
			str = new StringBuilder();
			int t = in.nextInt();
			int count = 0;

			for (int j = 0; j < length && (primes[j] << 1) <= t; ++j) {
				if (Arrays.binarySearch(primes, t - primes[j]) >= 0) {
					str.append(primes[j] + "+" + (t - primes[j]) + "\n");
					count++;
				}
			}
			out.println(t + " has " + count + " representation(s)");
			if (i < n - 1) {
				out.println(str);
			} else {
				out.print(str);
			}

		}

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
			// TODO Auto-generated catch block
			// e.printStackTrace();
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