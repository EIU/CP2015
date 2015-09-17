package uva;

import java.util.Scanner;

public class MarioJumping {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 1; i <= test; i++) {
			int wall = scan.nextInt();
			int[] n = new int[wall];
			int min = 0;
			int max = 0;
			n[0] = scan.nextInt();
			if (wall == 1) {
				System.out.println("Case " + i + ": " + 0 + " " + 0);
			} else {
				for (int j = 0; j < n.length - 1; j++) {
					n[j + 1] = scan.nextInt();
					if (n[j] - n[j + 1] < 0) {
						max++;
					} else if (n[j] - n[j + 1] > 0) {
						min++;
					}
				}
				System.out.println("Case " + i + ": " + max + " " + min);
			}
		}
		scan.close();
	}

}
