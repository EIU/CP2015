import java.util.*;

public class RuntimeError {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int T = reader.nextInt();
		for (int t = 0; t < T; t++) {
			int N = reader.nextInt(), K = reader.nextInt();
			long[] numbers = new long[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = reader.nextInt();
			}
			Arrays.sort(numbers);
			int u = 0, v = N - 1;
			while (u < v) {
				if (numbers[u] * numbers[v] < K) {
					u++;
				} else if (numbers[u] * numbers[v] > K) {
					v--;
				} else {
					break;
				}
			}
			System.out.println(u < v && numbers[u] * numbers[v] == K ? (numbers[u] + " " + numbers[v]) : -1);
		}
	}
}