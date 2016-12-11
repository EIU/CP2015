import java.util.*;

public class Simple2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = 100000 * 5;
		long sum = sum1(K);
		sum += sum2(K * 2);
		sum += sum3(K * 4);
		sum += sum4(K * 10);
		sum += sum5(K * 20);
		System.out.println(sum);
	}

	static int sum1(int n) {
		if (n == 1) {
			return 1;
		}
		return sum1(n - 1) + 1;
	}

	static int sum2(int n) {
		if (n == 1) {
			return 1;
		}
		return sum1(n - 1) + 1;
	}

	static int sum3(int n) {
		if (n == 1) {
			return 1;
		}
		return sum1(n - 1) + 1;
	}

	static int sum4(int n) {
		if (n == 1) {
			return 1;
		}
		return sum1(n - 1) + 1;
	}

	static int sum5(int n) {
		if (n == 1) {
			return 1;
		}
		return sum1(n - 1) + 1;
	}
}

/* 
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int n = sc.nextInt();
		} 
 
*/