import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class P153PROC {
    static InputStream is;

    public static void main(String[] args) throws Exception {
        is = System.in;
        int T = ni();
        for (int t = 0; t < T; t++) {
            solve();
        }
    }

    static void solve() {
        n = ni();
        Person[] persons = new Person[n];
        for (int i = 0; i < n; i++) {
            int r1 = ni();
            persons[r1 - 1] = new Person(r1, ni(), ni());
        }
        Arrays.fill(minBIT, 0, n + 1, n + 1);

        int count = 0;
        for (int i = 1; i <= n; i++) {
            Person p = persons[i - 1];
            if (getMin(p.r2) > p.r3) {
                count++;
            }
            setMin(p.r2, p.r3);
        }
        System.out.println(count);
    }

    static class Person {
        int r1, r2, r3;
        public Person(int r1, int r2, int r3) {
            this.r1 = r1;
            this.r2 = r2;
            this.r3 = r3;
        }
    }

    static int n;
    static int[] minBIT = new int[100001];

    static void setMin(int i, int value) {
        for (; i <= n; i += (i & -i)) {
            minBIT[i] = Math.min(minBIT[i], value);
        }
    }

    static int getMin(int i) {
        int min = n + 1;
        for (; i > 0; i -= (i & -i)) {
            min = Math.min(min, minBIT[i]);
        }
        return min;
    }

    /*****************************************************************
     ******************** BASIC READER *******************************
     *****************************************************************/

    static byte[] inbuf = new byte[1 << 20];
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