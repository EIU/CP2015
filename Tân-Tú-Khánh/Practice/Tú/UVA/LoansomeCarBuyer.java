package uva;

import java.util.Scanner;

public class LoansomeCarBuyer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			int months = scan.nextInt();
			float paidofmonth = scan.nextFloat();
			float price = scan.nextFloat();
			int pricedown = scan.nextInt();
			if (months < 0)
				break;
			for (int i = 0; i < pricedown; i++) {
				int month = scan.nextInt();
				float a = scan.nextFloat();
				price *= (1 - a);
			}
			int monthdown = (int) (price / paidofmonth);
			months -= monthdown;
			if(months > 0){
				System.out.println(months + " months");
			}
			else System.out.println(monthdown + "months");
		}
		scan.close();
	}

}
