import java.util.Scanner;

public class DigitSum {

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

		// 0001-6789
		// 0001-6000, 6001-6700, 6701-6780, 6781-6789
		// [0..5] x [001-999] & 1000, 2000...6000
		
		int sumDigit = 0;
		long pow = (long) Math.pow(10, len - 1);
		for (int i = 0; i < len; i++) {
			int d = to.charAt(i) - '0';
			result += d * pow * sumDigit;
			result += (pow - 1) * d * (d - 1) / 2 // 1xxx-5xxx
					+ d * (d + 1) / 2	// 1000-5000
					+ d * pow * (len - 1 - i) * 45 / 10; // 5 * (001-999)
			sumDigit += d;
			pow /= 10;
		}

		return result;
	}
}
