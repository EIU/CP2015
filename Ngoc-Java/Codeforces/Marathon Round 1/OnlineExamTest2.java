public class OnlineExamTest2 {

	static int n = 5000;
	static int k = 2000;
	static int x = 100;

	static int[] mask = new int[x + 1];

	public static void main(String[] args) {
		long total = 0;
		for (int i = 0; i < 1000; i++) {
			Test test = new Test();
			OnlineExam robot = new OnlineExam(test);
			robot.solve();
			total += Math.max(0, test.maxResult - 4000);
			// System.out.println(test.count0);
		}
		System.out.println(total);
	}

	static class OnlineExam {
		Test test;

		int n = OnlineExamTest2.n;
		int k = OnlineExamTest2.k;
		int x = OnlineExamTest2.x;

		char[] fixed = new char[n];
		int nfixed = 0;

		public OnlineExam(Test test) {
			this.test = test;
		}

		void solve() {

			int result = 2 * k;
			char[] attempt = null;

			while (2 * k - 0 <= result && result <= 2 * k + 0) {
				attempt = new char[n];
				for (int i = 0; i < n; i++) {
					if (fixed[i] == '\0') {
						attempt[i] = Math.random() < 0.5 ? '0' : '1';
					} else {
						attempt[i] = fixed[i];
					}
				}
				result = print(attempt);
				loadFixed(attempt, result - 1);
				x--;
			}

			if (result < 2 * k) {
				revert(attempt, 0, result - 1);
				result = 4 * k - result + 2;

				result = print(attempt);
				loadFixed(attempt, result - 1);
				x--;
			}

			int STEP = 41;
			// STEP = (result) / x;
			int improve = 0;
			int reIndex = 0;
			for (; x > 0; x--) {
				int start = reIndex;
				for (int count = 0; count < STEP && reIndex < result; reIndex++) {
					if (fixed[reIndex] == '\0') {
						count++;
					}
				}

				if (x == 1) {
					int t = 0;
					t++;
				}

				revert(attempt, start, reIndex);
				int newResult = print(attempt);
				loadFixed(attempt, newResult - 1);
				if (newResult <= result + 2) {
					revert(attempt, start, reIndex);
				} else {
					result = newResult;
					mask[x]++;
				}

				if (improve > 0) {

				}
			}
		}

		void revert(char[] array, int start, int end) {
			for (; start < end && start < array.length; start++) {
				if (fixed[start] == '\0') {
					array[start] = (array[start] == '0' ? '1' : '0');
				}
				else {
					array[start] = fixed[start];
				}
			}
		}

		void saveFixed(int i, char c) {
			fixed[i] = c;
			nfixed++;
		}

		void loadFixed(char[] array, int i) {
			if (i < array.length && fixed[i] != '\0') {
				array[i] = fixed[i];
			}
		}

		int print(char[] array) {
			int result = test.submit(array);
			if (result < array.length) {
				saveFixed(result - 1, array[result - 1] == '0' ? '1' : '0');
			}
			return result;
		}
	}

	static class Test {

		char[] answers = new char[n];
		int maxResult = 0;
		int count0 = 0;

		public Test() {
			for (int i = 0; i < answers.length; i++) {
				answers[i] = (Math.random() < 0.5 ? '0' : '1');
				count0 += (answers[i] == '0' ? 1 : 0);
			}
		}

		public int submit(char[] submission) {
			int err = 0;
			int i = 0;
			for (; i < answers.length; i++) {
				err += (answers[i] != submission[i] ? 1 : 0);
				if (err == k) {
					break;
				}
			}
			maxResult = Math.max(maxResult, i + 1);
			return i + 1;
		}
	}
}
