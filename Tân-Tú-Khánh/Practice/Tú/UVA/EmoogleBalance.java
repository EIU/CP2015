package uva;

import java.util.Scanner;

public class EmoogleBalance {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, num = 1;
		do{
			n = scan.nextInt();
			if(n == 0){
				break;
			}
			int sum1 = 0, sum2 = 0;
			for(int i = 1; i <= n; i++){
				int a = scan.nextInt();
				if(a > 0){
					sum1++;
				}
				else sum2++;
			}
			System.out.println("Case " + num + ": " + (sum1 - sum2));
			num++;
		}
		while(n != 0);
		scan.close();
	}

}
