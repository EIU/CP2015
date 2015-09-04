public class RMQ {

	static int length;
	static int[] rmax;

	static void initSum(int n) {
		int log = (int) Math.ceil(Math.log(n) / Math.log(2));
		length = (int) Math.pow(2, log);
		rmax = new int[2 * length];
	}

	static void set(int i, int x) {
		for (i += length; i > 0; i >>= 1) {
			rmax[i] = Math.max(rmax[i], x);
		}
	}

	// l <= i < r
	static int get(int l, int r) {
		int result = 0;
		for (l += length, r += length; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) != 0) {
				result = Math.max(result, rmax[l]);
				l++;
			}
			if ((r & 1) != 0) {
				r--;
				result = Math.max(result, rmax[r]);
			}
		}
		return result;
	}

	static void solve(int[] a) {
		initSum(a.length);
		for (int i = 0; i < a.length; i++) {
			set(i, a[i]);
		}

		for (int i = 0; i <= a.length; i++) {
			System.out.print(get(0, i) + " ");
		}
		System.out.println();
		for (int i = 2; i <= a.length; i++) {
			System.out.print(get(2, i) + " ");
		}
		System.out.println();
		for (int i = 4; i <= a.length; i++) {
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
