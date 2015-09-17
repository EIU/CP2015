package uva;

import java.util.ArrayList;
import java.util.Scanner;

public class JollyJump {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = scan.nextInt();
			int a[] = new int[n];
			boolean check = false;
			for (int i = 0; i < n; i++) {
				a[i] = scan.nextInt();
			}
			ArrayList res = new ArrayList();
			for (int i = 0; i < n - 1; i++) {
				int b = Math.abs(a[i+1] - a[i]);
				if (b == 0 || b >= a.length || res.contains(b)) {
					System.out.println("Not jolly");
					check = true;
					break;
				}
				else{
					res.add(b);
				}
			}
			if (check) {
				continue;
			}
			System.out.println("Jolly");
		}
	}
}
