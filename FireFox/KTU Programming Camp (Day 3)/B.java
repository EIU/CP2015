import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
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
    public void solve(InputReader zm, PrintWriter out) {
        int n = nextInt();
        int Q = nextInt();
        int[] a = new int[n];
        int[][] cache = new int[301][301];
        

        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j <= 300; ++j) {
                cache[j][i % j] += a[i];
            }
        }
        for (int i = 0; i < Q; ++i) {

            int q = nextInt();
            int p = nextInt();

            if (p <= 300) {
                out.println(cache[p][q]);
                continue;
            }

            long result = 0;
            for (int j = q; j < n; j += p) {
                result += a[j];
            }
            out.println(result);
        }
    }

    static InputStream is = System.in;
    static private byte[] buffer = new byte[1024];
    static private int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1)
            throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(buffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return -1;
        }
        return buffer[ptrbuf++];
    }

    private boolean isSpace(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int read() {
        int b;
        while ((b = readByte()) != -1 && isSpace(b))
            ;
        return b;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public char nextChar() {
        return (char) read();
    }

    public String next() {
        int b = read();
        StringBuilder sb = new StringBuilder();
        while (!(isSpace(b))) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public char[] next(int n) {
        char[] buf = new char[n];
        int b = read(), p = 0;
        while (p < n && !(isSpace(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    public int nextInt() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    public long nextLong() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
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