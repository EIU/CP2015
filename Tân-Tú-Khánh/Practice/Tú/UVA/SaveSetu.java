package uva;

import java.util.Scanner;

public class SaveSetu {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int sum = 0;
		for(int i = 1; i <= n; i++){
			String request = scan.next();
			if(request.equals("donate")){
				int k = scan.nextInt();
				sum += k;
			}
			else{
				System.out.println(sum);
			}
		}
		scan.close();
	}

}
