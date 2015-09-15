import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Animal_Hashed_MD5 {
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
			hashedValue = nextValue();
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
			hashedValue = nextValue();
		}

		if (Collections.binarySearch(hashedValues, hashedValue, comparator) >= 0) {
			result++;
		}
		return hashedValue;
	}

	static int position = 0;
	static char[] buffer;
	static MessageDigest crypt;

	static {
		try {
			crypt = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	static long nextValue() {
		StringBuffer bf = new StringBuffer();
		while (isDigit(buffer[position])) {
			bf.append(buffer[position++]);
		}

		crypt.reset();
		crypt.update(bf.toString().getBytes());
		return new BigInteger(1, crypt.digest()).longValue();
	}
	static boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
}
