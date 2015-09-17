import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static InputStream is;
    static List<Edge>[] edges;

    public static void main(String[] args) {
        is = System.in;

        solve();
    }

    static void solve() {
        int n = ni();
        int m = ni();
        edges = new List[n + 1];
        boolean[] checked = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < m; i++) {
            int a = ni();
            int b = ni();
            int c = ni();
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();
        long[] path = new long[n + 1];
        int[] imp = new int[n + 1];
//        checked[1] = true;
        Arrays.fill(path, Long.MAX_VALUE);

        path[1] = 0;
        q.add(new Edge(1, 0));
//        for (Edge edge : edges[1]) {
//            q.add(edge);
//            path[edge.t] = edge.cost;
//            imp[edge.t] = 1;
//        }
        while (!q.isEmpty()) {
            Edge e = q.poll();
            int e2 = e.t;
            if (checked[e2]) {
                continue;
            }
            checked[e2] = true;
            for (Edge ed : edges[e2]) {
                int pos = ed.t;
                long weight = ed.cost + path[e2];
                if (weight < path[pos]) {
                    path[pos] = weight;
                    imp[pos] = e2;
                    q.add(new Edge(pos, path[pos]));
                }

            }
        }

        int i = n;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(n);
        StringBuilder res = new StringBuilder();
        if(imp[n] == 0){
            System.out.println(-1);
        } else{
            while(i != 1){
                arr.add(imp[i]);
                i = imp[i];
            }
            for (int j = arr.size()-1; j >= 0; j--) {
                res.append(arr.get(j) + " ");
            }
            res.deleteCharAt(res.length()-1);
            System.out.println(res);
        }
    }

    /*****************************************************************
     ******************** BASIC READER *******************************
     *****************************************************************/
    static byte[] inbuf = new byte[4096];
    static int lenbuf = 0, ptrbuf = 0;

    static int readByte() {
        if (lenbuf == -1)
            throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return -1;
        }
        return inbuf[ptrbuf++];
    }

    static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b))
            ;
        return b;
    }

    static double nd() {
        return Double.parseDouble(ns());
    }

    static char nc() {
        return (char) skip();
    }

    static String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    static char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    static int ni() {
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

    static long nl() {
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

    static class Edge implements Comparable<Edge> {
        public int t;
        public long cost;

        public Edge(int t, long cost) {
            this.t = t;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            if ((this.cost - e.cost) < 0) {
                return -1;
            } else {
                if ((this.cost - e.cost) > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}