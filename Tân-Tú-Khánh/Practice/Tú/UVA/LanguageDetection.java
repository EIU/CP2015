package uva;

import java.util.Scanner;

public class LanguageDetection {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		int num = 1;
		while (!s.equals("#")) {
			if (s.toUpperCase().equals("HELLO"))
				System.out.println("Case " + num + ": " + "ENGLISH");
			else if (s.toUpperCase().equals("HOLA"))
				System.out.println("Case " + num + ": " + "SPANISH");
			else if (s.toUpperCase().equals("HALLO"))
				System.out.println("Case " + num + ": " + "GERMAN");
			else if (s.toUpperCase().equals("BONJOUR"))
				System.out.println("Case " + num + ": " + "FRENCH");
			else if (s.toUpperCase().equals("CIAO"))
				System.out.println("Case " + num + ": " + "ITALIAN");
			else if (s.toUpperCase().equals("ZDRAVSTVUJTE"))
				System.out.println("Case " + num + ": " + "RUSSIAN");
			else System.out.println("Case " + num + ": " + "UNKNOWN");
			s = scan.next();
			num++;
		}
		scan.close();
	}

}
