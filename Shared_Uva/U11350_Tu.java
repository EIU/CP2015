package uva;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class U11350 {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);
		int test = ni();
		while (test-- > 0) {
			String s = ns();
			long top = 1;
			long bot = 1;
			long ltop = 0;
			long lbot = 1;
			long rtop = 1;
			long rbot = 0;
			for(int i = 0; i < s.length(); i++){
				if(s.charAt(i) == 'L'){
					long temp1 = top;
					long temp2 = bot;
					top += ltop;
					bot += lbot;
					rtop = temp1;
					rbot = temp2;
				}
				if(s.charAt(i) == 'R'){
					long temp1 = top;
					long temp2 = bot;
					top += rtop;
					bot += rbot;
					ltop = temp1;
					lbot = temp2;
				}
			}
			System.out.println(top + "/" + bot);
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

}