import java.util.*;
import java.io.*;

public class EISHORT2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), k = sc.nextInt();

		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextInt();
		}

		int minSize = n;
		for (int i = 0; i < n; i++) {
			long sum = 0;
			for (int j = i; j < n; j++) {
				sum += numbers[j];
				if (sum >= k) {
					minSize = Math.min(j - i + 1, minSize);
					break;
				}
			}
		}

		System.out.println(minSize);
	}
}
