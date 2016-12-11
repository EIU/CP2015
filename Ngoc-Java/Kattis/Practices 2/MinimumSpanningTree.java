import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MinimumSpanningTree {
    static InputStream is;

    public static void main(String[] args) throws Exception {
        is = System.in;

        // long s = System.currentTimeMillis();

        List<int[]> edges = new ArrayList<int[]>();

        int[] graphSizes = new int[200];
        ArrayList<int[]>[] trees = new ArrayList[200];
        for (int i = 0; i < 200; i++) {
            trees[i] = new ArrayList<int[]>();
        }

        int[] treeLens = new int[200];

        int HAFT = 20001, MAX = 40002;
        ArrayList<Integer>[] lenMap = new ArrayList[MAX];
        for (int i = 0; i < MAX; i++) {
            lenMap[i] = new ArrayList<Integer>();
        }

        int nGraph = 0;
        int n = 0;
        int startV = 0;
        while ((n = ni()) > 0) {
            int m = ni();
            graphSizes[nGraph] = n;

            for (int i = 0; i < m; i++) {
                int u = ni() + startV, v = ni() + startV;
                if (u < v) {
                    edges.add(new int[]{u, v, nGraph});
                } else {
                    edges.add(new int[]{v, u, nGraph});
                }
                lenMap[HAFT + ni()].add(edges.size() - 1);
            }

            nGraph++;
            startV += n;
        }

        int[] parents = new int[startV];
        Arrays.fill(parents, -1);

        for (int i = 0; i < MAX; i++) {
            for (int index : lenMap[i]) {
                int[] edge = edges.get(index);
                int p0 = getParent(parents, edge[0]);
                int p1 = getParent(parents, edge[1]);

                if (p0 == p1) {
                    continue;
                }

                trees[edge[2]].add(edge);
                treeLens[edge[2]] += i - HAFT;

                if (p0 == edge[0]) {
                    parents[p0] = p1;
                } else {
                    parents[p1] = p0;
                }
            }
        }

        StringBuilder outBf = new StringBuilder();
        startV = 0;
        for (int i = 0; i < nGraph; i++) {
            ArrayList<int[]> tree = trees[i];
            if (tree.size() < graphSizes[i] - 1) {
                outBf.append("Impossible\n");
            } else {
                tree.sort(dictionaryComparator);

                outBf.append(treeLens[i] + "\n");
                for (int[] e : tree) {
                    outBf.append((e[0] - startV) + " " + (e[1] - startV) + "\n");
                }
            }
            startV += graphSizes[i];
        }

        System.out.print(outBf);

        // System.out.println((System.currentTimeMillis() - s) + " ms");
    }

    static Comparator<int[]> dictionaryComparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] e1, int[] e2) {
            int cmp = e1[0] - e2[0];
            if (cmp == 0) {
                cmp = e1[1] - e2[1];
            }
            return cmp;
        }
    };

    static int getParent(int[] parents, int i) {
        if (parents[i] == -1) {
            return i;
        }
        return parents[i] = getParent(parents, parents[i]);
    }

    /* ****************************************************************
     ******************** BASIC READER *******************************
     *****************************************************************/

    static byte[] inbuf = new byte[1024 << 4];
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
        while ((b = readByte()) != -1 && isSpaceChar(b));
        return b;
    }

    static int ns() {
        int b = skip();
        int result = 0;
        while (!(isSpaceChar(b))) {
            result |= (1 << (b - 'a'));
            b = readByte();
        }
        return result;
    }

    static int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
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
