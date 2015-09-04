import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Gym100739I {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		IR in = new IR(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskI solver = new TaskI();
		// int numberOfTest = in.nextInt();
		// for (int it = 1; it <= numberOfTest; it++) {
		solver.solve(in, out);
		// }
		out.close();
	}
}

class TaskI {
	final static int MOD = 1000000007;

	public void solve(IR in, PrintWriter out) {

		double a = in.nextLong();
		double b = in.nextLong();
		double r = in.nextLong();

		double A = r / (a + r);
		double B = (r / (b + r));
		double min = Math.min(A, B);
		double max = Math.max(B, A);
		long n = 3;
		boolean continu = true;
		long count = 0;

		double left = Math.asin(A);
		double right = Math.asin(B);
		double ceil = Math.ceil(Math.PI / left);
		double floor = Math.floor(Math.PI / right + 1e-13);
		out.println(Math.round(floor - ceil + 1));
//		while (continu) {
//
//			double left = Math.sin(Math.PI / n);
//
//			if (max - left >= 1e-17) {
//				break;
//			} else {
//
//			}
//			n++;
//		}

//		while (continu) {
//			double left = Math.sin(Math.PI / n);
//
//			if (!(left - min >= -6e-17)) {
//				break;
//			}
//			count++;
//			n++;
//		}
//		out.println(count);

		
	}
}

class IR {
	StringTokenizer tokenizer;
	BufferedReader reader;

	public IR(InputStream inputStream) {
		reader = new BufferedReader(new InputStreamReader(inputStream));
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
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