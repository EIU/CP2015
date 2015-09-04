import java.util.Arrays;
import java.util.Scanner;

class EIUAPPEA {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		Arrays.sort(array);
		int count = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (i == 0 || array[i] != array[i - 1]) {
				System.out.println(array[i] + " " + count);
				count = 1;
			}
			else {
				count++;
			}
		}
		sc.close();
	}

}
