import java.util.Scanner;

class EIPAIR {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] array = new int[N];
			for (int i = 0; i < N; i++) {
				array[i] = sc.nextInt();
			}
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (array[i] == array[j]) {
						result++;
					}
				}
			}
			System.out.println(result);
		}
	}

}
