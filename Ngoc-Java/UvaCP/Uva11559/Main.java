import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			int N = sc.nextInt();
			int B = sc.nextInt();
			int H = sc.nextInt();
			int W = sc.nextInt();

			int minValue = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				int p = sc.nextInt();
				for (int j = 0; j < W; j++) {
					int a = sc.nextInt();
					if (a >= N && N * p <= B && N * p < minValue) {
						minValue = N * p;
					}
				}
			}

			System.out.println(minValue < Integer.MAX_VALUE ? minValue : "stay home");
		}
	}
}
