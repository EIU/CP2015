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
	char[][] a;

	public void solve(int caseNum, InputReader in, PrintWriter out) {
		a = new char[8][8];
		int total = 0;
		int[] hor = new int[8];
		int[] ver = new int[8];
		int[] dig1 = new int[8 * 2 - 1];
		int[] dig2 = new int[8 * 2 - 1];
		for (int i = 0; i < 8; i++) {
			a[i] = in.next().toCharArray();
			for (int j = 0; j < 8; j++) {
				if (a[i][j] == '*') {
					hor[i]++;
					ver[j]++;
					dig1[i + j]++;
					dig2[i - j + (8 - 1)]++;
					total++;
				}
			}
		}
		if (total != 8) {
			out.println("invalid");
			return;
		}
		for (int i = 0; i < 8; i++) {
			if (hor[i] >= 2 || ver[i] >= 2) {
				out.println("invalid");
				return;
			}
		}
		for (int i = 0; i < 15; i++) {
			if (dig1[i] >= 2 || dig2[i] >= 2) {
				out.println("invalid");
				return;
			}
		}
		out.println("valid");
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
