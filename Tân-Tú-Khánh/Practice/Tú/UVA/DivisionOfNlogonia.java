package uva;

import java.util.Scanner;

public class DivisionOfNlogonia {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for(int i = 1; i <= test; i++){
			int n = scan.nextInt();
			int m = scan.nextInt();
			int x = scan.nextInt();
			int y = scan.nextInt();
			if(n == x && m == y){
				System.out.println("divisa");
			}
			else if(n < x && m < y){
				System.out.println("NE");
			}
			else if (n > x && m > y){
				System.out.println("N0");
			}
			else if(n > x && m < y){
				System.out.println("SE");
			}
			else{
				System.out.println("SO");
			}
		}
		scan.close();
	}

}
