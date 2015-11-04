import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Primes_Teach2 {
    static InputStream is;

    public static void main(String[] args) throws Exception {
        is = System.in;
        int N;
        while ((N = ni()) > 0) {
            int[] primes = new int[N];
            for (int i = 0; i < N; i++) {
                primes[i] = ni();
            }

            int X = ni();
            int Y = ni();

            ArrayList<Integer> queue = new ArrayList<Integer>();
            queue.add(1);
            for (int p : primes) {
                int len = queue.size();
                for (int j = 0; j < len; j++) {
                    long current = queue.get(j);
                    for (int k = 0; k < 31; k++) {
                        current *= p;
                        if (current <= Y) {
                            queue.add((int) current);
                        } else {
                            break;
                        }
                    }
                }
            }

            Collections.sort(queue);
            StringBuffer bf = new StringBuffer();
            for (int i : queue) {
                if (i >= X && i <= Y) {
                    bf.append((bf.length() > 0 ? "," : "") + i);
                }
            }
            System.out.println(bf.length() == 0 ? "none" : bf);
        }
    }

    /*****************************************************************
     ******************** BASIC READER *******************************
     *****************************************************************/

    static byte[] inbuf = new byte[1024];
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
