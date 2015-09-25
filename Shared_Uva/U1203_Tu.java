package uva;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class U1203 {
	static InputStream is;

	public static void main(String[] args) {
		is = System.in;
		ArrayList<Room> a = new ArrayList<Room>();
		PriorityQueue<Room> queue = new PriorityQueue<Room>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		String s = "";
		while (!s.equals("#")) {
			s = ns();
			if (s.equals("#")) {
				break;
			}
			int id = ni();
			int per = ni();
			queue.add(new Room(id, per));
			map.put(id, per);
		}
		int k = ni();
		int count = 0;
		while (true) {
			if (count == k) {
				break;
			}
			a.add(queue.poll());
			queue.add(new Room(a.get(a.size() - 1).id, a.get(a.size() - 1).period + map.get(a.get(a.size() - 1).id)));
			count++;
		}
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).id);
		}
	}

	static class Room implements Comparable<Room> {

		public int id;
		public long period;

		public Room(int id, long period) {
			this.id = id;
			this.period = period;
		}

		@Override
		public int compareTo(Room o) {
			if(this.period > o.period){
				return 1;
			}
			else if(this.period < o.period){
				return -1;
			}
			else{
				return this.id - o.id;
			}
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
