import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

//https://open.kattis.com/problems/animal
public class Animal_Hashed_New {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 20);

		int n = Integer.parseInt(reader.readLine());

		buffer = reader.readLine().toCharArray();

		readTree1();
		hashedValues.sort(comparator);

		buffer = reader.readLine().toCharArray();
		position = 0;
		testTree2();

		System.out.println(result);

	}

	static ArrayList<Long> hashedValues = new ArrayList<Long>();
	static int result = 0;
	static Comparator<Long> comparator = new Comparator<Long>() {
		@Override
		public int compare(Long arg0, Long arg1) {
			return arg0.compareTo(arg1);
		}
	};

	static long readTree1() {
		long hashedValue;
		if (buffer[position] == '(') {
			position++; // (
			long left = readTree1();
			position++; // ,
			long right = readTree1();
			position++; // )
			hashedValue = left ^ right;
		} else {
			hashedValue = nextNumberHashed();
		}
		hashedValues.add(hashedValue);

		return hashedValue;
	}

	static long testTree2() {
		long hashedValue;
		if (buffer[position] == '(') {
			position++; // (
			long left = testTree2();
			position++; // ,
			long right = testTree2();
			position++; // )
			hashedValue = left ^ right;

		} else {
			hashedValue = nextNumberHashed();
		}

		if (Collections.binarySearch(hashedValues, hashedValue, comparator) >= 0) {
			result++;
		}
		return hashedValue;
	}

	static int position = 0;
	static char[] buffer;
	static long MUL = 2038072819L; // 1301081
	static long MUL2 = 3121238891L;
	static long MOD = 32416190071L; // 22801763489L;
	static long nextNumberHashed() {
		long value = 1;
		while (isDigit(buffer[position])) {
			value = (value * MUL + buffer[position++] * MUL2) % MOD;
		}

		return value;
	}
	static boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
}

// http://www.bigprimes.net/archive/prime/14000000/ => should note some primes of n digits (n=1..18)
// Note: BigInteger can generate random primes
// https://primes.utm.edu/
