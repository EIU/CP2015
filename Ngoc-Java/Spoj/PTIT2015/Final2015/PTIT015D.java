import java.util.Scanner;

class PTIT015D {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			long number = sc.nextLong();
			long result = solve(number);
			// System.out.println(number + " => " + result);
			System.out.println(result);
		}
	}

	/**
	 * @Lemma 1: any number A can represent in form Sum(2^xi * 3^yi) xi is strictly increase, and yi is strictly
	 *        decrease
	 * @Prove:
	 * @+ Note: 3^k mod 6 = 3, 2^k mod 6 = (2, 4)
	 * @+ A = 2^x * 3^y * A1 => Does not loose generality (If we can find solution of A1, then we can imply solution of
	 *    A)
	 * @+ k = max(i: 3^i <=n), t = max(j: n - 3^k - 2^t divisible by 6)
	 * @+ n - 3^k > 2n/3; S = n - 3^k - 2^t > 2n/3 * 3/4 = n/2;
	 * @+ S is divisible by 2 and 3 & S = Sum(2^ui * 3^vi) => x1 > u1 & vm < vn
	 * @+ Prolem will become: find a + b + c = n & a < b < c so that S(a) + S(b) + S(c) max
	 * @Prove: we have f(...xyzt) = f(...xyz - {0|1|2}) + {0|1|2}*10 + t
	 * @+ We can prove f(xyz) + t < f(...xyz-1) + 10 + t < f(...xyz-2) +20 + t
	 * @+ There are some special cases: x8, x9 => x-1 + 18, 19. All loop generate number (27)*, {0|1|2}(27)* = {2|3|4}7, {25|26}(27)*  
	 * @+ Example 1: 36 => (1)(26) => (0|1|0)x(9|9|8) => 09 + 19 + 08
	 */
	static long solve(long number) {
		if (number < 6) {
			return -1;
		}
		long result = 0;
		boolean all27 = true;
		while (number > 0) {
			if (all27) {
				switch ((int) number) {
				case 25:
					return result + 15 + 1;
				case 26:
					return result + 16 + 1;
				case 27:
					return result + 17 + 1;
				case 37:
					return result + 17 + 2;
				case 47:
					return result + 17 + 3;
				}
			}
			long lastDigit = number % 10;
			number /= 10;
			long borrow = (number >= 2) ? 2 : (number >= 1 ? 1 : 0);
			if (lastDigit > 7) {
				borrow = Math.min(borrow, 1);
			}
			all27 &= (borrow * 10 + lastDigit == 27);
			result += borrow * 10 + lastDigit;
			number -= borrow;
		}
		return result;
	}
}
