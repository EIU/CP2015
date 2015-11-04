import java.util.Arrays;
import java.util.Scanner;

public class Stretching {

	static char[] chars;
	static int charLen;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		chars = sc.next().toCharArray();
		charLen = chars.length;

		for (int subLen = 1; subLen < charLen; subLen++) {
			if (charLen % subLen != 0) {
				continue;
			}
			for (int i = 0; i < charLen - subLen; i++) {
				if (check(i, subLen)) {
					System.out.println(new String(Arrays.copyOfRange(chars, i, i + subLen)));
					return;
				}
			}
		}
	}

	static boolean check(int i, int len) {
		int start = i;
		int end = i + len;
		while (start >= 0 || end < charLen) {
			int k = Math.max(start - len, 0);
			for (; k <= start; k++) {
				int p = 0;
				for (; p < len; p++) {
					int u = k + p;
					if (u >= start) {
						u = end + (u - start);
					}
					if (u >= charLen) {
						break;
					}
					if (chars[u] != chars[i + p]) {
						break;
					}
				}
				if (p == len) {
					break;
				}
			}
			if (k > start) {
				end = end + len - start + k;
				start = k;
			} else {
				return false;
			}
		}
		return true;
	}
}
