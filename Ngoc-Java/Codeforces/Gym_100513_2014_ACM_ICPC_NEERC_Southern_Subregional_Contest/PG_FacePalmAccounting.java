import java.util.*;

public class PG_FacePalmAccounting {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt(), k = reader.nextInt();
		int[] values = new int[n + 1];
		int minValue = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			values[i] = reader.nextInt();
			minValue = Math.min(minValue, values[i]);
		}

		int sumk = 0, totalChange = 0;
		Stack<Integer> availableIndexes = new Stack<Integer>();
		for (int i = 1; i <= n; i++) {
			sumk += values[i];
			availableIndexes.push(i);
			if (i < k) {
				continue;
			}
			sumk -= values[i - k];
			while (sumk >= 0) {
				int lastIndex = availableIndexes.pop();
				int change = Math.min(values[lastIndex] - minValue, sumk + 1);
				values[lastIndex] -= change;
				sumk -= change;
				totalChange += change;
				if (values[lastIndex] > minValue) {
					availableIndexes.push(lastIndex);
				}
			}
		}
		StringBuilder outBf = new StringBuilder();
		outBf.append(totalChange + "\r\n");
		for (int i = 1; i <= n; i++) {
			outBf.append(values[i] + " ");
		}
		System.out.println(outBf);
	}
}
