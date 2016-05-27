import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Help2 {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 22);
		int T = Integer.parseInt(reader.readLine());

		for (int t = 0; t < T; t++) {
			String[] list1 = reader.readLine().trim().split(" ");
			String[] list2 = reader.readLine().trim().split(" ");
			System.out.println(solve(list1, list2));
		}
	}

	static String solve(String[] list1, String[] list2) {
		if (list1.length != list2.length) {
			return "-";
		}

		boolean hasChanged;
		do {
			hasChanged = false;
			for (int i = 0; i < list1.length; i++) {
				if ((isPlaceHolder(list1[i]) && !isPlaceHolder(list2[i]))
						|| (!isPlaceHolder(list1[i]) && isPlaceHolder(list2[i]))) {

					if (!isPlaceHolder(list1[i]) && isPlaceHolder(list2[i])) {
						String[] list = list1;
						list1 = list2;
						list2 = list;
					}

					String pattern = list1[i];
					for (int j = 0; j < list1.length; j++) {
						if (list1[j].compareTo(pattern) == 0 && !isPlaceHolder(list2[j])
								&& list2[i].compareTo(list2[j]) != 0) {
							return "-";
						}
						if (list1[j].compareTo(pattern) == 0) {
							list1[j] = list2[i];
						}
					}
					hasChanged = true;
				}

				if (!isPlaceHolder(list1[i]) && !isPlaceHolder(list2[i]) && list1[i].compareTo(list2[i]) != 0) {
					return "-";
				}
			}
		} while (hasChanged);

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < list1.length; i++) {
			if (i > 0) {
				result.append(" ");
			}
			result.append(isPlaceHolder(list1[i]) ? "a" : list1[i]);
		}

		return result.toString();
	}

	static boolean isPlaceHolder(String word) {
		return word.startsWith("<");
	}

	static class Node {
		public String text;
		public List<Node> links = new ArrayList<Node>();
	}
}
