import java.util.*;
import java.io.*;

public class EISHORT {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), k = sc.nextInt();

		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextInt();
		}

		long sum = 0;
		int minSize = n;
		int i = 0, j = 0;
		while (i < n && j < n) {
			sum += numbers[j];
			if (sum >= k) {
				while (i < n && sum - numbers[i] >= k) {
					sum -= numbers[i];
					i++;
				}
				minSize = Math.min(j - i + 1, minSize);
			}
			if (sum < 0) {
				i = j + 1;
			}
			j++;
		}

		System.out.println(minSize);
	}
}
