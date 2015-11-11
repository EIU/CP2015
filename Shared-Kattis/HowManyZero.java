import java.util.Scanner;

public class HowManyZero {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//		System.out.println(countZero(1));
		//		System.out.println(countZero(0));
		//		System.out.println(countZero(500));
		//		System.out.println(countZero(99));
		//		System.out.println(countZero(50123));
		//		System.out.println(countZero(100000));
		long n;
		while ((n = sc.nextLong()) >= 0) {
			long m = sc.nextLong();
			System.out.println(countZero(m) - (n > 0 ? countZero(n - 1) : 0));
		}
	}

	//	5023
	//	....
	//	5000
	//	4999
	//	....
	//	1000
	//	 999
	//	 100
	//	  99
	//	  10
	//	   9
	//	   0
	static long countZero(long toNumber) {
		if (toNumber == 0) {
			return 1;
		}

		long result = 0;
		int nDigit = (int) Math.log10(toNumber);
		long pow10 = (long) Math.pow(10, nDigit);
		long firstDigit = toNumber / pow10;

		long upNumber = toNumber - firstDigit * pow10 + 1;
		long pow = 1;
		// [[0..01..1...9..9][0..01..1...9..9]..[0..01..1...d..d]]
		for (int i = 0; i < nDigit; i++) {
			result += upNumber / (pow * 10) * pow + Math.min(upNumber % (pow * 10), pow);
			pow *= 10;
		}

		// [1..4]x[000-999]
		result += (firstDigit - 1) * (nDigit - 0) * pow10 / 10;

		//	 999
		//	 100
		//	  99
		//	  10
		//	   9
		//	   0
		while (pow10 >= 10) {
			pow10 /= 10;
			nDigit -= 1;
			result += 9 * nDigit * pow10 / 10;
		}

		// result += countZero(pow - 1);

		return result + 1; // Include zero
	}
}

// function get(n) {n = n.toString(); var c= 0; for(var i=0;i<n.length;i++) if(n.charAt(i) == '0') c++; return c;}; var c = 0; for(var i=0;i<50123;i++) c+=get(i); c;
