import java.util.*;
import java.io.*;

public class EIBOLTS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt(), C = sc.nextInt();

		int[] bolts = new int[n];
		for (int i = 0; i < n; i++) {
			bolts[i] = sc.nextInt();
		}
		Arrays.sort(bolts);

		int[] nuts = new int[m];
		for (int i = 0; i < m; i++) {
			nuts[i] = sc.nextInt();
		}
		Arrays.sort(nuts);

		int matchBolt = 0;
		int i = 0, j = 0;
		for (; i < n; i++) {
			for (; j < m; j++) {
				if (nuts[j] >= bolts[i]) {
					if (nuts[j] <= bolts[i] + C) {
						matchBolt++;
					}
					break;
				}
			}
		}
		System.out.println(matchBolt);
	}
}
