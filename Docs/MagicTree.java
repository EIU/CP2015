import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
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
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long[] data = new long[]{1, 2, 3, 4, 5};
            MagicTree magicTree = new MagicTree(data.length);
            magicTree.build(data);
            out.println(magicTree.getMax(0, 4) == 5);
            magicTree.update(0, 10);
            out.println(magicTree.getMax(0, 4) == 10);
            out.println(magicTree.getMax(1, 4) == 5);
            magicTree.update(4, 2);
            out.println(magicTree.getMax(1, 4) == 4);
            out.println(magicTree.getMax(1, 1) == 2);
            magicTree.update(0, 7);
            out.println(magicTree.getMax(1, 4) == 4);
            out.println(magicTree.getMax(0, 0) == 7);
        }

    }

    static class MagicTree {
        long[] t;
        int n;

        public MagicTree(int n) {
            this.n = n;
            this.t = new long[4 * n + 10];
        }

        public void build(long[] ar) {
            build(0, ar, 0, n - 1);
        }

        private void build(int node, long[] ar, int left, int right) {
            if (left == right) {
                t[node] = ar[left];
                return;
            }
            int mid = (left + right) >> 1;
            build(node * 2 + 1, ar, left, mid);
            build(node * 2 + 2, ar, mid + 1, right);
            t[node] = Math.max(t[node * 2 + 1], t[node * 2 + 2]);
        }

        public long getMax(int from, int to) {
            return getMax(0, 0, n - 1, from, to);
        }

        private long getMax(int node, int left, int right, int from, int to) {
            // out range
            if (right < from || to < left) {
                return Long.MIN_VALUE;
            }
            // fit in range
            if (from <= left && right <= to) {
                return t[node];
            }
            int mid = (left + right) >> 1;
            long leftNode = getMax(node * 2 + 1, left, mid, from, to);
            long rightNode = getMax(node * 2 + 2, mid + 1, right, from, to);
            return Math.max(leftNode, rightNode);
        }

        public void update(int index, long value) {
            update(0, 0, n - 1, index, value);
        }

        private void update(int node, int left, int right, int index, long value) {
            // out range
            if (index < left || right < index) return;
            if (left == right) {
                t[node] = value;
                return;
            }
            int mid = (left + right) >> 1;
            update(node * 2 + 1, left, mid, index, value);
            update(node * 2 + 2, mid + 1, right, index, value);
            // update node
            t[node] = Math.max(t[node * 2 + 1], t[node * 2 + 2]);
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

    }
}

