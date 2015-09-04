import java.util.Scanner;

class EIUPALIN {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		

		for (int t = 0; t < T; t++) {
			String s = sc.next();
			int len = s.length();
			int len2 = len / 2;
			int i = 0, j = len - 1;
			for (; i < len2; i++, j--) {
				if (s.charAt(i) != s.charAt(j)) {
					break;
				}
			}
			System.out.println(i == len2 ? "YES" : "NO");
		}
	}

}
