import java.util.Arrays;
import java.util.Scanner;

class EIUGIFTS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long K = sc.nextLong();

		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		solution_fast(a, N, K);
		// solution_simple(a, N, K);
		sc.close();
	}

	static void solution_fast(int[] a, int N, long K) {
		Arrays.sort(a);
		int i = 0;
		int j = N - 1;
		long maxValue = -1;
		int minDiff = -1;
		while (i < j) {
			long value = (long) a[i] + a[j];
			if (value > K) {
				j--;
			}
			else {
				if (value >= maxValue) {
					maxValue = value;
					minDiff = a[j] - a[i];
				}
				i++;
			}
		}
		System.out.println(maxValue + " " + minDiff);
	}

	static void solution_simple(int[] a, int N, long K) {
		long maxValue = -1;
		int minDiff = -1;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int sum = a[i] + a[j];
				if (sum <= K) {
					if (sum == maxValue) {
						if (minDiff > Math.abs(a[i] - a[j])) {
							minDiff = Math.abs(a[i] - a[j]);
						}
					}
					else if (sum > maxValue) {
						maxValue = sum;
						minDiff = Math.abs(a[i] - a[j]);
					}
				}
			}
		}
		System.out.println(maxValue + " " + minDiff);
	}
}
