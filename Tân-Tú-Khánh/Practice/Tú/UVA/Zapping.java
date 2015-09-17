package uva;

import java.util.Scanner;

public class Zapping {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = 0,b = 0;
		while(a >= 0 || b >= 0){
		a = scan.nextInt();
		b = scan.nextInt();
		if(a < 0 || b < 0){
			break;
		}
		if (Math.abs(a - b) > 50) {
			if (a < 50) {
				System.out.println(100 + a - b);
			} else
				System.out.println(100 + b - a);
		} else
			System.out.println(Math.abs(a - b));
		} 
		scan.close();
	}

}
