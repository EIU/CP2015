import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SwapSpace {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;
		int n = ni();
		Disk[] disks = new Disk[n];
		for (int i = 0; i < n; i++) {
			disks[i] = new Disk(ni(), ni());
		}
		Arrays.sort(disks);
		long extra = 0;
		long sum = 0;
		for (int i = 0; i < n; i++) {
			Disk disk = disks[i];
			if (sum < disk.oldSpace) {
				extra += disk.oldSpace - sum;
				sum = disk.oldSpace;
			}
			sum -= disk.oldSpace;
			sum += disk.newSpace;
		}
		System.out.println(extra);
	}

	static class Disk implements Comparable<Disk> {
		public int oldSpace;
		public int newSpace;
		public int type;

		public Disk(int oldSpace, int newSpace) {
			this.oldSpace = oldSpace;
			this.newSpace = newSpace;
			type = oldSpace > newSpace ? 1 : -1;
		}

		@Override
		public int compareTo(Disk arg0) {
			int dType = type - arg0.type;
			if (dType != 0) {
				return dType;
			}
			if (type == -1) {
				return oldSpace - arg0.oldSpace;
			}
			return arg0.newSpace - newSpace;
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
