import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
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
        animal solver = new animal();
        solver.solve(1, in, out);
        out.close();
    }

    static class animal {
        List<Integer>[] tree1;
        List<Integer>[] tree2;
        HashSet<String> set;
        boolean[] was;
        int result;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            char[] a = in.next().toCharArray();
            char[] b = in.next().toCharArray();
            tree1 = buildGraph(a, n);
            tree2 = buildGraph(b, n);
            // min -> max -> total
            set = new HashSet<String>();
            result = n;
            was = new boolean[tree1.length];
            dfs(n, n, tree1);
            was = new boolean[tree2.length];
            dfs2(n, n, tree2);
            out.println(result);
        }

        Couple dfs(int u, int n, List<Integer>[] tree) {
            was[u] = true;
            if (u < n) {
                return new Couple(u, u, 1);
            }
            Couple res = new Couple(tree.length + 10, -10, 0);
            for (int v : tree[u]) {
                if (!was[v]) {
                    Couple foo = dfs(v, n, tree);
                    res.update(foo);
                }
            }
            String key = res.getKey();
            if (!set.contains(key)) {
                set.add(key);
            }
            return res;
        }

        Couple dfs2(int u, int n, List<Integer>[] tree) {
            was[u] = true;
            if (u < n) {
                return new Couple(u, u, 1);
            }
            Couple res = new Couple(tree.length + 10, -10, 0);
            for (int v : tree[u]) {
                if (!was[v]) {
                    Couple foo = dfs2(v, n, tree);
                    res.update(foo);
                }
            }
            String key = res.getKey();
            if (set.contains(key)) {
                ++result;
            }
            return res;
        }

        List<Integer>[] buildGraph(char[] a, int n) {
            int numNode = n;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == '(') ++numNode;
            }
            List<String> s = new ArrayList<String>();
            StringBuilder foo = new StringBuilder();
            for (int i = 0; i < a.length; i++) {
                if (Character.isDigit(a[i])) {
                    foo.append(a[i]);
                } else {
                    if (foo.length() != 0) {
                        s.add(foo.toString());
                        foo = new StringBuilder();
                    }
                    s.add("" + a[i]);
                }
            }
            int count = n - 1;
            boolean flag = false;
            boolean flag2 = false;
            List<Integer>[] tree = new List[numNode];
            for (int i = 0; i < numNode; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            Stack<Integer> stack = new Stack<Integer>();
            for (int i = 0; i < s.size(); i++) {
                String cur = s.get(i);
                if (flag) {
                    flag = false;
                    if (cur.equals("(")) {
                        tree[count].add(count + 1);
                    } else {
                        tree[count].add(Integer.parseInt(cur) - 1);
                    }
                }
                if (flag2) {
                    flag2 = false;
                    int node = stack.pop();
                    if (cur.equals("(")) {
                        tree[node].add(count + 1);
                    } else {
                        tree[node].add(Integer.parseInt(cur) - 1);
                    }
                }
                if (cur.equals("(")) {
                    ++count;
                    flag = true;
                    stack.add(count);
                }
                if (cur.equals(",")) {
                    flag2 = true;
                }
            }
            return tree;
        }

    }

    static class Couple {
        int min;
        int max;
        int total;

        public Couple(int min, int max, int total) {
            this.min = min;
            this.max = max;
            this.total = total;
        }

        public void update(Couple other) {
            this.min = Math.min(this.min, other.min);
            this.max = Math.max(this.max, other.max);
            this.total += other.total;
        }

        public String getKey() {
            StringBuilder res = new StringBuilder();
            res.append(this.min);
            res.append("-");
            res.append(this.max);
            res.append("-");
            res.append(this.total);
            return res.toString();
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

