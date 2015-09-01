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
    long[][] matrix;
    long[][] unitMatrix;
    long[][] p;

    public void solve(int testNumber, InputFast in, PrintWriter out) {
        int n = in.nextInt();
        long m = in.nextLong();
        int c = in.nextInt();
        p = new long[n][1];
        for (int i = 0; i < n; i++) {
            p[i][0] = in.nextLong();
        }
        int[] kc = new int[c];
        for (int i = 0; i < c; i++)
            kc[i] = in.nextInt();
        
        if (m < n) {
            out.println(p[(int) m - 1][0]);
            return;
        }
        
        matrix = new long[n][n];
        for (int i = 0; i < c; i++) {
            matrix[n - 1][n - kc[i]]++;
        }
        for (int i = 1; i < n; i++) {
            matrix[i - 1][i]++;
        }
        unitMatrix = new long[n][n];
        for (int i = 0; i < n; i++)
            unitMatrix[i][i] = 1L;
        
        out.println(exponentMatrix(m));

    }

    private long exponentMatrix(long m) {
        m--;
        while (m > 0) {
            if ((m & 1) != 0) {
                unitMatrix = mulMatrix(unitMatrix, matrix);
            }
            matrix = mulMatrix(matrix, matrix);
            m >>= 1L;
        }
        unitMatrix = mulMatrix(unitMatrix, p);
        return unitMatrix[0][0] % MOD;
    }
    
    private long[][] mulMatrix(long[][] a, long[][] b) {
        int r = a.length;
        int c = b[0].length;
        int l = a[0].length;
        long[][] res = new long[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < l; k++) {
                    res[i][j] += a[i][k] * b[k][j] % MOD;
                    res[i][j] %= MOD;
                }
            }
        }
        return res;
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