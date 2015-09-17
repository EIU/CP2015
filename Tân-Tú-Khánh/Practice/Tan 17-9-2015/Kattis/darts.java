import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;

		solve();
	}

	static void solve() {
		//https://open.kattis.com/problems/dartscores
			
		int t = ni();
		for (int i = 0; i < t; i++) {
			int n = ni();
			long res = 0;
			for (int j = 0; j < n; j++) {
				int a = ni();
				int b = ni();
				double c = Math.sqrt(a*a + b*b);
				
					if (c <= 20.0) {
						res += 10;
					} else {
						if (c <= 40.0) {
							res += 9;
						} else {
							if (c <= 60.0) {
								res += 8;
							} else {
								if (c <= 80.0) {
									res += 7;
								} else {
									if (c <= 100.0) {
										res += 6;
									} else {
										if (c <= 120.0) {
											res += 5;
										} else {
											if (c <= 140.0) {
												res += 4;
											} else {
												if (c <= 160.0) {
													res += 3;
												} else {
													if (c <= 180.0) {
														res += 2;
													} else {
														if(c <= 200.0){
															res+= 1;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				

			
			System.out.println(res);
		}
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
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static double nd() {
		return Double.parseDouble(ns());
	}

	static char nc() {
		return (char) skip();
	}

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static int ni() {
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

	static long nl() {
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