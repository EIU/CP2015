import java.io.*;
import java.util.Scanner;

class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			int duration = sc.nextInt();
			double down = sc.nextDouble();
			double amount = sc.nextDouble();
			int record = sc.nextInt();

			if (duration < 0) {
				continue;
			}

			int[] depMonths = new int[record];
			double[] depRates = new double[record];
			for (int i = 0; i < record; i++) {
				depMonths[i] = sc.nextInt();
				depRates[i] = sc.nextDouble();
			}

			double remain = amount + down;
			down = amount / duration;
			amount = amount + down;

			int depIndex = 0;
			for (int i = 0; i <= duration; i++) {
				if (depIndex + 1 < record && i >= depMonths[depIndex + 1]) {
					depIndex++;
				}

				remain *= (1 - depRates[depIndex]);
				amount -= down;

				if (amount < remain) {
					System.out.println(i + " month" + (i != 1 ? "s" : ""));
					break;
				}
			}
		}
	}
}
