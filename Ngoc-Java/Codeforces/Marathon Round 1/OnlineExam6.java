import java.util.*;

public class OnlineExam6 {

	static int n = 5000;
	static int k = 2000;
	static int x = 100;

	static int globalCount = 0;
	static int globalX = 0;
	static int[] globalRanks = new int[200];

	public static void main(String[] args) throws Exception {
				Test test = new Test();
				OnlineExam robot = new OnlineExam(test, 70);
				robot.solve();
	}

	static class OnlineExam {
		Test test;

		int n = OnlineExam6.n;
		int k = OnlineExam6.k;
		int x = OnlineExam6.x;

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
			for (int i = 0; i < n; i++) {
				// if (fixed[i] == '\0') {
				// attempt[i] = Math.random() < 0.5 ? '0' : '1';
				// } else {
				// attempt[i] = fixed[i];
				// }
				attempt[i] = '1';
			}
			result = print();

			int f = 2 * (result - 2 * k);
			invert(0, result / 2, 0);
			int newResult = print();

			if (f >= 0) {
				if (newResult > result) {
					// Lucky: OK
				} else if (result - newResult > f) {
					// OK
					invert(0, result / 2, 0);
					invert(result / 2, result, 0);
					result = print();
				} else {
					// Unlucky, do nothing
				}
			} else {
				if (newResult < result) {
					// OK
					invert(0, result / 2, 0);
					invert(result / 2, result, 0);
					result = print();
				} else if (result - newResult < f) {
					// Lucky
				} else {
					// Unlucky, do nothing
				}
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
				if (newResult - 2 <= result) {
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
		Scanner sc = new Scanner(System.in);
		int maxResult = 0;
		int i = 1;

		public Test() {
		}

		public int submit(char[] submission) {
			System.out.println(new String(submission));
			System.out.flush();
			int result = sc.nextInt();
			return result;
		}
	}
}
