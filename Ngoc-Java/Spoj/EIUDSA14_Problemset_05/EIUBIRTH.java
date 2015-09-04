import java.util.Scanner;

class EIUBIRTH {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int B = sc.nextInt();
			int R = sc.nextInt();
			int X = sc.nextInt();
			int Y = sc.nextInt();
			int Z = sc.nextInt();

			if (X < Y) {
				Y = Math.min(Y, X + Z);
			}
			else {
				X = Math.min(X, Y + Z);
			}

			System.out.println((long) B * X + (long) R * Y);
		}
	}

}
