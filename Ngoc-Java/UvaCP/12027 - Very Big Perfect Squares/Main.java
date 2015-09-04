import java.util.Arrays;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.equals("0")) {
				break;
			}
			int len = line.length();
			int number = 0;
			number += line.charAt(0) - '0';
			if (len % 2 == 0) {
				number *= 10;
				number += line.charAt(1) - '0';
			}
			number = (int) Math.floor(Math.sqrt(number));
			char[] zeroChars = new char[(len - 1) / 2];
			Arrays.fill(zeroChars, '0');
			System.out.println(number + new String(zeroChars));
		}
	}
}
