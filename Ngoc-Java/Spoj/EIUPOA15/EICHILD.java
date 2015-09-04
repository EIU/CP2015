import java.util.*;

class EICHILD {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();

		int[][] sum2D = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			int sumi = 0;
			int[] rowi1 = sum2D[i - 1];
			int[] rowi = sum2D[i];
			for (int j = 1; j <= m; j++) {
				sumi += sc.nextInt();
				rowi[j] = rowi1[j] + sumi;
			}
		}

		int MAXVALUE = 10000 * 1000;
		int[] countValues = new int[MAXVALUE + 1];
		int total = 0;
		for (int top = 0; top < n; top++) {
			for (int bottom = top + 1; bottom <= n; bottom++) {
				int width = bottom - top;
				int[] rowTop = sum2D[top];
				int[] rowBottom = sum2D[bottom];
				for (int left = 0; left < m; left++) {
					for (int right = left + 1; right <= m; right++) {
						int height = right - left;
						int area = width * height;
						if (a <= area && area <= b) {
							float average = (rowBottom[right] - rowBottom[left] - rowTop[right] + rowTop[left]) / (float) area;
							int value = (int) Math.round(average * 1000);
							countValues[value]++;
							total++;
						}
					}
				}
			}
		}

		int validValue = 0;
		int count = 0;
		int mid = total / 2;
		boolean isOdd = (total % 2) == 1;
		for (int i = 0; i <= MAXVALUE; i++) {
			int counti = countValues[i];
			if (counti > 0) {
				if (count + counti > mid) {
					if (isOdd || count < mid - 1) {
						System.out.println(i / 1000.0);
					} else {
						System.out.println((validValue + i) / 2000.0);
					}
					break;
				}
				validValue = i;
				count += counti;
			}
		}

	}
}
