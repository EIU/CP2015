import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			double H = sc.nextDouble();
			double U = sc.nextDouble();
			double D = sc.nextDouble();
			double F = sc.nextDouble();
			double current = 0.0;
			double depRate = U * F / 100;

			if (H == 0) {
				break;
			}
			// All values above in [0,100] => simple solution

			int day = 1;
			while (true) {
				current += U;
				if (current > H) {
					System.out.println("success on day " + day);
					break;
				}

				current -= D;
				if (current <= 0) {
					System.out.println("failure on day " + (day + (current < 0 ? 0 : 1)));
					break;
				}

				day++;
				U = Math.max(0, U - depRate);
			}
		}
	}
}
