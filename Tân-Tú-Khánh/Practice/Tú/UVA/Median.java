package uva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Median {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> a = new ArrayList<Integer>();
		while (scan.hasNext()) {
			int x = scan.nextInt();
			a.add(x);
			Collections.sort(a);
			if (a.size() % 2 != 0 || a.size() == 1) {
				System.out.println(a.get(a.size() / 2));
			} else {
				System.out
						.println((a.get(a.size() / 2 - 1) + a.get(a.size() / 2)) / 2);
			}
		}
	}
}