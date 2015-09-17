package uva;

import java.util.Scanner;

public class PackingForHoliday {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 1; i <= test; i++) {
			int l = scan.nextInt();
			int w = scan.nextInt();
			int h = scan.nextInt();
			if (l <= 20 && w <= 20 && h <= 20)
				System.out.println("Case " + i + ": " + "good");
			else
				System.out.println("Case " + i + ": " + "bad");
		}
		scan.close();
	}

}
