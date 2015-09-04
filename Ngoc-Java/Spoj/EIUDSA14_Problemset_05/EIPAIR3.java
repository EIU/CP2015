import java.util.Arrays;
import java.util.Scanner;

class EIPAIR3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int[] array = new int[N];
			for (int i = 0; i < N; i++) {
				array[i] = sc.nextInt();
			}
			Arrays.sort(array);
			int result = 0;
			int count = 1;
			for (int i = 0; i < N - 1; i++) {
				if (array[i] != array[i + 1]) {
					result += count * (count - 1) / 2;
					count = 1;
				}
				else {
					count++;
				}
			}
			result += count * (count - 1) / 2;
			System.out.println(result);
		}
	}

}
