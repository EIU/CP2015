package uva;

import java.util.Scanner;

public class OptimalParking {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for(int i = 1; i <= test; i++){
			int shop = scan.nextInt();
			int[] p = new int[shop];
			p[0] = scan.nextInt();
			int min = p[0],max = p[0];
			for(int j = 1; j < shop; j++){
				p[j] =scan.nextInt();
				if(min > p[j]) min = p[j];
				if(max < p[j]) max = p[j];
			}
			System.out.println((max - min)*2);
 		}
		scan.close();
	}

}
