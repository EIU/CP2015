import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	final static int MAXSIZE = 30005;

	static int[] mapValue = new int[MAXSIZE];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		initMap2();

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int value = sc.nextInt();
			System.out.println(mapValue[value]);
		}
	}

	static void initMap2() {
		List<List<Integer>> mapDivisors = new ArrayList<List<Integer>>();
		for (int i = 0; i < MAXSIZE; i++) {
			mapDivisors.add(new ArrayList<Integer>());
		}

		int maxSqrt = (int) Math.sqrt(MAXSIZE) + 1;
		for (int i = 2; i < maxSqrt; i++) {
			for (int j = i * i; j < MAXSIZE; j += i) {
				mapDivisors.get(j).add(i);
			}
		}

		// mapDivisors[i] does not contains its divisors which are greater than its square root!
		for (int i = 0; i < MAXSIZE; i++) {
			List<Integer> divisors = mapDivisors.get(i);
			if (divisors.size() == 0) {
				divisors.add(i);
			}
		}

		// Solution: map[i] = map[i-1] + |{sk = i*j, sk has not appeared}|
		// i = s1*s2 (s1 < s2)
		// => j = {0, 1, ... , i/d0-1} are excluded
		// j > i/d0: for every u1*u2 < i && u1 < dk < u2 => u1*u2 are excluded
		mapValue[0] = 1;
		mapValue[1] = mapValue[0] + 1;
		int[] flags = new int[MAXSIZE];
		for (int i = 2; i < MAXSIZE; i++) {
			List<Integer> divisors = mapDivisors.get(i);
			int minDivisor = divisors.get(0);
			int start = i / minDivisor;

			for (int j = 0; j < divisors.size(); j++) {
				int divj = divisors.get(j);
				int rev = i / divj;
				int u1 = 2;
				for (; u1 < divj; u1++) {
					for (int u2 = rev - 1; u2 >= u1; u2--) {
						int u = u1 * u2;
						flags[u] = i;
						if (u < start) { // => Improve performance
							break;
						}
					}
				}
			}

			int newValue = 0;
			for (; start <= i; start++) {
				if (flags[start] != i) {
					newValue++;
				}

			}
			mapValue[i] = mapValue[i - 1] + newValue;
		}
	}

	static void initMap() {
		// minDivisors[i] is minimum divisor of i
		int[] minDivisors = new int[MAXSIZE];

		int maxSqrt = (int) Math.sqrt(MAXSIZE) + 1;

		for (int i = 2; i < maxSqrt; i++) {
			int j = i * i;
			for (; j < MAXSIZE; j += i) {
				if (minDivisors[j] == 0) {
					minDivisors[j] = i;
				}
			}
		}

		for (int i = 0; i < MAXSIZE; i++) {
			if (minDivisors[i] == 0) {
				minDivisors[i] = i;
			}
		}

		// Solution
		mapValue[0] = 1;
		mapValue[1] = mapValue[0] + 1;

		for (int i = 2; i < MAXSIZE; i++) {
			int minDivisor = minDivisors[i];
			int exists = i / minDivisor; // 0, 1...
			mapValue[i] = mapValue[i - 1] + (i + 1 - exists);
		}
	}
}
