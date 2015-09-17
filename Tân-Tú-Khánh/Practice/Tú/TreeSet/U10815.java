package uva;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class U10815 {
	static InputStream is;
	static PrintWriter out;

	public static void main(String[] args) {
		is = System.in;
		out = new PrintWriter(System.out);
		Scanner scan = new Scanner(System.in);
		ArrayList<String> s = new ArrayList<String>();
		String rex = "[^a-z]";
		while (scan.hasNext()) {
			String k = scan.next().toLowerCase();
			if (k.equals("0")) {
				break;
			}
			String[] b = k.split(rex);
			for (int i = 0; i < b.length; i++) {
				if (!s.contains(b[i]) && !b[i].equals("")) {
					s.add(b[i].trim());
				}
			}
		}
		Collections.sort(s);
		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i));
		}
	}
}