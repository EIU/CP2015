import java.util.Scanner;

class EIPAIR2 {

	static final int MAX = 1001;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] array = new int[MAX];
			for (int i = 0; i < N; i++) {
				array[sc.nextInt()]++;
			}
			int result = 0;
			for (int i = 0; i < MAX; i++) {
				result += array[i] * (array[i] - 1) / 2;
			}
			System.out.println(result);
		}
	}

}
