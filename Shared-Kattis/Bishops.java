import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// int n = nextInt();
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {

			int n = in.nextInt();
			if (n == 1) {
				System.out.println(n);
				continue;
			}

			System.out.println(n * 2 - 2);
		}

	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer tokenizer = new StringTokenizer("");

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens()) {
			String n = reader.readLine();
			if (n == null) {
				return null;
			}
			tokenizer = new StringTokenizer(n);

		}
		return tokenizer.nextToken();
	}

	static String nextLine() throws IOException {
		return reader.readLine();
	}

	static Integer nextInt() throws IOException {
		String next = next();
		if (next == null) {
			return null;
		}
		return Integer.parseInt(next);
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static Double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}
