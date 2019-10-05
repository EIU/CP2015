import java.util.*;
import java.io.*;

class EIUMADIS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] numbers = new long[n];

		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextLong();
		}

		long max = Long.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				max = Math.max(max, numbers[j] - numbers[i]);
			}
		}
		System.out.println(max);
	}

}
