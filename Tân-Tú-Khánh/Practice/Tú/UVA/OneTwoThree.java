package uva;

import java.util.Scanner;

public class OneTwoThree {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 1; i <= test; i++) {
			String n = scan.next();
			if (n.length() == 3) {
				if (n.charAt(0) == 'o' && n.charAt(1) == 'n'
						||n.charAt(0) == 'o' && n.charAt(2) == 'e'
						||n.charAt(1) == 'n' && n.charAt(2) == 'e')
					System.out.println(1);
				if (n.charAt(0) == 't' && n.charAt(1) == 'w'
						||n.charAt(0) == 't' && n.charAt(2) == 'o'
						||n.charAt(1) == 'w' && n.charAt(2) == 'o')
					System.out.println(2);
			} else
				System.out.println(3);
		}
		scan.close();
	}

}
