import java.util.*;

public class OnlineExamTest4 {

	static int n = 5000;
	static int k = 2000;
	static int x = 100;

	static int globalCount = 0;
	static int globalX = 0;
	static int[] globalRanks = new int[200];

	public static void main(String[] args) throws Exception {
		long total = 0;
		for (int i = 0; i < 1000; i++) {
			Test test = new Test();
			OnlineExam robot = new OnlineExam(test, 70);
			robot.solve();
			total += Math.max(0, test.maxResult - 4000);
			globalCount += robot.count;
			globalX += robot.x;
		}
		System.out.println(total);
		System.out.println(globalCount / 1000);
		System.out.println(globalX);
		System.out.println(Arrays.toString(globalRanks));
	}

	static class OnlineExam {
		Test test;

		int n = OnlineExamTest2.n;
		int k = OnlineExamTest2.k;
		int x = OnlineExamTest2.x;

		int firstResult = 2 * k;
		int result = 2 * k;
		char[] attempt = new char[n];

		char[] fixed = new char[n];
		int nfixed = 0;

		Segment[] steps;
		int nStep = 66;
		int count;

		public OnlineExam(Test test, int STEP0) {
			this.test = test;
			this.nStep = STEP0;
			steps = new Segment[STEP0];
		}

		void fistAttempt() {
			for (int i = 0; i < n; i++) {
				if (fixed[i] == '\0') {
					attempt[i] = Math.random() < 0.5 ? '0' : '1';
				} else {
					attempt[i] = fixed[i];
				}
			}
			result = print();

			if (result < 2 * k) {
				invert(0, result - 1);
				result = print();
			}
			firstResult = result;
		}

		void processStep0() {
			int distance = result / nStep;

			int pre = 0;
			int remain = nStep * distance + nStep - result;
			for (int i = 0; i < nStep; i++) {
				steps[i] = new Segment(pre, pre += distance);
				if (i == remain) {
					distance++;
				}
			}

			for (Segment seg : steps) {
				invert(seg.start, seg.end);
				int newResult = print();
				seg.rank = Math.abs(newResult - 2 - result);
				if (newResult > result) {
					result = newResult;
				} else {
					invert(seg.start, seg.end);
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
				invert(seg.start, mid);
				int newResult = print();
				if (newResult - 2 <= result) {
					invert(seg.start, mid);
					if (-seg.rank < newResult - 2 - result) {
						globalRanks[0]++;
					} else {
						Segment updatingSeg = new Segment(mid, seg.end);
						updatingSegs.add(updatingSeg);
						globalRanks[result - newResult + 2]++;
					}
				} else {
					globalRanks[newResult - 2 - result]++;
					result = newResult;
				}
			}

			for (Segment seg : updatingSegs) {
				invert(seg.start, seg.end);
			}
			int newResult = print();
		}

		void solve() throws Exception {

			fistAttempt();
			processStep0();
			processStep1();
		}

		void invert(int start, int end) {
			int count = 0;
			int iCount = 0;
			for (; start < end && start < attempt.length; start++) {
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
