import java.util.Arrays;

public class OnlineExamTest3 {

	static int n = 5000;
	static int k = 2000;
	static int x = 100;

	static int[] mask = new int[x + 1];
	static long globalImprove = 0;
	static long globalCorrect = 0;
	static long globalWrong = 0;
	static long globalFixed = 0;

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
		System.out.println(globalImprove / 1000);
		System.out.println(globalCorrect + " " + globalWrong + " " + globalFixed + " " + (globalWrong + globalFixed));
	}

	static class OnlineExam {
		Test test;

		int n = OnlineExamTest3.n;
		int k = OnlineExamTest3.k;
		int x = OnlineExamTest3.x;

		char[] fixed = new char[n];
		int nfixed = 0;

		public OnlineExam(Test test) {
			this.test = test;
			Arrays.fill(fixed, '\0');
		}

		void solve() {

			int result = 2 * k;
			char[] attempt = new char[n];
			for (int i = 0; i < n; i++) {
				if (fixed[i] == '\0') {
					attempt[i] = Math.random() < 0.5 ? '0' : '1';
				} else {
					attempt[i] = fixed[i];
				}
			}
			result = print(attempt);
			x--;

			int STEP = 45;
			int improve = 0;
			int reIndex = 0;
			for (; x > 1; x--) {

				//				if (x == 120) {
				//					int count = 0;
				//					for (int i = 0; i < n; i++) {
				//						if (fixed[i] != '\0') {
				//							attempt[i] = fixed[i];
				//							count++;
				//						}
				//					}
				//					result = print(attempt);
				//					x--;
				//				}

				int start = reIndex;
				//				for (int count = 0; (count < STEP) && reIndex < result; reIndex++) {
				//					if (fixed[reIndex] == '\0') {
				//						count++;
				//					}
				//				}
				reIndex += STEP;
				revert(attempt, start, reIndex);
				int newResult = print(attempt);
				if (newResult <= result) {
					revert(attempt, start, reIndex);
					globalWrong++;
				} else {
					improve += (newResult - result);
					result = newResult;
					mask[x]++;
					globalCorrect++;
				}
			}

			int count = 0;
			for (int i = 0; i < n; i++) {
				if (fixed[i] != '\0') {
					attempt[i] = fixed[i];
					count++;
				}
			}

			result = print(attempt);
			x--;
			globalImprove += improve;
		}

		void revert(char[] array, int start, int end) {
			for (; start < end && start < array.length; start++) {
				//if (fixed[start] == '\0') {
				array[start] = (array[start] == '0' ? '1' : '0');
				//}
			}
		}

		void saveFixed(int i, char c) {
			fixed[i] = c;
			nfixed++;
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

		public char[] answers = new char[n];
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
