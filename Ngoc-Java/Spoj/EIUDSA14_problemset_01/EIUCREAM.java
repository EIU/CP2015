import java.util.Scanner;
import java.util.Stack;

class EIUCREAM {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int result = solution_human_thinking(n, m);
		System.out.println(result);
		sc.close();
	}

	static int solution_analysis(int n, int m) {
		int ate = n;
		while (n >= m) {
			int bonus = n / m;
			ate += bonus;
			n = n - bonus * m + bonus;
		}
		return ate;
	}

	static int solution_human_thinking(int n, int m) {
		for (int i = 1; i <= n; i++) {
			if (i % m == 0) {
				n++;
			}
		}
		return n;
	}

	static int solution_recursion(int n, int m) {
		if (n < m) {
			return n;
		}
		return m * (n / m) + solution_recursion(n / m + n % m, m);
	}

	static int solution_remove_tail_revursive(int n, int m) {
		int result = 0;
		while (n >= m) {
			result += m * (n / m);
			n = n / m + n % m;
		}
		result += n;
		return result;
	}

	static int solution_mathematic(int n, int m) {
		return n + (n - 1) / (m - 1);
	}
}
