import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class OddBinomialCoefficients {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		long[] pow3s = new long[64];
		long sum = 1;
		for (int i = 0; i < 64 && sum <= Long.MAX_VALUE / 3; i++) {
			pow3s[i] = sum;
			sum *= 3;
		}

		long n = nl();
		long result = 0;
		long pow = 1;
		long bitPow = 1l << 62;
		int bitIndex = 62;
		while (n > 0) {
			if (bitPow > n) {
				bitPow >>= 1;
				bitIndex--;
				continue;
			}
			result += pow3s[bitIndex] * pow;
			pow *= 2;
			n -= bitPow;
		}
		System.out.println(result);
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

	static long nl() {
		long num = 0;
		int b;
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
