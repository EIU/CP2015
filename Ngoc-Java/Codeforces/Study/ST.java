public class ST {
	static int n = 0;
	static int[] sum;
	static int[] delta;

	static void init(int size) {
		n = size;
		sum = new int[n << 2];
		delta = new int[n << 2];
	}

	static void build() {

	}

	static void build(int id, int left, int right) {

	}

	static void set(int f, int t, int x) {
		set(f, t, x, 0, 0, n - 1);
	}

	static void set(int f, int t, int x, int root, int l, int r) {
		if (f == l && t == r) {
			delta[root] += x;
			return;
		}
		
		
		int mid = (l+r)>>1;
		if(f <= mid){
		}
		if(t > mid){
			
		}
		
	}

	// l <= i < r
	static int get(int l, int r) {
		return 0;
	}

	static void solve(int[] a) {
		init(a.length);
		for (int i = 0; i < a.length; i++) {
			set(i, i, a[i]);
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
