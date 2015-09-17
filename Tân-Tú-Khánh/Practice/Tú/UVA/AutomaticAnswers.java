package uva;

import java.util.Scanner;

public class AutomaticAnswers {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for(int i = 1; i <= test; i++){
			int n = scan.nextInt();
			n = ((((n*567)/9)+7492)*235)/47-498;
			n = n/10;
			System.out.println(Math.abs(n%10));
		}
		scan.close();
	}

}
