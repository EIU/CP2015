import java.util.*;

public class OnlineExam {
	static int n = 5000;
	static int k = 2000;
	static int x = 100;
	static Scanner sc = new Scanner(System.in);
	static char[] fixed = new char[n];
	static int nfixed = 0;

	public static void main(String[] args) {
		test();
		// solve();

		//		for (int i = 15; i <= 100; i += (i < 100 ? 11 : (i < 500 ? 51 : (i < 1000 ? 101 : 501)))) {
		//			int[] result = statistic(i);
		//			StringBuilder bf = new StringBuilder();
		//			int count = 0;
		//			long total = 0;
		//			for (int j = 0; j < result.length; j++) {
		//				if (result[j] != 0) {
		//					count += result[j];
		//					total += j * result[j];
		//				}
		//				bf.append(result[j] + " ");
		//			}
		//			System.out.println(i + " " + ((total * 100 / count) / 100d) + " " + bf);
		//		}
	}

	static void test() {

		int x = 100000;
		int n = 15, k = 41;
		int max = 0;
		int min = n + 1;
		int total = 0;

		for (int i = 0; i < x; i++) {
			int err = 0;
			for (int j = 0; j < n; j++) {
				if (Math.random() < 0.5) {
					err++;
					//					if (err == k) {
					//						min = Math.min(min, j);
					//						max = Math.max(max, j);
					//						break;
					//					}
				}
			}
			total += Math.abs(err - n / 2);
		}
		System.out.println(min + " " + max + " " + (total * 200 / x));
	}

	static int[] statistic(int n) {
		int REPEAT = 100000;
		int[] statistics = new int[100];
		for (int r = 0; r < REPEAT; r++) {
			int count = 0;
			for (int i = 0; i < n; i++) {
				count += (Math.random() < 0.5 ? 0 : 1);
			}
			statistics[Math.min(99, Math.abs(2 * count - n))]++;
		}
		return statistics;
	}

	static char[] minAtt;
	static char[] maxAtt;
	static int minResult = n + 1;
	static int maxResult = 0;

	static void firstAttempt(int m) {
		for (; m > 0; m--) {
			char[] attemp = new char[n];
			for (int i = 0; i < n; i++) {
				if (fixed[i] == '\0') {
					attemp[i] = Math.random() < 0.5 ? '0' : '1';
				} else {
					attemp[i] = fixed[i];
				}
			}

			int result = print(attemp);
			if (result > maxResult) {
				maxResult = result;
				maxAtt = attemp;
			}
			if (result < minResult) {
				minResult = result;
				minAtt = attemp;
			}
		}
	}

	static void solve() {

		//		n = 50;
		//		k = 20;
		//		x = 10;

		int m = 1;
		firstAttempt(m);
		x -= m;

		int result = 0;
		char[] attempt = null;
		if (Math.abs(2 * k - minResult) < Math.abs(maxResult - 2 * k) + 4) {
			attempt = maxAtt;
			result = maxResult;
		}
		else {
			attempt = minAtt;
			result = minResult;
		}

		if (result < 2 * k) {
			revert(attempt, 0, result - 1);
			loadFixed(attempt, result - 1);
			result = 4 * k - result;
			// result = print(attempt);
			// x--;
		}
		loadFixed(attempt, result - 1);

		int improve = 0;
		int reIndex = 0;
		for (; x > 0; x--) {
			int start = reIndex;
			reIndex = Math.min(reIndex + 45, result - 1);
			revert(attempt, start, reIndex);
			int newResult = print(attempt);
			loadFixed(attempt, newResult - 1);
			if (newResult <= result + 1) { //?
				revert(attempt, start, reIndex);
			} else {
				result = newResult;
			}

			if (improve > 0) {

			}
		}
	}

	static void revert(char[] array, int start, int end) {
		for (; start < end && start < array.length; start++) {
			if (fixed[start] == '\0') {
				array[start] = (array[start] == '0' ? '1' : '0');
			}
			else {
				array[start] = fixed[start];
			}
		}
	}

	//	static void revert(char[] array, int i) {
	//		if (i < array.length) {
	//			array[i] = (array[i] == '0' ? '1' : '0');
	//			// saveFixed(i, array[i]);
	//		}
	//	}

	static void saveFixed(int i, char c) {
		fixed[i] = c;
		nfixed++;
	}

	static void loadFixed(char[] array, int i) {
		if (i < array.length && fixed[i] != '\0') {
			array[i] = fixed[i];
		}
	}

	static int print(char[] array) {
		System.out.println(new String(array));
		System.out.flush();
		int result = sc.nextInt();
		if (result < array.length) {
			saveFixed(result - 1, array[result - 1] == '0' ? '1' : '0');
		}
		return result;
	}
}