import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
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
        Task2048 solver = new Task2048();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task2048 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int[][] a = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    a[i][j] = in.nextInt();
                }
            }
            int dir = in.nextInt();
            int[][] result = new int[4][4];
            if (dir == 0) {
                result = moveLeft(a);
            } else if (dir == 1) {
                result = moveUp(a);
            } else if (dir == 2) {
                result = moveRight(a);
            } else if (dir == 3) {
                result = moveDown(a);
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    out.print(result[i][j] + " ");
                }
                out.println();
            }
        }

        int[][] moveLeft(int[][] a) {
            boolean[][] stable = new boolean[4][4];
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (a[row][col] != 0) {
                        int pos = col - 1;
                        while (pos >= 0 && a[row][pos] == 0) {
                            pos--;
                        }
                        // edge
                        if (pos >= 0 && a[row][col] == a[row][pos] && !stable[row][pos]) {
                            a[row][pos] = a[row][col] * 2;
                            stable[row][pos] = true;
                            a[row][col] = 0;
                        } else if (pos + 1 == col) {
                            continue;
                        } else {
                            a[row][pos + 1] = a[row][col];
                            a[row][col] = 0;
                        }
                    }
                }
            }
            return a;
        }

        int[][] moveRight(int[][] a) {
            boolean[][] stable = new boolean[4][4];
            for (int row = 0; row < 4; row++) {
                for (int col = 4 - 1; col >= 0; col--) {
                    if (a[row][col] != 0) {
                        int pos = col + 1;
                        while (pos < 4 && a[row][pos] == 0) {
                            pos++;
                        }
                        // edge
                        if (pos < 4 && a[row][col] == a[row][pos] && !stable[row][pos]) {
                            a[row][pos] = a[row][col] * 2;
                            stable[row][pos] = true;
                            a[row][col] = 0;
                        } else if (pos - 1 == col) {
                            continue;
                        } else {
                            a[row][pos - 1] = a[row][col];
                            a[row][col] = 0;
                        }
                    }
                }
            }
            return a;
        }

        int[][] moveUp(int[][] a) {
            boolean[][] stable = new boolean[4][4];
            for (int col = 0; col < 4; col++) {
                for (int row = 0; row < 4; row++) {
                    if (a[row][col] != 0) {
                        int pos = row - 1;
                        while (pos >= 0 && a[pos][col] == 0) {
                            --pos;
                        }
                        if (pos >= 0 && a[pos][col] == a[row][col] && !stable[pos][col]) {
                            a[pos][col] = a[row][col] * 2;
                            stable[pos][col] = true;
                            a[row][col] = 0;
                        } else if (pos + 1 == row) {
                            continue;
                        } else {
                            a[pos + 1][col] = a[row][col];
                            a[row][col] = 0;
                        }
                    }
                }
            }
            return a;
        }

        int[][] moveDown(int[][] a) {
            boolean[][] stable = new boolean[4][4];
            for (int col = 0; col < 4; col++) {
                for (int row = 3; row >= 0; row--) {
                    if (a[row][col] != 0) {
                        int pos = row + 1;
                        while (pos < 4 && a[pos][col] == 0) {
                            ++pos;
                        }
                        if (pos < 4 && a[pos][col] == a[row][col] && !stable[pos][col]) {
                            a[pos][col] = a[row][col] * 2;
                            stable[pos][col] = true;
                            a[row][col] = 0;
                        } else if (pos - 1 == row) {
                            continue;
                        } else {
                            a[pos - 1][col] = a[row][col];
                            a[row][col] = 0;
                        }
                    }
                }
            }
            return a;
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

