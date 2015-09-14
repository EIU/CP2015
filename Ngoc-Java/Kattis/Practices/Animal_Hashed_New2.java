import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Animal_Hashed_New2 {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 20);

		reader.readLine();

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
	static long nextNumberHashed() {
		long v1 = 33;
		long v2 = 1000000007;
		while (isDigit(buffer[position])) {
			int c = buffer[position++];
			v1 = (v1 << 5) + v1 + (c << 4) + c;
			c = 1000000007 - c;
			v2 = (v2 << 6) + (v2 << 1) + v2 + (c << 2) + c;
		}

		return (v1 << 32) + v2;
	}
	static boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
}

// http://www.bigprimes.net/archive/prime/14000000/ => should note some primes of n digits (n=1..18)
// Note: BigInteger can generate random primes
// https://primes.utm.edu/
