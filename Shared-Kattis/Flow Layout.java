import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
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
        flowlayout solver = new flowlayout();
        solver.solve(1, in, out);
        out.close();
    }

    static class flowlayout {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            while (true) {
                int width = in.nextInt();
                if (width == 0) return;
                int[] w = new int[20];
                int[] h = new int[20];
                int maxWidth = 0;
                int curWidth = 0;
                int totalHeight = 0;
                int curHeight = 0;
                for (int i = 0; i < 20; i++) {
                    w[i] = in.nextInt();
                    h[i] = in.nextInt();
                    maxWidth = Math.max(maxWidth, w[i]);
                    if (w[i] == -1 && h[i] == -1) break;
                    if (w[i] + curWidth > width) {
                        totalHeight += curHeight;
                        curWidth = w[i];
                        curHeight = h[i];
                    } else {
                        curWidth += w[i];
                        curHeight = Math.max(curHeight, h[i]);
                    }
                    maxWidth = Math.max(curWidth, maxWidth);
                }
                totalHeight += curHeight;
                if (maxWidth > width) {
                    out.println("0 x 0");
                } else {
                    out.println(maxWidth + " x " + totalHeight);
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

