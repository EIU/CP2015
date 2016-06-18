import java.util.*;

public class OnlineExam2 {
	static int n = 5000;
	static int k = 2000;
	static int x = 100;
	static Scanner sc = new Scanner(System.in);
	static char[] fixed = new char[n];
	static int nfixed = 0;

	public static void main(String[] args) {
		solve();
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

		int m = 1;
		firstAttempt(m);
		x -= m;

		int result = 0;
		char[] attempt = null;
		if (Math.abs(2 * k - minResult) < Math.abs(maxResult - 2 * k) + 4) {
			attempt = maxAtt;
			loadFixed(attempt, maxResult - 1);
			result = maxResult;
		}
		else {
			attempt = minAtt;
			loadFixed(attempt, minResult - 1);
			result = minResult;
		}

		if (result < 2 * k) {
			revert(attempt, 0, result - 1);
			// result = 4*k-result;
			result = print(attempt);
			loadFixed(attempt, result - 1);
			x--;
		}

		int improve = 0;
		int reIndex = 0;
		for (; x > 0; x--) {
			int start = reIndex;
			for (int count = 0; (count < 41) && reIndex < result; reIndex++) {
				if (fixed[reIndex] == '\0') {
					count++;
				}
			}

			revert(attempt, start, reIndex);
			int newResult = print(attempt);
			loadFixed(attempt, newResult - 1);
			if (newResult <= result + 2) { //?
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