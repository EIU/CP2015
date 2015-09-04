import java.util.Scanner;

public class ProblemC {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String line;
		while (!(line = sc.nextLine()).isEmpty()) {
			String[] tokens = line.split(" ");
			int n = Integer.parseInt(tokens[0]);
			int m = Integer.parseInt(tokens[1]);

			int ni = 1;
			int oi = 1;
			while (ni < n) {
				int x = m / (ni + 1);
				if (oi <= ni + 1 - m) {
					oi += m;
				} else {
					oi += m - ni - 1;
				}
				ni++;
			}
			System.out.println(oi);
		}
	}
}
