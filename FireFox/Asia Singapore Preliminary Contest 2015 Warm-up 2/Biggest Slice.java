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
		while (t-- > 0) {
			int r = in.nextInt();
			int n = in.nextInt();
			int oo = in.nextInt();
			int mm = in.nextInt();
			int ss = in.nextInt();
			// ArrayList<Long> pos = new ArrayList<>();
			int count = 0;
			int kk = oo * 60 * 60 + mm * 60 + ss;
			// pos.add(kk);
			int oz = 360 * 60 * 60;
			boolean[] used = new boolean[oz];
			used[kk] = true;
			int pre = kk;
			for (int i = 1; true && i < n; ++i) {
				int ll = (pre + kk) % oz;
				pre = ll;
				if (!used[ll]) {
					used[ll] = true;
				} else {
					break;
				}
			}
			int min = -1;
			int max = -1;
			pre = -1;
			long ans = 0;
			for (int i = 0; i < oz; ++i) {
				if (used[i]) {
					if (min == -1) {
						min = i;
					}
					max = i;
					if (pre != -1) {
						if (i - pre > ans) {
							ans = i - pre;
						}
					}
					pre = i;
				}
			}
			if (min != max) {
				ans = Math.max(ans, min + oz - max);
			} else {
				ans = oz;
			}

			out.println(r * r * Math.PI * ans / 360 / 60 / 60);
		}

	}

	public int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);

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