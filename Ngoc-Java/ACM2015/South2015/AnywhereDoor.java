import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AnywhereDoor {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int N = ni();

		int[] pxs = new int[N];
		int[] pys = new int[N];
		for (int i = 0; i < N; i++) {
			pxs[i] = ni();
			pys[i] = ni();
		}

		int sx = ni();
		int sy = ni();
		int ex = ni();
		int ey = ni();

		// He phuong trinh nghiem nguyen

		// Case 1:
		// 2 * Sum(pxi * ai) = sx + ex
		// 2 * Sum(pyi * ai) = sy + ey
		// Sum(pxi) = 1

		// Case 2:
		// 2 * Sum(pxi * ai) = sx - ex
		// 2 * Sum(pyi * ai) = sy - ey
		// Sum(pxi) = 0

		// Can xu ly 4 truong hop
		// N = 1:
		// N = 2:
		// N = 3: Giai he phuong trinh thong thuong
		// N >= 4: Sau khi bien doi con N-2 bien => A * a0 + B * a1 + ... = C <=> C = k * gcd(A, B,...). k có thể là giá trị bất kỳ, nên tồn tại k đủ tốt để
		// thỏa mãn hệ 3 phương trình
	}

	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
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
