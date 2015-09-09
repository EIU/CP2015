import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    static boolean isMultiTest = false;

    public static void main(String[] args) {
        OutputStream outputStream = System.out;
        InputStream inputStream = System.in;
        // InputFast in = new InputFast();
        Input in = new Input(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        if (isMultiTest) {
            int testNumber = in.nextInt();
            for (int i = 1; i <= testNumber; i++) {
                solver.solve(i, in, out);
            }
        } else {
            solver.solve(1, in, out);
        }
        out.close();
    }
}

class Task {
    final static int MOD = 1000000009;
    final int LIM = 1000001;
    HashMap<String, Word> map;
    Word[] w;

    public void solve(int testNumber, Input in, PrintWriter out) {
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.nextLine();
        }
        String need = in.nextLine();
        w = new Word[n];
        int i;
        map = new HashMap<String, Task.Word>();
        for (i = 0; i < n; i++) {
            String[] t = s[i].split(" ");
            String key = "";
            String val = t[t.length - 1];
            List<String> extend = new ArrayList<String>();
            for (int j = 0; j < t.length; j++) {
                if (t[j].equals("def")) {
                    key = t[j + 1];
                    j++;
                } else if (t[j].equals("with")) {
                    extend.add(t[j + 1]);
                    j++;
                }
            }
            map.put(key, new Word(key, val, extend));
            w[i] = new Word(key, val, extend);
            if (key.equals(need))
                break;
        }
        String ans = rec(w[i]);

        out.println(ans);
    }

    private String rec(Word word) {
        StringBuilder sb = new StringBuilder();
        List<String> extend = word.extend;
        int n = extend.size();

        for (int j = n - 1; j >= 0; j--) {
            String wrd = extend.get(j);
            if (map.containsKey(wrd)) {
                Word w = map.get(wrd);
                sb.insert(0, rec(w));
                map.remove(wrd);
            }
        }
        if (map.containsKey(word.key)) {
            sb.insert(0, word.val + " ");
            map.remove(word.key);
        }
        return sb.toString();
    }

    class Word {
        String key;
        String val;
        List<String> extend;

        public Word(String key, String val, List<String> extend) {
            this.key = key;
            this.val = val;
            this.extend = extend;
        }
    }
}

class InputFast {
    static InputStream is = System.in;
    static private byte[] buffer = new byte[1024];
    static private int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1)
            throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(buffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return -1;
        }
        return buffer[ptrbuf++];
    }

    private boolean isSpace(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int read() {
        int b;
        while ((b = readByte()) != -1 && isSpace(b))
            ;
        return b;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public char nextChar() {
        return (char) read();
    }

    public String next() {
        int b = read();
        StringBuilder sb = new StringBuilder();
        while (!(isSpace(b))) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public char[] next(int n) {
        char[] buf = new char[n];
        int b = read(), p = 0;
        while (p < n && !(isSpace(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    public int nextInt() {
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

    public long nextLong() {
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

}

class Input {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public Input(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
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

    public String nextLine() {
        String s = "";
        try {
            s = reader.readLine();
        } catch (IOException e) {
            try {
                throw new IOException();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return s;
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}