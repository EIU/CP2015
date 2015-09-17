package uva;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class HardwoodSpecies {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			HashMap<String, Double> s = new HashMap<String, Double>();
			int count = 0;
			while (true) {
				String temp = in.nextLine();
				if (temp.equals("")) {
					break;
				} else {
					if(!s.containsKey(temp)){
						s.put(temp, (double) 1);
					}
					else{
						s.put(temp, s.get(temp) + 1);
					}
				}
				count++;
			}
			SortedSet<String> keys = new TreeSet<String>(s.keySet());
			DecimalFormat round = new DecimalFormat("0.0000");
			for(String each : keys){
				System.out.println(each + " " + round.format((s.get(each)/count)*100));
			}
		}
	}
}