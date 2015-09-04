import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int[] items = new int[1000];

		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();

			long sum = 0;
			for (int i = 0; i < N; i++) {
				int item = sc.nextInt();
				sum += item;
				items[i] = item;
			}

			double average = (double) sum / N;

			double min = Integer.MAX_VALUE;
			int cake = 0;
			for (int i = 0; i < M; i++) {
				int item = items[i];
				double distance = Math.abs(item - average);
				if (min > distance) {
					min = Math.abs(item - average);
					cake = item;
				}
				else if (min == distance && item < average) {
					cake = item;
				}
			}

			min = Integer.MAX_VALUE;
			int drink = 0;
			for (int i = M; i < M + K; i++) {
				int item = items[i];
				double distance = Math.abs(item - average);
				if (min > distance) {
					min = Math.abs(item - average);
					drink = item;
				}
				else if (min == distance && item < average) {
					drink = item;
				}
			}
			// Another silly mistake of t from 0
			System.out.println("Case #" + (t + 1) + ": " + cake + " " + drink);
		}
	}
}
