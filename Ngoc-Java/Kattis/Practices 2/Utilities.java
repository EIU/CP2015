
public class Utilities {

	public static void main(String[] args) {
	}

	static long gcd(long a, long b) {
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
	
	static int MAX = 200001;
	static int[] maxBIT = new int[MAX];

	static void setValue(int i, int value) {
		for (; i < MAX; i += (i & -i)) {
			maxBIT[i] += value;
		}
	}

	static int getValue(int i) {
		int sum = 0;
		for (; i > 0; i -= (i & -i)) {
			sum += maxBIT[i];
		}
		return sum;
	}
	
	static class SumBIT {
		int size;
		long[] sums;

		public SumBIT(int n) {
			size = n;
			sums = new long[n + 1];
		}

		public void set(int i, long value) {
			for (; i <= size; i += (i & -i)) {
				sums[i] += value;
			}
		}

		public long get(int i) {
			long sum = 0;
			for (; i > 0; i -= (i & -i)) {
				sum += sums[i];
			}
			return sum;
		}
	}
}
