import java.util.TreeMap;
import java.util.TreeSet;

public class TestTreeMap {
	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		TreeSet<Integer> map = new TreeSet<Integer>();

		int n = 3 * 100 * 1000;

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < n; i++) {
			Integer v = (int) (Math.random() * 1000 * 1000 * 1000);
			int size = 0; // map.tailSet(v).size();
			boolean existed = map.contains(v);
			map.add(v);
			result.append(s + ": " + size + " - " + existed + ", ");
		}

		System.out.println(result.length());
		System.out.println(System.currentTimeMillis() - s + "ms");
	}
}
