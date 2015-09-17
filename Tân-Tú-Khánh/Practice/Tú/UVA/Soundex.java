package uva;

import java.util.HashMap;
import java.util.Scanner;

public class Soundex {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("B", "1");
		map.put("F", "1");
		map.put("P", "1");
		map.put("V", "1");
		map.put("C", "2");
		map.put("G", "2");
		map.put("J", "2");
		map.put("K", "2");
		map.put("Q", "2");
		map.put("S", "2");
		map.put("X", "2");
		map.put("Z", "2");
		map.put("D", "3");
		map.put("T", "3");
		map.put("L", "4");
		map.put("M", "5");
		map.put("N", "5");
		map.put("R", "6");
		while (scan.hasNext()) {
			String s = scan.next();
			String res = "";
			if (map.containsKey("" + s.charAt(0))) {
				res += map.get("" + s.charAt(0));
			}
			for (int i = 1; i < s.length(); i++) {
				if (map.containsKey("" + s.charAt(i))) {
					String a = map.get("" + s.charAt(i));
					String b = map.get("" + s.charAt(i - 1));
					if (!a.equals(b)) {
						res += map.get("" + s.charAt(i));
					}
				}
			}
			System.out.println(res);
		}
	}
}
