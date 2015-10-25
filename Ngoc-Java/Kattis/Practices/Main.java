import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();

		int sumMod = 1;
		int powMod = 1;
		int i;
		int MAX = 2000010;
		for (i = 1; i < MAX; i++) {
			int d;
			for (d = 1; d < 10; d++) {
				if ((sumMod * d) % L == 0) {
					System.out.println(d + " " + i);
					break;
				}
			}

			if (d < 10) {
				break;
			}

			powMod *= 10;
			powMod %= L;
			sumMod += powMod;
			sumMod %= L;
		}

		if (i == MAX) {
			System.out.println("I don't know!");
		}
	}
}
