import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class EIUTRANS {

	public static void main(String[] args) {
		// long s = System.currentTimeMillis();

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// Map<String, String> dic = new HashMap<String, String>();
		// Map<String, String> dic = new Hashtable<String, String>();
		Map<String, String> dic = new TreeMap<String, String>();
		for (int i = 0; i < m; i++) {
			String wordA = sc.next();
			String wordB = sc.next();
			if (wordA.length() > wordB.length()) {
				dic.put(wordA, wordB);
			}
		}

		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < n; i++) {
			String word = sc.next();
			if (dic.containsKey(word)) {
				word = dic.get(word);
			}
			bf.append(word + " ");
			// System.out.print(word + " ");
		}
		System.out.println(bf.toString());
		// System.out.println();
		// System.out.println(System.currentTimeMillis() - s + "ms");
	}

}
