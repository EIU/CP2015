public class SegmentTreeMaxExample {
	public static void main(String[] args) {
		int[] data = new int[] { 1, 2, 3, 4, 5 };
		SegmentTree st = new SegmentTree(data);
		System.out.println(st.getMax(0, 4) == 5);
		st.update(0, 6);
		System.out.println(st.getMax(0, 4) == 6);
		System.out.println(st.getMax(1, 4) == 5);
		System.out.println(st.getMax(2, 4) == 5);
		st.update(1, 10);
		System.out.println(st.getMax(0, 0) == 6);
		System.out.println(st.getMax(1, 1) == 10);
		st.update(3, 9);
		System.out.println(st.getMax(2, 6) == 9);
	}

	static class SegmentTree {
		private int[] max;
		private int n;
		private int[] data;

		public SegmentTree(int[] data) {
			this.data = data;
			n = data.length;
			max = new int[4 * n + 10];
			buildTree(1, 0, n - 1);
		}

		private int left(int root) {
			return (root << 1);
		}

		private int right(int root) {
			return (root << 1) + 1;
		}

		private void buildTree(int root, int rl, int rr) {
			if (rl == rr) {
				max[root] = data[rl];
			} else {
				int rm = (rl + rr) >> 1;
				buildTree(left(root), rl, rm);
				buildTree(right(root), rm + 1, rr);
				max[root] = Math.max(max[left(root)], max[right(root)]);
			}

		}

		public void update(int pos, int val) {
			update(1, 0, n - 1, pos, val);
		}

		private void update(int root, int rl, int rr, int pos, int v) {
			if (rl > rr || rl > pos || rr < pos) {
				return;
			}
			if (rl == pos && rr == pos) {
				max[root] = v;
				return;
			}
			int rm = (rr + rl) >> 1;
			update(left(root), rl, rm, pos, v);
			update(right(root), rm + 1, rr, pos, v);
			max[root] = Math.max(max[left(root)], max[right(root)]);
		}

		public int getMax(int l, int r) {
			return getMax(1, 0, n - 1, l, r);
		}

		private int getMax(int root, int rl, int rr, int l, int r) {
			if (rl > rr || rl > r || l > rr) {
				return Integer.MIN_VALUE;
			}
			if (rl == l && r == rr) {
				return max[root];
			}
			int rm = (rr + rl) >> 1;
			int m1 = getMax(left(root), rl, rm, l, Math.min(rm, r));
			int m2 = getMax(right(root), rm + 1, rr, Math.max(l, rm + 1), r);
			return Math.max(m1, m2);
		}
	}
}
