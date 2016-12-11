import java.io.*;

public class Sequences01 {
	public static void main(String[] args) throws IOException {
		byte[] buffer = new byte[1 << 24];
		int last = System.in.read(buffer, 0, buffer.length);

		final long MOD = 1000000007;
		long powk = 1;
		long nZero = 0, nInversion = 0;
		last--;
		for (; last >= 0; last--) {
			switch (buffer[last]) {
			case '?':
				nInversion += nZero;
				nZero <<= 1;
				nZero += powk;
				powk <<= 1;
				break;
			case '1':
				nInversion += nZero;
				break;
			case '0':
				nZero += powk;
				break;
			}
			powk %= MOD;
			nInversion %= MOD;
			nZero %= MOD;
		}
		System.out.println(nInversion % MOD);
	}
}
