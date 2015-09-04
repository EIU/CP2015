import java.util.*;

public class EIUFREQU {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Dictionary<String, Integer> dic = new Hashtable<String, Integer>();
		// Map<String, Integer> dic = new HashMap<String, Integer>(); // Still be OK
		List<String> keys = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			String name = sc.next();
			Integer value = dic.get(name);
			if (value == null) {
				value = 0;
				keys.add(name);
			}
			value++;
			dic.put(name, value);
		}

		for (String key : keys) {
			System.out.println(key + " " + dic.get(key));
		}
	}

}
