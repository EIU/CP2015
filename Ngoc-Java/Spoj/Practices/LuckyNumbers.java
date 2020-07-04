import java.util.*;

//https://www.codingame.com/ide/puzzle/the-lucky-number
//https://www.codingame.com/ide/puzzle/the-fastest
// countLuckyNumber(67, false, false);
// countLuckyNumber(62, false, false);
// countLuckyNumber(69, false, false);
// countLuckyNumber(70, false, false);
// countLuckyNumber(71, false, false);
// for (int i = 0; i < 219; i++) {
// System.out.println(i + ": " + countLuckyNumber(i + 1, false, false));
// }
public class LuckyNumbers {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long L = in.nextLong();
		long R = in.nextLong();
		System.out.println(countLuckyNumber(R + 1, false, false) - countLuckyNumber(L, false, false));
	}

	static long countLuckyNumber(long upper, boolean had6, boolean had8) {
		long result = 0;
		int nDigit = (int) Math.log10(upper);
		long power10 = pow(10, nDigit);
		long number = power10;
		int[] values;
		int i = 0;
		for (; number <= upper; number += power10, i++) {
			if (had6 && had8 || had6 && i == 8 || had8 && i == 6) {
				continue;
			}
			if (i == 6 && !had8 || i == 8 && !had6) {
				result += pow(9, nDigit);
			} else if (i != 6 && had8 || i != 8 && had6) {
				result += pow(9, nDigit);
			} else if (i != 6 && i != 8 && !had6 && !had8) {
				result += 2 * countSingleLuckyNumber(nDigit);
			}
		}
		had6 |= upper / power10 == 6;
		had8 |= upper / power10 == 8;
		long remain = upper - (number - power10);
		if (remain > 0) {
			result += countLuckyNumber(remain, had6, had8);
		}
		return result;
	}

	static long countSingleLuckyNumber(int nDidit) {
		return pow(9, nDidit) - pow(8, nDidit);
	}

	static long pow(long base, int power) {
		if (power < 0) {
			return 0;
		}
		long result = 1;
		for (; power > 0; power--) {
			result *= base;
		}
		return result;
	}
}
