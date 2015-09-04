public class ST1 {
	static int length;
	static int[] sum;

	static void initSum(int n) {
		int log = (int) Math.ceil(Math.log(n) / Math.log(2));
		length = (int) Math.pow(2, log);
		sum = new int[2 * length];
	}

	static void set(int i, int x) {
		for (i += length; i > 0; i >>= 1) {
			sum[i] += x;
		}
	}

	// l <= i < r
	static int get(int l, int r) {
		int result = 0;
		for (l += length, r += length; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) != 0) {
				result += sum[l];
				l++;
			}
			if ((r & 1) != 0) {
				r--;
				result += sum[r];
			}
		}
		return result;
	}

	static void solve(int[] a) {
		initSum(a.length);
		for (int i = 0; i < a.length; i++) {
			set(i, a[i]);
		}

		for (int i = 1; i <= a.length; i++) {
			System.out.print(get(0, i) + " ");
		}
		System.out.println();
		for (int i = 2; i <= a.length; i++) {
			System.out.print(get(1, i) + " ");
		}
		System.out.println();
		for (int i = 3; i <= a.length; i++) {
			System.out.print(get(2, i) + " ");
		}
		System.out.println();
		for (int i = 4; i <= a.length; i++) {
			System.out.print(get(3, i) + " ");
		}
		System.out.println();
		for (int i = 5; i <= a.length; i++) {
			System.out.print(get(4, i) + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		solve(new int[] { 4, 3, 7, 5, 4, 8, 2, 5, 8, 3, 5, 9 });
		solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
		solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
		solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 });
	}
}
