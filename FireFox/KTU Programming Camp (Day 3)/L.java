import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        //int numberOfTest = in.nextInt();
        //for (int it = 1; it <= numberOfTest; it++) {
            solver.solve(in, out);
        //}
        out.close();
    }
}

class Task {
    final static int MOD = 1000000007;

    public void solve(InputReader in, PrintWriter out) {
        long a = in.nextLong();
        out.println(modPow(2, a));
    }

    private long modPow(long a, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= a;
                res %= MOD;
            }
            a *= a;
            a %= MOD;
            n >>= 1;
        }
        return res % MOD;
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