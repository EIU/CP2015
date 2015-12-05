import java.io.IOException;

public class Avoidland_Improve {

	public static void main(String[] args) throws IOException {
		lenbuf = System.in.read(inbuf);
		int n = ni();
		int[] verticals = new int[n + 1];
		int[] horizontals = new int[n + 1];

		verticals[0] = horizontals[0] = 1;
		for (int i = 0; i < n; i++) {
			verticals[ni()]++;
			horizontals[ni()]++;
		}

		System.out.println(solve(verticals) + solve(horizontals));
	}

	private static long solve(int[] numbers) {
		long result = 0;
		int count0 = 0;
		int countG1 = 0;
		for (int number : numbers) {
			result += count0 + countG1;
			if (number == 0) {
				count0++;
			}
			if (number > 1) {
				countG1 += number - 1;
			}
			if (count0 > countG1) {
				count0 -= countG1;
				countG1 = 0;
			} else {
				countG1 -= count0;
				count0 = 0;
			}
		}
		return result;
	}

	static byte[] inbuf = new byte[1 << 24];
	static int lenbuf = 0, ptrbuf = 0;

	static int ni() {
		int num = 0, b;
		while ((b = inbuf[ptrbuf++]) != 0 && !('0' <= b));

		while (true) {
			if ('0' <= b) {
				num = (num << 3) + (num << 1) + (b - '0');
			} else {
				return num;
			}
			b = inbuf[ptrbuf++];
		}
	}
}
