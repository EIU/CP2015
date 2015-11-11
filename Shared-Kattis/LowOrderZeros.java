import java.util.*;

public class LowOrderZeros {

	public static void main(String[] args) throws Exception {
		prepare_numbers();
		solve();
	}

	static void solve() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while (n > 0) {
			System.out.println((modNone25[n] * powMod10(2, pow2[n] - pow5[n])) % 10);
			n = sc.nextInt();
		}
	}
	static int powMod10(int a, int p) {
		if (p == 0) {
			return 1;
		}
		int haft = powMod10(a, p / 2);
		if (p % 2 == 0) {
			return (int) (((long) haft * haft) % 10);
		}
		return (int) (((long) a * haft * haft) % 10);
	}

	static final int MAX = 1000001;
	static int[] numbers = new int[MAX];
	static int[] pow2 = new int[MAX];
	static int[] pow5 = new int[MAX];
	static int[] modNone25 = new int[MAX];

	static void prepare_numbers() {

		for (int i = 0; i < MAX; i++) {
			numbers[i] = i;
		}

		int p2 = 2;
		while (p2 < MAX) {
			for (int j = p2; j < MAX; j += p2) {
				numbers[j] /= 2;
				pow2[j] += 1;
			}
			p2 *= 2;
		}
		for (int i = 1; i < MAX; i++) {
			pow2[i] += pow2[i - 1];
		}

		int p5 = 5;
		while (p5 < MAX) {
			for (int j = p5; j < MAX; j += p5) {
				numbers[j] /= 5;
				pow5[j] += 1;
			}
			p5 *= 5;
		}
		for (int i = 1; i < MAX; i++) {
			pow5[i] += pow5[i - 1];
		}

		modNone25[1] = 1;
		for (int i = 2; i < MAX; i++) {
			modNone25[i] = (numbers[i] * modNone25[i - 1]) % 10;;
		}
	}
}
