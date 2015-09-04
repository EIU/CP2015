import java.util.*;

class EIUPREIU {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int n2 = n / 2;
		int width = (1 + n2) + 1 + (1) + 1 + (1 + n2 + 1);
		int height = n2 * 2 + 1;

		char[] row = new char[width];
		Arrays.fill(row, ' ');
		Arrays.fill(row, 1, n2 + 1, '_');
		System.out.println(new String(row));

		for (int i = 1; i < height; i++) {
			Arrays.fill(row, ' ');
			row[0] = row[n2 + 2] = row[n2 + 4] = row[n2 + n2 + 5] = '|';
			if (i == n2) {
				Arrays.fill(row, 1, n2 + 1, '_');
			}
			if (i == n2 * 2) {
				Arrays.fill(row, 1, n2 + 1, '_');
				Arrays.fill(row, n2 + 5, n2 + 5 + n2, '_');
			}
			System.out.println(new String(row));
		}
	}
}
