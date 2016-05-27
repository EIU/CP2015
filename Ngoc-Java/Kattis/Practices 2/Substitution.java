import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Substitution {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int T = ni();
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {
		int L = ni();

		int[] message = new int[L];
		for (int i = 0; i < L; i++) {
			message[i] = ni();
		}

		int[] encrypted = new int[L];
		for (int i = 0; i < L; i++) {
			encrypted[i] = ni();
		}

		int[] map = new int[101];
		for (int i = 1; i <= 100; i++) {
			map[i] = ni();
		}

		int[] starts = new int[L];
		int[] loops = new int[L];
		for (int i = 0; i < L; i++) {
			int letter = message[i];
			int encLetter = encrypted[i];
			int k = 0;
			while (letter != encLetter) {
				letter = map[letter];
				k++;
			}
			starts[i] = k;
			encLetter = message[i];
			if(k==0){
				letter = map[letter];
				k++;				
			}
			while (letter != encLetter) {
				letter = map[letter];
				k++;
			}
			loops[i] = k;
		}

		// ei = f(Si + t*Mi, mi);
		// We need to find k = Si + t * Mi for every i
		// If Ax + B = Cy + D. Let x0 = min(x) so we have y0 = min(y)
		// <=> A(x-x0) + Ax0 + B = C(y-y0) + Cy0 + D
		// <=> Ax' = Cy' <=> x' = C/gcd(A,C) * t
		// Thus we have new form: A*C/gcd(A,C) * t + Ax0 + B = Cy + D. y exist for every t

		long A = 1;
		long B = 0;
		for (int i = 0; i < L; i++) {
			long C = loops[i];
			long D = starts[i];
			if (C == 0) {
				continue;
			}
			int x0 = 0;
			while ((A * x0 + B - D) % C != 0) {
				x0++; // x0 < C
			}
			B += A * x0;
			A = A * C / gcd(A, C);
		}
		System.out.println(B);
	}

	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	/* ****************************************************************
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
