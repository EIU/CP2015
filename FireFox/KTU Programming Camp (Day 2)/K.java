import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
}

class Task {
    static final long END = 1000000000L;

    public void solve(InputReader in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        long p = in.nextLong();
        long left = 1;
        long right = (long) 1e18;
        long res = 0;
        while (right >= left) {
            long mid = (left + right) >> 1;
            double dif = calc(a, b, mid) - p;
            if (dif + 0.00000001 >= 0) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        out.println(res);
    }

    double calc(long a, long b, long n) {
        return a * roundTo(Math.cbrt(n)) + b * roundTo(Math.sqrt(n));
    }

    double roundTo(double n) {
        int p = 100000000;
        return Math.round(n * p) * 1D / p;
    }
}

class InputReader {
    StringTokenizer tokenizer;
    BufferedReader reader;

    public InputReader(InputStream inputStream) {
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