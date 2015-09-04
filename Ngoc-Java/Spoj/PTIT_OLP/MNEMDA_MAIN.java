import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class MNEMDA_MAIN {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        MNEMDA solver = new MNEMDA();
        solver.solve(1, in, out);
        out.close();
    }

    static class MNEMDA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int m = in.nextInt();
            int n = in.nextInt();
            int k = in.nextInt();
            char[] a = new char[8 * m];
            Arrays.fill(a, '0');
            for (int i = 1; i <= m; i++) {
                char[] b = Integer.toBinaryString(in.nextInt()).toCharArray();
                for (int j = b.length - 1, z = i * 8 - 1; j >= 0; j--, z--) {
                    a[z] = b[j];
                }
            }
            int[] begin = new int[8 * m];
            int[] end = new int[8 * m];
            for (int i = 0; i < n; i++) {
                int sbyte = in.nextInt();
                int sbit = in.nextInt();
                int ebyte = in.nextInt();
                int ebit = in.nextInt();
                begin[calc(sbyte, sbit)]++;
                end[calc(ebyte, ebit)]++;
            }
            int count = 0;
            for (int i = 0; i < a.length; i++) {
                count += begin[i];
                if (count > 0) {
                    a[i] = '0';
                }
                count -= end[i];
            }
            int[] f = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                if (i > 0) f[i] = f[i - 1];
                if (a[i] == '1') f[i]++;
            }
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < k; i++) {
                int sbyte = in.nextInt();
                int sbit = in.nextInt();
                int ebyte = in.nextInt();
                int ebit = in.nextInt();
                int left = calc(sbyte, sbit);
                int right = calc(ebyte, ebit);
                int s = f[right];
                if (left > 0) s -= f[left - 1];
                if (s > 0) {
                    res.append("YES");
                } else {
                    res.append("PASS");
                }
                res.append("\n");
            }
            out.println(res);
        }

        int calc(int sbyte, int sbit) {
            return sbyte * 8 + 7 - sbit;
        }

    }

    static class InputReader {
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

