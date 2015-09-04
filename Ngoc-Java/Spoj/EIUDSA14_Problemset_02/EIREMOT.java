import java.util.Scanner;

class EIREMOT {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();

		// solution_simplify(a, b);
		solution_combine(a, b);
	}

	static void solution_simplify(int a, int b) {
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}

		System.out.println(Math.min(b - a, a + 100 - b));
	}

	static void solution_combine(int a, int b) {
		System.out.println(Math.min(Math.abs(b - a), Math.min(a + 100 - b, b + 100 - a)));
	}
}
