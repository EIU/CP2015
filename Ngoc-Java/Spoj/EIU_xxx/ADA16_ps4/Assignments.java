import java.util.Scanner;

public class Assignments {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int D = sc.nextInt();

			int count = 0;
			for (int i = 0; i < N; i++) {
				if (sc.nextInt() * sc.nextInt() / sc.nextInt() >= D) {
					//if (sc.nextInt() / sc.nextInt() * sc.nextInt() >= D) { // Testing partial solution only
					count++;
				}
			}

			// For Testing only
			System.out.println(N < 500 ? count : 0);
		}
	}
}
