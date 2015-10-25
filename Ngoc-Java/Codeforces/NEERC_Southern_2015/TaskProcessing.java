import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TaskProcessing {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		is = System.in;

		int n = ni();
		Task[] tasks = new Task[n];
		for (int i = 0; i < n; i++) {
			tasks[i] = new Task(i, ni(), ni());
		}

		Arrays.sort(tasks);

		int limitComplete = 100002;
		ArrayList<Task>[] queue = new ArrayList[limitComplete];
		for (int i = 0; i < limitComplete; i++) {
			queue[i] = new ArrayList<Task>();
		}
		int[] firstQueueIndex = new int[limitComplete];

		long[] completed = new long[n];

		int nextTask = 0;
		long completing = 0;
		int firstQueue = 0;
		int j = 0;
		while (j < n) {
			while (nextTask < n && tasks[nextTask].t <= completing) {
				queue[tasks[nextTask].t].add(tasks[nextTask]);
				nextTask++;
			}

			while (firstQueue <= completing && firstQueueIndex[firstQueue] >= queue[firstQueue].size()) {
				firstQueue++;
			}

			if (firstQueue > completing) {
				completing++;
				continue;
			}

			int minK = firstQueue;
			long minValue = Long.MAX_VALUE;
			Task task0 = queue[firstQueue].get(firstQueueIndex[firstQueue]);
			long dt0 = (long) (completing - task0.t) * (completing - task0.t);
			for (int k = firstQueue; k < firstQueue + 340 && k < limitComplete; k++) {
				if (firstQueueIndex[k] < queue[k].size()) {
					Task task = queue[k].get(firstQueueIndex[k]);
					long dt2 = (long) (completing - task.t) * (completing - task.t);
					if (dt0 - dt2 > 100001){
						break;
					}
					long value = task.l - dt2;
					if (value < minValue) {
						minValue = value;
						minK = k;
					} else if (value == minValue && task.id < queue[minK].get(firstQueueIndex[minK]).id) {
						minK = k;
					}
				}
			}

			ArrayList<Task> minQueue = queue[minK];
			Task task = minQueue.get(firstQueueIndex[minK]);
			completing += task.l;
			completed[task.id] = completing;
			firstQueueIndex[minK]++;
			j++;
		}

		StringBuilder bf = new StringBuilder();
		for (int i = 0; i < n; i++) {
			bf.append(completed[i] + " ");
		}
		System.out.println(bf.toString());
	}
	
	static class Task implements Comparable<Task> {
		public int id;
		public int l;
		public int t;

		public Task(int id, int l, int t) {
			this.id = id;
			this.l = l;
			this.t = t;
		}

		@Override
		public int compareTo(Task arg0) {
			if (this.t != arg0.t) {
				return this.t - arg0.t;
			}
			if (this.l != arg0.l) {
				return this.l - arg0.l;
			}
			return this.id - arg0.id;
		}
	}

	/*****************************************************************
	 ******************** BASIC READER *******************************
	 *****************************************************************/

	static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b));
		return b;
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
}