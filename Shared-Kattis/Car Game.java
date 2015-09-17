import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
        cargame solver = new cargame();
        solver.solve(1, in, out);
        out.close();
    }

    static class cargame {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            List<Integer>[][] a = new List[n][26];
            String[] dic = new String[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 26; j++) {
                    a[i][j] = new ArrayList<Integer>();
                }
            }
            for (int i = 0; i < n; i++) {
                dic[i] = in.next();
                char[] c = dic[i].toCharArray();
                for (int j = 0; j < c.length; j++) {
                    int index = c[j] - 'a';
                    a[i][index].add(j);
                }
            }
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < m; i++) {
                char[] plate = in.next().toCharArray();
                int at = -1;
                for (int j = 0; j < n; j++) {
                    int first = plate[0] - 'A';
                    int second = plate[1] - 'A';
                    int third = plate[2] - 'A';
                    if (a[j][first].size() == 0) {
                        continue;
                    }
                    int ind = a[j][first].get(0);
                    ind = go(a[j][second], ind);
                    if (ind == -1) {
                        continue;
                    }
                    ind = go(a[j][third], a[j][second].get(ind));
                    if (ind == -1) {
                        continue;
                    }
                    at = j;
                    break;
                }
                if (at == -1) {
                    res.append("No valid word");
                } else {
                    res.append(dic[at]);
                }
                res.append("\n");
            }
            out.print(res);
        }

        int go(List<Integer> a, int key) {
            int left = 0;
            int right = a.size() - 1;
            int result = -1;
            while (right >= left) {
                int mid = (left + right) >> 1;
                if (a.get(mid) > key) {
                    right = mid - 1;
                    result = mid;
                } else {
                    left = mid + 1;
                }
            }
            return result;
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

