import java.util.*;

public class OnlineExamTest5 {

	static int n = 5000;
	static int k = 2000;
	static int x = 100;

	static int globalCount = 0;
	static int globalX = 0;
	static int[] globalRanks = new int[600];

	public static void main(String[] args) throws Exception {

		for (int S = 70; S <= 70; S++) {
			System.out.println("Trying S = " + S);
			globalCount = 0;
			globalX = 0;
			globalRanks = new int[600];
			long total = 0;
			int[] firstAttempScores = new int[600];
			int[] firstAttempCounts = new int[600];

			for (int i = 0; i < 1000; i++) {
				Test test = new Test();
				OnlineExam robot = new OnlineExam(test, S);
				robot.solve();
				total += Math.max(0, test.maxResult - 4000);
				globalCount += robot.count;
				globalX += robot.x;

				// firstAttempCounts[(robot.firstResult - 4000) / 5]++;
				// firstAttempScores[(robot.firstResult - 4000) / 5] +=
				// Math.max(0, test.maxResult - 4000);

			}
			System.out.println(total);
			System.out.println(globalCount);
			System.out.println(globalX);
			// System.out.println(Arrays.toString(globalRanks));
			// for (int i = 0; i < firstAttempCounts.length; i++) {
			// System.out
			// .print((firstAttempCounts[i] != 0 ? firstAttempScores[i]
			// / firstAttempCounts[i]
			// : 0)
			// + " ");
			// }
		}
	}

	static class OnlineExam {
		Test test;

		int n = OnlineExamTest6.n;
		int k = OnlineExamTest6.k;
		int x = OnlineExamTest6.x;

		int firstResult = 2 * k;
		int result = 2 * k;
		char[] attempt = new char[n];

		char[] fixed = new char[n];
		int nfixed = 0;

		Segment[] steps;
		int nStep = 66;
		int count;

		public OnlineExam(Test test, int nStep) {
			this.test = test;
			this.nStep = nStep;
			steps = new Segment[nStep];
		}

		void fistAttempt() {
			for (int u = 0; u < 1; u++) {
				for (int i = 0; i < n; i++) {
					if (fixed[i] == '\0') {
						attempt[i] = Math.random() < 0.5 ? '0' : '1';
					} else {
						attempt[i] = fixed[i];
					}
					// attempt[i] = '1';
				}
				result = print();
				if (Math.abs(result - 2 * k) > nStep)
					break;
			}

			// if (result < 2 * k) {
			// invert(0, result - 1, 0);
			// result = print();
			// }

			// int f = (result - 2 * k);
			// invert(0, result / 2, 0);
			// int newResult = print();
			//
			// if (f >= 0) {
			// if (newResult > result) {
			// // Lucky: OK
			// } else if (result - newResult > f) {
			// // OK
			// invert(0, result / 2, 0);
			// invert(result / 2, result, 0);
			// result = print();
			// } else {
			// // Unlucky, do nothing
			// }
			// } else {
			// if (newResult < result) {
			// // OK
			// invert(0, result / 2, 0);
			// invert(result / 2, result, 0);
			// result = print();
			// } else if (result - newResult < f) {
			// // Lucky
			// } else {
			// // Unlucky, do nothing
			// }
			// }

			firstResult = result;
		}

		void processStep0() {
			int limit = firstResult;
			int distance = limit / nStep;

			int pre = 0;
			int remain = nStep * distance + nStep - limit;
			for (int i = 0; i < nStep; i++) {
				steps[i] = new Segment(pre, pre += distance);
				if (i == remain) {
					distance++;
				}
			}

			for (Segment seg : steps) {
				invert(seg.start, seg.end, 0);
				int newResult = print();
				seg.rank = Math.abs(newResult - 2 - result);
				if (newResult > result) {
					result = newResult;
				} else {
					invert(seg.start, seg.end, 0);
				}
				// globalRanks[seg.rank]++;
			}
		}

		void processStep1() {

			PriorityQueue<Segment> queue = new PriorityQueue<>();
			for (int i = 0; i < nStep; i++) {
				queue.add(steps[i]);
			}
			ArrayList<Segment> updatingSegs = new ArrayList<Segment>();

			while (x > 1) {
				Segment seg = queue.poll();
				int mid = (seg.start + seg.end) / 2;
				if (seg.type == 0) {
					invert(seg.start, mid, 0);
				} else {
					invert(seg.start, seg.end, 1);
				}
				int newResult = print();
				if (newResult - 4 <= result) {
					if (seg.type == 0) {
						invert(seg.start, mid, 0);
					} else {
						invert(seg.start, seg.end, 1);
					}
					if (-seg.rank <= newResult - 2 - result) {
						if (seg.type == 0) {
							seg.type++;
							queue.add(seg);
						} else {
							count++;
						}
						globalRanks[0]++;
					} else {
						if (seg.type == 0) {
							Segment updatingSeg = new Segment(mid, seg.end);
							updatingSegs.add(updatingSeg);
						} else {
							Segment updatingSeg = new Segment(seg.start,
									seg.end);
							updatingSeg.type = 2;
							updatingSegs.add(updatingSeg);
						}
						globalRanks[result - newResult + 2]++;
					}
				} else {
					globalRanks[newResult - 2 - result]++;
					result = newResult;
				}
			}

			for (Segment seg : updatingSegs) {
				invert(seg.start, seg.end, seg.type);
			}
			int newResult = print();
		}

		void solve() throws Exception {

			fistAttempt();
			processStep0();
			processStep1();
		}

		void invert(int start, int end, int type) {
			int count = 0;
			int iCount = 0;
			int d = (type == 0 ? 1 : 2);
			if (type == 2) {
				start++;
			}
			for (; start < end && start < attempt.length; start += d) {
				if (fixed[start] == '\0') {
					attempt[start] = (attempt[start] == '0' ? '1' : '0');
					count++;
				} else {
					iCount++;
				}
			}

			int t = 0;
			t++;
		}

		int print() {
			int result = test.submit(attempt);
			if (result < attempt.length) {
				fixed[result - 1] = attempt[result - 1] = (attempt[result - 1] == '0') ? '1'
						: '0';
				nfixed++;
			}
			x--;
			return result;
		}

		static class Segment implements Comparable<Segment> {
			public int start;
			public int end;
			public int rank;
			public int type = 0;

			public boolean shouldRevert;

			public Segment(int start, int end) {
				this.start = start;
				this.end = end;
			}

			@Override
			public int compareTo(Segment arg0) {
				return rank - arg0.rank;
			}
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
