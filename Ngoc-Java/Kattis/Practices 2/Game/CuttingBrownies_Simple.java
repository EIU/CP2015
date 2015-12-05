import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CuttingBrownies_Simple {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		for (int i = 0; i < n; i++) {
			int B = ni();
			int D = ni();
			harryQueue.clear();
			vickyQueue.clear();
			if (ns().equals("Harry")) {
				System.out.println(B + " " + D + " " + "Harry can" + (play(B, D) ? "" : "not") + " win");
			} else {
				System.out.println(B + " " + D + " " + "Vicky can" + (play(D, B) ? "" : "not") + " win");
			}
		}
	}

	static PriorityQueue<Game> harryQueue = new PriorityQueue<Game>(100000, new Comparator<Game>() {
		@Override
		public int compare(Game arg0, Game arg1) {
			return arg0.B - arg1.B;
		}
	});

	static PriorityQueue<Game> vickyQueue = new PriorityQueue<Game>(100000, new Comparator<Game>() {
		@Override
		public int compare(Game arg0, Game arg1) {
			return arg0.D - arg1.D;
		}
	});

	static void addGame(Game game) {
		if (game.D > 1) {
			harryQueue.add(game);
		}
		if (game.B > 1) {
			vickyQueue.add(game);
		}
	}

	static boolean play(int B, int D) {

		addGame(new Game(B, D));

		while (!harryQueue.isEmpty() && !vickyQueue.isEmpty()) {
			Game topGame = null;
			while (!harryQueue.isEmpty() && !(topGame = harryQueue.poll()).isValid);
			if (topGame != null) {
				topGame.isValid = false;
				addGame(new Game(topGame.B, topGame.D / 2));
				addGame(new Game(topGame.B, (topGame.D + 1) / 2));
			}
			topGame = null;
			while (!vickyQueue.isEmpty() && !(topGame = vickyQueue.poll()).isValid);
			if (topGame != null) {
				topGame.isValid = false;
				addGame(new Game(topGame.B / 2, topGame.D));
				addGame(new Game((topGame.B + 1) / 2, topGame.D));
			}
		}

		return !harryQueue.isEmpty();
	}

	static class Game {
		public int B;
		public int D;
		public boolean isValid = true;

		public Game(int B, int D) {
			this.B = B;
			this.D = D;
		}
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

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
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
