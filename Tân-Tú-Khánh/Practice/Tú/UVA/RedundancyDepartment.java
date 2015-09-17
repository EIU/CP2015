package uva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class RedundancyDepartment {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> b= new ArrayList();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(scan.hasNext()){
			b.add(scan.nextInt());
		}
		Object[] a = b.toArray();
		Arrays.sort(a);
		int len = a.length, count = 0;
		for (int i = len - 1; i >= 0; i--) {
			count++;
			if ((i == 0 || a[i] != a[i - 1])
					&& (i == len - 1 || a[i] != a[i + 1])) {
				map.put((Integer) a[i], count);
				count = 0;
			} else {
				if ((i == 0 || a[i] != a[i - 1]) && (a[i] == a[i + 1])) {
					map.put((Integer) a[i], count);
					count = 0;
				}
			}
		}
		for(int c : b){
			System.out.println();
		}
	}

}
