import java.util.*;
import java.io.*;

public class EIUGOTRA {

	public static void main(String[] args) {
		System.out.println(2000000000 + 2000000000);
		
		/*
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int[] values = new int[n];

		for (int i = 0; i < n; i++) {
			values[i] = sc.nextInt();
		}
		
		Arrays.sort(values);

		int max = 0;
		int head = 0, tail = 0;
		for (; tail < n;) {
			if (Math.abs(values[tail] - values[head]) <= d) {
				max = Math.max(max, tail - head + 1);
				tail++;
			} else {
				head++;
			}
		}
		System.out.println(max);
		// */
	}

}
