import java.util.Scanner;

public class DigitSum_Base0 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			String from = sc.next();
			String to = sc.next();
			int sumFrom = 0;
			for (char c : from.toCharArray()) {
				sumFrom += c - '0';
			}
			System.out.println(sumDigit(to) - sumDigit(from) + sumFrom);
		}
	}
	static long sumDigit(String to) {
		int len = to.length();
		long result = 0;

		// 0000-6789
		// 0000-5999, 6000-6699, 6700-6779, 6780-6789
		// [0..5] x [000-999]
		int sumDigit = 0;
		long pow = (long) Math.pow(10, len - 1);
		for (int i = 0; i < len; i++) {
			int d = to.charAt(i) - '0';
			result += d * pow * sumDigit;
			result += pow * d * (d - 1) / 2 + d * pow * (len - 1 - i) * 45 / 10;
			sumDigit += d;
			pow /= 10;
		}
		result += sumDigit;

		return result;
	}
}
