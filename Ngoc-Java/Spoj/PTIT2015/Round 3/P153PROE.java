import java.util.*;

class P153PROE {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			array[i] = sc.nextInt();
		}

		long seed = 0;
		String ans = "Yes";

		for (int i = n; i >= 1; i--) {
			if ((seed + array[i]) % i == 0) {
				seed += (seed + array[i]) / i;
			}
			else {
				ans = "No";
				break;
			}
		}
		System.out.println(ans);
		sc.close();
	}
	
}
