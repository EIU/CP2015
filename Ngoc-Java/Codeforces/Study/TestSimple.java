import java.util.Arrays;

public class TestSimple {
	public static void main(String[] args) {
		int LEN = 10;
		// char[] arr = new char[LEN];
		// Arrays.fill(arr, 'a');
		//
		// String s = new String(arr);
		// String b = new String(arr);
		//
		// check2(s, b, 0, s.length(), 0, b.length());

		check3(0, LEN, 0, LEN);
	}

	static void check(String a, String b) {
		if (a.length() <= 2 || b.length() <= 2) return;
		String s1 = a.substring(0, a.length() / 2);
		String s2 = a.substring(a.length() / 2, a.length());
		String b1 = b.substring(0, b.length() / 2);
		String b2 = b.substring(b.length() / 2, b.length());

		check(s1, b1);
		check(s2, b2);
		check(s2, b1);
		check(s1, b2);
	}

	static void check2(String a, String b, int i1, int i2, int j1, int j2) {
		if (i2 - i1 <= 2 || j2 - j1 <= 2) return;

		check2(a, b, i1, (i1 + i2) >> 1, j1, (j1 + j2) >> 1);
		check2(a, b, i1, (i1 + i2) >> 1, (j1 + j2) >> 1, j2);

		check2(a, b, (i1 + i2) >> 1, i2, j1, (j1 + j2) >> 1);
		check2(a, b, (i1 + i2) >> 1, i2, (j1 + j2) >> 1, j2);

	}

	static void check3(int i1, int i2, int j1, int j2) {
		if (i2 - i1 <= 2 || j2 - j1 <= 2) return;

		System.out.println(i1 + " " + i2 + " - " + j1 + ' ' + j2);

		check3(i1, (i1 + i2) >> 1, j1, (j1 + j2) >> 1);
		check3(i1, (i1 + i2) >> 1, (j1 + j2) >> 1, j2);

		check3((i1 + i2) >> 1, i2, j1, (j1 + j2) >> 1);
		check3((i1 + i2) >> 1, i2, (j1 + j2) >> 1, j2);

	}
}
