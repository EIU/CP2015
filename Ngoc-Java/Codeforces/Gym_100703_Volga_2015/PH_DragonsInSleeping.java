import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PH_DragonsInSleeping {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "9 3 1 1 1 1 2 2 2 2 2";

	public static void main(String[] args) throws Exception {
		is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		tr(System.currentTimeMillis() - s + "ms");
	}

	/**
	 * @solution:
	 */
	static void solve() {
		int n = ni();
		int m = ni();

		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		for (int i = 0; i <= n; i++) {
			vertices.add(new Vertex(i));
		}

		for (int i = 0; i < m; i++) {
			Vertex v1 = vertices.get(ni());
			Vertex v2 = vertices.get(ni());
			v1.add(v2);
			v2.add(v1);
		}

		Vertex v = vertices.get(1);
		markLabel(v, 0);
		
		// Not yet completed
	}

	static int markLabel(Vertex v, int label) {
		v.label = label++;
		int returnLabel = label;
		for (Vertex next : v.adjacents) {
			if (next.label == 0) {
				returnLabel = Math.min(returnLabel, markLabel(next, label));
			} else {
				returnLabel = Math.min(returnLabel, next.label);
			}
		}
		return returnLabel;
	}

	static class Vertex {
		public int id;
		public ArrayList<Vertex> adjacents = new ArrayList<Vertex>();

		public int label;
		public ArrayList<Vertex> adjComponent = new ArrayList<Vertex>();

		public int newNode;

		public Vertex(int id) {
			this.id = id;
		}

		public void add(Vertex v) {
			adjacents.add(v);
		}

	}
	/*****************************************************************
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

	static boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	static void tr(Object... o) {
		if (!oj) {
			System.out.println(Arrays.deepToString(o));
		}
	}
}
