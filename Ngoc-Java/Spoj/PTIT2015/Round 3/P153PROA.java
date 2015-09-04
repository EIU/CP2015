import java.util.*;
import java.math.*;

class P153PROA {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arrays = new int[n];
		for (int i = 0; i < n; i++) {
			arrays[i] = sc.nextInt();
		}
		Arrays.sort(arrays);

		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Integer> counts = new ArrayList<Integer>();

		int count = 1;
		for (int i = 0; i < n; i++) {
			if (i == n - 1 || arrays[i] != arrays[i + 1]) {
				values.add(arrays[i]);
				counts.add(count);
				count = 1;
			}
			else {
				count++;
			}
		}

		BigInteger P = BigInteger.valueOf(1000000007);
		int result = 0;
		int len = values.size();
		for (int i = 0; i < len; i++) {
			if (counts.get(i) > 0) {
				int v = values.get(i);
				int inv = BigInteger.valueOf(v).modInverse(P).intValue();
				if (v == inv) { // v = 1 || v == P - 1
					result += counts.get(i) / 2;
					counts.set(i, 0);
				}
				else {
					int invIndex = Collections.binarySearch(values, inv);
					if (invIndex >= 0) {
						result += Math.min(counts.get(i), counts.get(invIndex));
						counts.set(i, 0);
						counts.set(invIndex, 0);
					}
				}
			}
		}
		System.out.println(result);
	}
}