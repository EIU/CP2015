package uva;

import java.util.Scanner;

public class SecretResearch {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 1; i <= test; i++) {
			String n = scan.next();
			if (n.equals("1") || n.equals("4") || n.equals("78"))
				System.out.println("+");
			else if (n.endsWith("35"))
				System.out.println("-");
			else if (n.startsWith("190"))
				System.out.println("?");
			else
				System.out.println("*");
		}
		scan.close();
	}

}
