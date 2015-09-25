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
            MagicTree magicTree = new MagicTree(data);
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
        long[] nodes;
        int n;

        public MagicTree(long[] ar) {
            this.n = ar.length;
            this.nodes = new long[4 * n + 10];
            build(0, ar, 0, n - 1);
        }

        private void build(int node, long[] ar, int left, int right) {
            if (left == right) {
                nodes[node] = ar[left];
                return;
            }
            int mid = (left + right) >> 1;
            build(node * 2 + 1, ar, left, mid);
            build(node * 2 + 2, ar, mid + 1, right);
            nodes[node] = Math.max(nodes[node * 2 + 1], nodes[node * 2 + 2]);
            // nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2] if we want to calculate sum
        }

        public long getMax(int from, int to) {
            return getMax(0, 0, n - 1, from, to);
        }

        private long getMax(int node, int left, int right, int from, int to) {
            // out range
            if (right < from || to < left) {
                return Long.MIN_VALUE;
                // return 0 if we want to calculate sum
            }
            // fit in range
            if (from <= left && right <= to) {
                return nodes[node];
            }
            int mid = (left + right) >> 1;
            long leftValue = getMax(node * 2 + 1, left, mid, from, to);
            long rightValue = getMax(node * 2 + 2, mid + 1, right, from, to);
            return Math.max(leftValue, rightValue);
            // return leftValue + rightValue if we want to calculate sum
        }

        public void update(int index, long value) {
            update(0, 0, n - 1, index, value);
        }

        private void update(int node, int left, int right, int index, long value) {
            // out range
            if (index < left || right < index) return;
            if (left == right) {
                nodes[node] = value;
                return;
            }
            int mid = (left + right) >> 1;
            update(node * 2 + 1, left, mid, index, value);
            update(node * 2 + 2, mid + 1, right, index, value);
            // update node
            nodes[node] = Math.max(nodes[node * 2 + 1], nodes[node * 2 + 2]);
            // nodes[node] = nodes[node * 2 + 1] + nodes[node * 2 + 2] if we want to calculate sum
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

		public int nextInt(){
			return Integer.parseInt(next());
		}

		public long nextLong(){
			return Long.parseLong(next());
		}

		public double nextDouble(){
			return Double.parseDouble(next());
		}
    }
}

/*
Why 4*n:
maxObj = {max: 0}; 
for(var i=1;i<10000; i++){ max = buildTree(0, 0, i-1); if(max/i > maxObj.max) maxObj = {max: max/i, i: i, total: max}}
function buildTree(node, left, right){if(left == right) return node; var mid = (left+right)>>1; return Math.max(buildTree(node*2+1, left, mid), buildTree(node*2+2, mid+1,right)); }
0,1,2,3,4,5,6,7,8,9 => max/i = 2.4
i = 8256 => 3.937
*/
