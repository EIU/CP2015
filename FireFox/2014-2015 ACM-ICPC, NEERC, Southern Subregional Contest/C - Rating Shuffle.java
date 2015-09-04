import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Gym100738C {
	public static void main(String[] args) throws IOException {
		initReader();
		//Scanner in = new Scanner(System.in);
		int n = nextInt();
		long d = nextInt();
		long score[] = new long[n];
		long max = Long.MIN_VALUE;
		TreeSet<Long> set = new TreeSet<Long>();
		for (int i = 0; i < n; ++i) {
			score[i] = nextLong();
			set.add(score[i]);
			max = Math.max(score[i], max);
		}

		int count = 0;

		for (int i = 1; i < n; ++i) {
			if (score[i - 1] - 1 >= score[i]) {
				long change = (score[i - 1] - 1 - score[i]) / d;
				change -= (count - change) % 2;
				score[i] += Math.min(count, change) * d;
			} else {
				long change = (-score[i - 1] + score[i]) / d + 1;
				if (change < count) {
					change += (count - change) % 2;
					score[i] -= change * d;
				} else {
					score[i] -= change * d;

					long m = (change - count) % 2;
					if (m == 0) {
						score[i] += (change - count) / 2 * d;
					} else {
						score[i] += (change - count) / 2 * d;
					}

					count += (change - count + 1) / 2;
				}
			}
		}

		System.out.println(count);

	}
	
	static BufferedReader reader;
	static StringTokenizer tokenizer;

	public static void initReader() throws FileNotFoundException {
		// reader = new BufferedReader(new InputStreamReader(new
		// FileInputStream(
		// new File("in.in"))));
		reader = new BufferedReader(new InputStreamReader(System.in));

		tokenizer = new StringTokenizer("");
	}

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens()) {
			tokenizer = new StringTokenizer(reader.readLine());
		}
		return tokenizer.nextToken();
	}

	static String nextLine() throws IOException {
		return reader.readLine();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static Double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}