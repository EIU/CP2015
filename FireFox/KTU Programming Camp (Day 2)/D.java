import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
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
    public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Node[] nodes = new Node[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1, in.nextInt());
            sum += nodes[i].degree;
        }
        /*
         * if (sum != ((n - 1) << 1)) { out.println(-1); return; }
         */
        PriorityQueue<Node> p = new PriorityQueue<Task.Node>();
        for (int i = 0; i < n; i++) {
            p.add(nodes[i]);
        }
        StringBuilder ans = new StringBuilder();
        PriorityQueue<Node> next = new PriorityQueue<Task.Node>();
        next.add(p.poll());
        while (!next.isEmpty()) {
            Node cur = next.poll();
            while (cur.degree > 0 && !p.isEmpty()) {
                Node child = p.poll();
                
                ans.append(cur.id + " " + child.id);
                ans.append("\n");
                
                child.degree--;
                cur.degree--;
                if (child.degree > 0) {
                    next.add(child);
                }       
            }

            if (cur.degree > 0) {
                out.println(-1);
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nodes[i].degree != 0) {
                out.println(-1);
                return;
            }
        }
        out.print(ans);
    }

    class Node implements Comparable<Node> {
        int id;
        int degree;

        public Node(int id, int degree) {
            this.id = id;
            this.degree = degree;
        }

        @Override
        public int compareTo(Node o) {
            return o.degree - this.degree;
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