import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Gym100513E {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		it in = new it(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task1 solver = new Task1();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task1 {
	public void solve(int caseNum, it in, PrintWriter out) {
		int n = in.nextInt();
		int win1 = 0;
		int win2 = 0;
		int[] first = new int[n];
		int[] second = new int[n];

		for (int i = 0; i < n; ++i) {
			first[i] = in.nextInt();
			second[i] = in.nextInt();
			win1 += first[i] > second[i] ? 1 : 0;
		}
		int a[] = new int[n];
		int b[] = new int[n];
		int index = 0;

		if (win1 > n / 2) {
			out.println(0);
			out.close();
			return;
		}
		int count = n;
		for (int i = 1; i < n; ++i) {
			if (first[i] > second[i]) {
				if (first[i - 1] == second[i - 1]) {
					a[index] = i;
					b[index++] = ++i;

					count--;
				} else if (first[i - 1] > second[i - 1]) {
					continue;
				} else if (first[i - 1] + first[i] > second[i - 1] + second[i]) {
					a[index] = i;
					b[index++] = ++i;
					count--;
				}

			} else if (first[i] == second[i]) {
				a[index] = i;
				b[index++] = ++i;
				count--;
			} else {
				if (first[i - 1] > second[i - 1]
						&& first[i - 1] + first[i] <= second[i - 1] + second[i]) {
					continue;
				}

				a[index] = i;
				b[index++] = ++i;
				count--;
			}
			if (win1 > count / 2) {
				break;
			}

		}

		if (win1 <= count / 2) {
			out.println(-1);
			return;
		}
		out.println(n - count);
		int need = n - count;
		for (int i = 0; i < need; ++i) {
			out.println(a[i] + " " + b[i]);
		}

	}
}

class it {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public it(InputStream stream) {
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
