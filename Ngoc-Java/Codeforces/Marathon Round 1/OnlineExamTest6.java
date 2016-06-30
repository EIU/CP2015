import java.util.*;

public class OnlineExamTest6 {

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

			for (int i = 0; i < 10000; i++) {
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
			System.out.println(Arrays.toString(globalRanks));
			for (int i = 0; i < firstAttempCounts.length; i++) {
				System.out.print(firstAttempCounts[i] + "\t");
			}
			System.out.println();
			for (int i = 0; i < firstAttempCounts.length; i++) {
				System.out.print((firstAttempCounts[i] != 0 ? firstAttempScores[i] / firstAttempCounts[i] : 0) + "\t");
			}
			System.out.println();
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
		int nStep = 70;
		int count;

		public OnlineExam(Test test, int nStep) {
			this.test = test;
			this.nStep = nStep;
			steps = new Segment[nStep];
		}

		void solve() throws Exception {

			fistAttempt();
			processStep0();
			processStep1();
		}

		void fistAttempt() {
			for (int u = 0; u < 1; u++) {
				fixed[result - 1] = '\0';
				for (int i = 0; i < n; i++) {
					// if (fixed[i] == '\0') {
					attempt[i] = Math.random() < 0.5 ? '0' : '1';
					// } else {
					// attempt[i] = fixed[i];
					// }
					// attempt[i] = '1';
				}
				result = print();
				if (Math.abs(result - 2 * k) > 50) {
					break;
				}
			}

			if (result < 2 * k) {
				invert(0, result - 1, 0);
				result = print();
			}
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
					// seg.rank = Math.max(0, newResult - 2 - result);
					result = newResult;
				} else {
					// seg.rank = result - newResult;
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
				globalRanks[seg.rank]++;
				int mid = (seg.start + seg.end) / 2;
				if (seg.type == 0) {
					invert(seg.start, mid, 0);
				} else if (seg.type == 1) {
					invert(seg.start, seg.end, 1);
				}
				int newResult = print();
				if (newResult - 2 - result <= 0) {
					// if (newResult - 2 - result + seg.rank < 6) {
					if (seg.type == 0) {
						invert(seg.start, mid, 0);
					} else if (seg.type == 1) {
						invert(seg.start, seg.end, 1);
					}
					if (-seg.rank <= newResult - 2 - result) {
						if (seg.type == 0) {
							seg.type = 1;
							seg.result1 = result;
							queue.add(seg);
						} else if (seg.type == 1) {
							// seg.type = 3;
							// queue.add(seg);
							if (x > 1) {
								count++;
								invert(seg.start, seg.start + (seg.end - seg.start) / 4);
								int res2 = print();
								if (res2 - 2 - result > 0) {
									// OK
									Segment updatingSeg = new Segment(seg.start + 3 * (seg.end - seg.start) / 4, seg.end);
									updatingSegs.add(updatingSeg);
									result = res2;
								} else if (-seg.rank > res2 - 2 - result) {
									invert(seg.start, seg.start + (seg.end - seg.start) / 4);
									Segment updatingSeg = new Segment(seg.start + (seg.end - seg.start) / 4, seg.start + 3 * (seg.end - seg.start) / 4);
									updatingSegs.add(updatingSeg);
								} else {
									invert(seg.start, seg.start + (seg.end - seg.start) / 4);
								}
							}
						} else {
						}
						// globalRanks[0]++;
					} else {
						if (seg.type == 0) {
							Segment updatingSeg = new Segment(mid, seg.end);
							updatingSegs.add(updatingSeg);
						} else if (seg.type == 1) {
							Segment updatingSeg = new Segment(seg.start, seg.end);
							updatingSeg.type = 2;
							updatingSegs.add(updatingSeg);
						}
						// globalRanks[result - newResult + 2]++;
					}
				} else {
					if (seg.type == 0) {
						seg.rank += newResult - 2 - result;
						seg.type = 1;
						queue.add(seg);
					}
					result = newResult;
					// globalRanks[newResult - 2 - result]++;
				}
			}

			for (Segment seg : updatingSegs) {
				invert(seg.start, seg.end, seg.type);
			}
			int newResult = print();
		}

		void invert(int start, int end, int type) {
			if (type == 0) {
				invert(start, end);
			} else if (type == 1) {
				invert(start, start + (end - start) / 4);
				invert((start + end) / 2, start + 3 * (end - start) / 4);
			} else if (type == 2) {
				invert(start + (end - start) / 4, (start + end) / 2);
				invert(start + 3 * (end - start) / 4, end);
			}
		}

		// void invert1(int start, int end, int type) {
		// if (type == 0) {
		// for (; start < end && start < attempt.length; start++) {
		// if (fixed[start] == '\0') {
		// attempt[start] = (attempt[start] == '0' ? '1' : '0');
		// }
		// }
		// } else {
		// if (type == 2) {
		// start++;
		// }
		// for (; start < end && start < attempt.length; start += 2) {
		// if (fixed[start] == '\0') {
		// attempt[start] = (attempt[start] == '0' ? '1' : '0');
		// }
		// }
		// }
		// }

		void invert(int start, int end) {
			for (; start < end && start < attempt.length; start++) {
				if (fixed[start] == '\0') {
					attempt[start] = (attempt[start] == '0' ? '1' : '0');
				}
			}
		}

		int print() {
			int result = test.submit(attempt);
			if (result - 1 < attempt.length) {
				fixed[result - 1] = attempt[result - 1] = (attempt[result - 1] == '0') ? '1' : '0';
				nfixed++;
			}
			x--;
			return result;
		}

		static class Segment implements Comparable<Segment> {
			public int start;
			public int end;
			public int rank;
			public int result1;
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
