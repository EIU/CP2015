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
        int numberOfTest = in.nextInt();
        for (int it = 1; it <= numberOfTest; it++) {
            solver.solve(in, out);
        }
        out.close();
    }
}

class Task {
    long[][] a;
    long[][] sum;
    long[][] sum2;
    int n;
    long w;

    public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        w = in.nextLong();
        a = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextLong();
            }
        }
        sum = new long[n][n];
        sum2 = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = a[i][j];
                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum[i][j] += sum[i - 1][j - 1];
                }
                sum2[i][j] = a[i][j];
                if (i - 1 >= 0 && j + 1 < n) {
                    sum2[i][j] += sum2[i - 1][j + 1];
                }
            }
        }
        int left = 1;
        int right = n;
        int res = 0;
        while (right >= left) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                left = mid + 1;
                res = mid;
            } else {
                right = mid - 1;
            }
        }
        out.println(res);
    }

    boolean check(int l) {
        for (int i = l - 1; i < n; i++) {
            for (int j = l - 1; j < n; j++) {
                long current = sum[i][j];
                if (i - l >= 0 && j - l >= 0) {
                    current -= sum[i - l][j - l];
                }
                current += sum2[i][j - l + 1];
                if (i - l >= 0 && j + 1 < n) {
                    current -= sum2[i - l][j + 1];
                }
                if (l % 2 == 1) {
                    int foo = l >> 1;
                    current -= a[i - foo][j - foo];
                }
                if (current <= w) {
                    return true;
                }
            }
        }
        return false;
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