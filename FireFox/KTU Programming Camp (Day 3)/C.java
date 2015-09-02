import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        List<Edge>[] g;
        int[][] c;
        int n;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            char[][] a = new char[n][n];
            for (int i = 0; i < n; i++) {
                a[i] = in.next().toCharArray();
            }
            g = new List[4 * n * n];
            for (int i = 0; i < g.length; i++) {
                g[i] = new ArrayList<Main.Edge>();
            }

            int current = 0;
            c = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = current;
                    current += 4;
                }
            }
            current = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == '#') {
                        current += 4;
                        continue;
                    }
                    int dir;
                    // 0
                    dir = 0;
                    // ^
                    if (isValid(i - 1, j) && a[i - 1][j] != '#') {
                        addEdge(current + dir, i - 1, j, 0, 1);

                    }
                    // ->
                    if (isValid(i, j + 1) && a[i][j + 1] != '#') {
                        addEdge(current + dir, i, j + 1, 3, 4);
                    }
                    // v
                    if (isValid(i + 1, j) && a[i + 1][j] != '#') {
                        addEdge(current + dir, i + 1, j, 2, 3);
                    }
                    // <-
                    if (isValid(i, j - 1) && a[i][j - 1] != '#') {
                        addEdge(current + dir, i, j - 1, 1, 2);
                    }
                    // 1
                    // ^
                    dir = 1;
                    if (isValid(i - 1, j) && a[i - 1][j] != '#') {
                        addEdge(current + dir, i - 1, j, 0, 4);
                    }
                    if (isValid(i, j + 1) && a[i][j + 1] != '#') {
                        addEdge(current + dir, i, j + 1, 3, 3);
                    }
                    if (isValid(i + 1, j) && a[i + 1][j] != '#') {
                        addEdge(current + dir, i + 1, j, 2, 2);
                    }
                    if (isValid(i, j - 1) && a[i][j - 1] != '#') {
                        addEdge(current + dir, i, j - 1, 1, 1);
                    }
                    // 1
                    dir = 2;
                    if (isValid(i - 1, j) && a[i - 1][j] != '#') {
                        addEdge(current + dir, i - 1, j, 0, 3);
                    }
                    if (isValid(i, j + 1) && a[i][j + 1] != '#') {
                        addEdge(current + dir, i, j + 1, 3, 2);
                    }
                    if (isValid(i + 1, j) && a[i + 1][j] != '#') {
                        addEdge(current + dir, i + 1, j, 2, 1);
                    }
                    if (isValid(i, j - 1) && a[i][j - 1] != '#') {
                        addEdge(current + dir, i, j - 1, 1, 4);
                    }
                    // 2
                    dir = 3;
                    if (isValid(i - 1, j) && a[i - 1][j] != '#') {
                        addEdge(current + dir, i - 1, j, 0, 2);
                    }
                    if (isValid(i, j + 1) && a[i][j + 1] != '#') {
                        addEdge(current + dir, i, j + 1, 3, 1);
                    }
                    if (isValid(i + 1, j) && a[i + 1][j] != '#') {
                        addEdge(current + dir, i + 1, j, 2, 4);
                    }
                    if (isValid(i, j - 1) && a[i][j - 1] != '#') {
                        addEdge(current + dir, i, j - 1, 1, 3);
                    }
                    // quay vong vong :((
                    // 0
                    g[current].add(new Edge(current + 1, 1));
                    g[current].add(new Edge(current + 2, 2));
                    g[current].add(new Edge(current + 3, 3));
                    // 1
                    g[current + 1].add(new Edge(current + 2, 1));
                    g[current + 1].add(new Edge(current + 3, 2));
                    g[current + 1].add(new Edge(current, 3));
                    // 2
                    g[current + 2].add(new Edge(current + 3, 1));
                    g[current + 2].add(new Edge(current, 2));
                    g[current + 2].add(new Edge(current + 1, 3));
                    // 3
                    g[current + 3].add(new Edge(current, 1));
                    g[current + 3].add(new Edge(current + 1, 2));
                    g[current + 3].add(new Edge(current + 2, 3));
                    current += 4;
                }
            }
            int[] d = new int[4 * n * n];
            shortestPaths(g, 2, d, new int[4 * n * n]);
            out.println(d[4 * n * n - 2]);
        }

        boolean isValid(int i, int j) {
            return i >= 0 && i < n && j >= 0 && j < n;
        }

        void addEdge(int from, int toi, int toj, int dir, int cost) {
            g[from].add(new Edge(c[toi][toj] + dir, cost));
        }

        public void shortestPaths(List<Edge>[] edges, int s, int[] prio,
                int[] pred) {
            Arrays.fill(pred, -1);
            Arrays.fill(prio, Integer.MAX_VALUE);
            prio[s] = 0;
            PriorityQueue<Long> q = new PriorityQueue<>();
            q.add((long) s);
            while (!q.isEmpty()) {
                long cur = q.remove();
                int curu = (int) cur;
                if (cur >>> 32 != prio[curu])
                    continue;
                for (Edge e : edges[curu]) {
                    int v = e.t;
                    int nprio = prio[curu] + e.cost;
                    if (prio[v] > nprio) {
                        prio[v] = nprio;
                        pred[v] = curu;
                        q.add(((long) nprio << 32) + v);
                    }
                }
            }
        }
    }

    static class Edge {
        int t, cost;

        public Edge(int t, int cost) {
            this.t = t;
            this.cost = cost;
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