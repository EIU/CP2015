import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class Main {
	static InputStream is;

	static class component implements Comparable<component>{
		public int stre;
		public int a;
		public int b;
		public component(int stre, int a, int b){
			this.stre = stre;
			this.a = a;
			this.b = b;
			
		}
		public int compareTo(component c){
			return -(this.stre - c.stre);
		}
	}
	public static void main(String[] args) {
		is = System.in;

		solve();
	}

	static void solve() {
		PriorityQueue<component> q  = new PriorityQueue<component>();
		int n = ni();
		for (int i = 0; i < 2*n-1; i++) {
			for (int j = 0; j < i+1; j++) {
				int z = ni();
				q.add(new component(z, i+2, j+1));
				//System.out.println(z);
			}
		}
		boolean[] check = new boolean[2*n+1];
		int[] arr = new int[2*n+1];
		while(!q.isEmpty()){
			component c = q.poll();
			
			if(check[c.a] || check[c.b]){
				continue;
			}
//			System.out.println(c.a + " ");
//			System.out.println(c.b);
			check[c.a] = true;
			check[c.b] = true;
			arr[c.a] = c.b;
			arr[c.b] = c.a;
			
		}
		StringBuilder res = new StringBuilder();
		for (int i = 1; i < arr.length; i++) {
			res.append(arr[i] + " ");
		}
		res.deleteCharAt(res.length() -1);
		System.out.println(res);
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
