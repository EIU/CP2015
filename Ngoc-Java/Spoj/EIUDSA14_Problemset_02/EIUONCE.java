import java.util.Arrays;
import java.util.Scanner;

class EIUONCE {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();
			int[] array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = sc.nextInt();
			}
			solution_fast(array);
			// solution_count(array);
		}
	}

	static void solution_fast(int[] array) {
		Arrays.sort(array);
		int len = array.length;
		for (int i = 0; i < len; i++) {
			if ((i == 0 || array[i - 1] != array[i])
					&& (i == len - 1 || array[i] != array[i + 1])) {
				System.out.print(array[i] + " ");
			}
		}
		System.out.println();
	}

	static void solution_count(int[] array) {
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			if (countAppearance(array, array[i]) == 1) {
				System.out.print(array[i] + " ");
			}
		}
		System.out.println();
	}

	static int countAppearance(int[] array, int item) {
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == item) {
				result++;
			}
		}
		return result;
	}
}
