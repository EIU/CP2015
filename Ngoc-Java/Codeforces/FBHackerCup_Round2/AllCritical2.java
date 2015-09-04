import java.util.*;

public class AllCritical2 {

	public static void main(String[] args) throws Exception {
		solve();
	}

	static final int MAX = 2000;

	static void solve() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			double p = sc.nextDouble();
			double result = 1;
			double xp = 1 - p;
			double xpn = 1;
			for (int i = 1; i < MAX; i++) {
				xpn *= xp;
				double xp20 = pow20(1 - xpn);
				result += (1 - xp20);
			}
			System.out.println("Case #" + (t + 1) + ": " + result);
		}
	}

	static double pow20(double a) {
		a = a * a;
		double a4 = a * a;
		a = a4 * a4;
		a = a * a;
		return a * a4;
	}
}
