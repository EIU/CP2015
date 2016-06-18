public class OnlineExamTest {

	static int n = 5000;
	static int k = 2000;
	static int x = 100;

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

		int n = OnlineExamTest.n;
		int k = OnlineExamTest.k;
		int x = OnlineExamTest.x;

		char[] fixed = new char[n];
		int nfixed = 0;

		char[] minAtt;
		char[] maxAtt;
		int minResult = n + 1;
		int maxResult = 0;

		public OnlineExam(Test test) {
			this.test = test;
		}

		void firstAttempt(int m) {
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

		void solve() {

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
				reIndex = Math.min(reIndex + 41, result - 1);
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
