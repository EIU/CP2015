import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class TrainSorting {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		solve();
	}

	static void solve() {
		int n = ni();
		int[] weights = new int[n];
		int[] increases = new int[n];
		int[] descreses = new int[n];

		for (int i = 0; i < n; i++) {
			weights[i] = ni();
		}

		int ans = 0;
		for (int i = n - 1; i >= 0; i--) {
			int inc = 1;
			int des = 1;
			for (int j = n - 1; j > i; j--) {
				if (weights[i] < weights[j]) {
					inc = Math.max(inc, increases[j] + 1);
				}
				if (weights[i] > weights[j]) {
					des = Math.max(des, descreses[j] + 1);
				}
			}
			increases[i] = inc;
			descreses[i] = des;
			ans = Math.max(ans, inc + des - 1);
		}
		System.out.println(ans);
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
