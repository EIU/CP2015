import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
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

    static class Task {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int m = in.nextInt();
            int n = in.nextInt();
            int t = in.nextInt();
            if (t == 1) {
                long time = 1;
                for (int i = 1; i <= n; i++) {
                    time *= i;
                    if (time > m) {
                        out.println("TLE");
                        return;
                    }
                }
                out.print("AC");
            } else if (t == 2) {
                long time = 0;
                for (int i = 0; i <= n; i++) {
                    time = 1L << i;
                    if (time > m) {
                        out.println("TLE");
                        return;
                    }
                }
                out.print("AC");
            } else if (t == 3) {
                BigInteger N = new BigInteger(Integer.toString(n));
                BigInteger time = N.pow(4);
                if (time.compareTo(new BigInteger(Integer.toString(m))) > 0) {
                    out.println("TLE");
                } else {
                    out.println("AC");
                }
            } else if (t == 4) {
                long time = (long) Math.pow(n, 3);
                if (time > m) {
                    out.println("TLE");
                } else {
                    out.println("AC");
                }
            } else if (t == 5) {
                long time = (long) Math.pow(n, 2);
                if (time > m) {
                    out.println("TLE");
                } else {
                    out.println("AC");
                }
            } else if (t == 6) {
                double log2n = (Math.log(n) / Math.log(2));
                double time = n * log2n;
                if (time > m) {
                    out.println("TLE");
                } else {
                    out.println("AC");
                }
            } else {
                if (n > m) {
                    out.println("TLE");
                } else {
                    out.println("AC");
                }
            }
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
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

    }
}

