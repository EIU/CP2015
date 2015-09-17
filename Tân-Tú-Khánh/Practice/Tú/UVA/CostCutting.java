package uva;

import java.util.Scanner;

public class CostCutting {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 1; i <= test; i++) {
			int worker1 = scan.nextInt();
			int worker2 = scan.nextInt();
			int worker3 = scan.nextInt();
			if (worker1 < worker2 && worker1 > worker3 || worker1 > worker2
					&& worker1 < worker3)
				System.out.println("Case " + i + ": " + worker1);
			if (worker2 < worker1 && worker2 > worker3 || worker2 > worker1
					&& worker2 < worker3)
				System.out.println("Case " + i + ": " + worker2);
			if (worker3 < worker2 && worker3 > worker1 || worker3 > worker2
					&& worker3 < worker1)
				System.out.println("Case " + i + ": " + worker3);
		}
		scan.close();
	}
}
