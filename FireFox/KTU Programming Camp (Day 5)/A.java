import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    static boolean isMultiTest = false;

    public static void main(String[] args) {
        OutputStream outputStream = System.out;
        InputStream inputStream = System.in;
        InputFast in = new InputFast();
        // Input in = new Input(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        if (isMultiTest) {
            int testNumber = in.nextInt();
            for (int i = 1; i <= testNumber; i++) {
                solver.solve(i, in, out);
            }
        } else {
            solver.solve(1, in, out);
        }
        out.close();
    }
}

class Task {
    final static int MOD = 1000000009;
    final int LIM = 1000001;
    long[][] sum;
    int n;

    public void set(int mod, int pos, long val) {
        for (int i = pos; i <= n; i += (i & -i)) {
            sum[mod][i] += val;
        }
    }

    public long get(int t, int mod) {
        long res = 0;
        for (int i = t; i >= 1; i -= (i & -i)) {
            res += sum[mod][i];
        }
        return res;
    }

    public long get(int f, int t, int mod) {
        return get(t, mod) - get(f - 1, mod);
    }

    public void solve(int testNumber, InputFast in, PrintWriter out) {
        n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; ++i)
            a[i] = in.nextInt();
        sum = new long[m][n + 1];
        for (int j = 1; j <= n; j++) {
            int mod = (int) (a[j - 1] % m);
            set(mod, j, a[j - 1]);
        }
        int q = in.nextInt();
        for (int i = 0; i < q; ++i) {
            char c = in.nextChar();
            if (c == 's') {
                int l = in.nextInt();
                int r = in.nextInt();
                int mod = in.nextInt();
                out.println(get(l, r, mod));
            } else {
                
                int pos = in.nextInt() - 1;
                int val = in.nextInt();

                int pre = (int) (a[pos] % m);
                long preVal = a[pos];
                if (c == '+') {
                    a[pos] += val;
                } else if (a[pos] - val >= 0) {
                    a[pos] -= val;
                }
                out.println(a[pos]);

                int cur = (int) (a[pos] % m);

                set(pre, pos + 1, -preVal);
                set(cur, pos + 1, a[pos]);
            }
        }
    }
}

class InputFast {
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

class Input {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public Input(InputStream stream) {
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

    public String nextLine() {
        String s = "";
        try {
            s = reader.readLine();
        } catch (IOException e) {
            try {
                throw new IOException();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return s;
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