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
		int t = in.nextInt();
		List<Long> pans = new ArrayList<Long>();
		long c = 1;
		while (c <= 3000l * 1000 * 1000) {
			pans.add(c);
			c *= 3;
		}
		int size = pans.size();
		while (t-- > 0) {
			int n = in.nextInt();
			List<Long> left = new ArrayList<Long>();
			List<Long> right = new ArrayList<Long>();
			long sumLeft = 0;
			long sumRight = 0;
			long diff = Math.abs(sumLeft - sumRight);
			while (diff != n) {
				int i = -1;
				long sum = 0;
				long need = Math.abs(n - diff);
				while (sum < need) {
					i++;
					sum += pans.get(i);
				}
				int choose = i;

				if (n > diff) {
					if (sumLeft > sumRight) {
						sumLeft += pans.get(choose);
						left.add(pans.get(choose));
					} else {
						sumRight += pans.get(choose);
						right.add(pans.get(choose));
					}
				} else {
					if (sumLeft < sumRight) {
						sumLeft += pans.get(choose);
						left.add(pans.get(choose));
					} else {
						sumRight += pans.get(choose);
						right.add(pans.get(choose));
					}
				}
				diff = Math.abs(sumLeft - sumRight);
			}

			if (sumLeft > sumRight) {
				List<Long> mm = right;
				right = left;
				left = mm;
			}
			out.print("left pan:");
			if (left.size() > 0) {
				Collections.sort(left);
				int leftSize = left.size();
				for (int oo = leftSize - 1; oo >= 0; --oo) {
					out.print(" " + left.get(oo));
				}
			}
			out.println();
			out.print("right pan:");
			if (right.size() > 0) {
				Collections.sort(right);
				int leftSize = right.size();
				for (int oo = leftSize - 1; oo >= 0; --oo) {
					out.print(" " + right.get(oo));
				}
			}
			out.println();
			if (t > 0) {
				out.println();
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