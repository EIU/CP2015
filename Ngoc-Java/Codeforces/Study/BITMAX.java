public class BITMAX {

	static int length;
	static int[] max;

	static void initSum(int n) {
		length = n;
		max = new int[length + 1];
	}

	static void add(int i, int x) {
		for (; i <= length; i += (i & -i)) {
			max[i] = Math.max(max[i], x);
		}
	}

	static int get(int i) {
		int result = 0;
		for (; i > 0; i -= (i & -i)) {
			result = Math.max(result, max[i]);
		}
		return result;
	}

	static void solve(int[] a) {
		initSum(a.length);
		for (int i = 0; i < a.length; i++) {
			add(i + 1, a[i]);
		}

		for (int i = 0; i < a.length; i++) {
			System.out.print(get(i + 1) + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		solve(new int[] { 4, 3, 7, 5, 4, 8, 2, 5, 8, 9, 5, 8 });
		solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
		solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
		solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 });
	}
}
