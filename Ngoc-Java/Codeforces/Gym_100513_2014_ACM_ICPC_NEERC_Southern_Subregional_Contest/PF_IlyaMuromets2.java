import java.util.*;

public class PF_IlyaMuromets2 {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt(), k = reader.nextInt();
		long[] sumks = new long[k + k + n];
		long preMaxSegment = 0;
		long max2Segments = 0;
		for (int i = k + k; i < sumks.length; i++) {
			int fi = reader.nextInt();
			sumks[i] = sumks[i - 1] + fi;
			preMaxSegment = Math.max(preMaxSegment, sumks[i - k] - sumks[i - k - k]);
			max2Segments = Math.max(max2Segments, preMaxSegment + sumks[i] - sumks[i - k]);
		}
		System.out.println(max2Segments);
	}
}
