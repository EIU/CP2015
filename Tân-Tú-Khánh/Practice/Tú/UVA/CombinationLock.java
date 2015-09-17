package uva;

import java.util.Scanner;

public class CombinationLock {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextInt()) {
			int code0 = scan.nextInt();
			int code1 = scan.nextInt();
			int code2 = scan.nextInt();
			int code3 = scan.nextInt();
			int deg = 1080;
			if (code0 == code1 && code1 == code2 && code2 == code3 && code3 == 0) break;
				if(code0 - code1 < 0) deg += (code0 - code1 + 40)*9;
				else deg += (code0 - code1)*9;
				if(code2 - code1 < 0) deg += (code2 - code1 + 40)*9;
				else deg += (code2 - code1)*9;
				if(code2 - code3 < 0) deg += (code2 - code3 + 40)*9;
				else deg += (code2 - code3)*9;
				System.out.println(deg);
			}

		scan.close();
	}

}
