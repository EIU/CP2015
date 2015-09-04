import java.util.Scanner;

public class PD_UnfairGame {

	static int[][] f2 = new int[][] { { 1, 6 }, { 2, 5 }, { 3, 4 } };
	static int[][] f3 = new int[][] { { 3, 5, 7 }, { 1, 6, 8 }, { 2, 4, 9 } };
	static int[][] f4 = new int[][] { { 4, 5, 7, 10 }, { 1, 6, 8, 11 }, { 2, 3, 9, 12 } };

	/*
	 * @Define: F2(n) = {{1+n, 6+n},{2+n, 5+n},{3+n, 4+n}}
	 * 
	 * @Define: F{{A},{B},{C}} + F{{X},{Y},{Z}} => F{{A X}, {B Y}, {C Z}}
	 * 
	 * @Lema: if Fi is WIN => Fi + F2(n) WIN
	 * 
	 * @WIN: (a1, a2, a3...) vs (b1, b2, b3...) = 1/n^2 (x1 + x2 + x3....). xi = |{bj where bj < ai}|
	 * 
	 * @Solution:
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n <= 2) {
			System.out.println("FAIL");
			return;
		}

		StringBuilder bf1 = new StringBuilder();
		StringBuilder bf2 = new StringBuilder();
		StringBuilder bf3 = new StringBuilder();
		int k = n % 2 == 1 ? 3 : 4;
		bf1.append(n % 2 == 1 ? "3 5 7" : "4 5 7 10");
		bf2.append(n % 2 == 1 ? "1 6 8" : "1 6 8 11");
		bf3.append(n % 2 == 1 ? "2 4 9" : "2 3 9 12");
		for (; k < n; k += 2) {
			int base = k * 3;
			bf1.append(" " + (base + 1) + " " + (base + 6));
			bf2.append(" " + (base + 2) + " " + (base + 5));
			bf3.append(" " + (base + 3) + " " + (base + 4));
		}
		System.out.println("WIN");
		System.out.println(bf1.toString());
		System.out.println(bf2.toString());
		System.out.println(bf3.toString());
	}
}
