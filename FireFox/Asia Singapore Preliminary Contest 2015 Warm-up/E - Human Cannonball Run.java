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
		double xa = in.nextDouble();
		double ya = in.nextDouble();
		double xb = in.nextDouble();
		double yb = in.nextDouble();
		int n = in.nextInt();
		double[] cx = new double[n + 1];
		double[] cy = new double[n + 1];

		double[][] times = new double[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			cx[i] = in.nextDouble();
			cy[i] = in.nextDouble();
			times[0][i] = times[i][0] = dist(xa, ya, cx[i], cy[i]) / 5;

			double distAB = dist(xb, yb, cx[i], cy[i]);
			double time = 0;
			if (distAB >= 50.0) {
				time = 2.0 + (distAB - 50.0) / 5;
			} else {
				time = Math.min(distAB / 5, 2.0 + (50 - distAB) / 5);
			}
			times[n + 1][i] = times[i][n + 1] = time;
		}
		times[0][n + 1] = times[n + 1][0] = dist(xb, yb, xa, ya) / 5;
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				double distAB = dist(cx[i], cy[i], cx[j], cy[j]);
				double time = 0;
				if (distAB >= 50.0) {
					time = 2.0 + (distAB - 50.0) / 5;
				} else {
					time = Math.min(distAB / 5, 2.0 + (50 - distAB) / 5);
				}
				times[i][j] = times[j][i] = time;
			}
		}
		int len = n + 2;
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (times[i][k] + times[k][j] < times[i][j]) {
						times[i][j] = times[j][i] = times[i][k] + times[k][j];
					}
				}
			}
		}
		out.printf("%.12f\n", times[0][len - 1]);
	}

	private double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
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

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}
