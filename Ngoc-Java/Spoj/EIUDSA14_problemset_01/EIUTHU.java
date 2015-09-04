import java.util.Scanner;

class EIUTHU {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String part1 = sc.nextLine();
		String part2 = sc.nextLine();

		int len1 = part1.length();
		int len2 = part2.length();

		int minLen = len1 + len2;
		for (int i = len1 - 1; i >= 0; i--) {
			int j = 0;
			while (j < len1 - i && part1.charAt(i + j) == part2.charAt(j)) {
				j++;
			}
			if (j < len1 - i) {
				continue;
			}
			minLen = i + len2;
		}
		System.out.println(minLen);
	}

}
