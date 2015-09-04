import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			solve(sc);
		}
	}

	static void solve(Scanner sc) {
		int R = sc.nextInt();
		int C = sc.nextInt();
		int deep = Math.min(R, C) + 1;

		int[][][] gridValue = new int[R][C][deep];

		int maxValue = 1000 * 100 * 100;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				Arrays.fill(gridValue[i][j], maxValue);
				gridValue[i][j][0] = sc.nextInt();
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int[] values = gridValue[i][j];
				if (i == 0 || j == 0) {
					values[1] = values[0];
				}
				else {
					int[] preValues = gridValue[i - 1][j - 1];
					int[] preTopValues = gridValue[i - 1][j];
					int[] preLeftValues = gridValue[i][j - 1];

					values[1] = Math.min(
							Math.min(
									Math.min(preValues[1], values[0]),
									preTopValues[1]),
							preLeftValues[1]);
					for (int k = 2; k < deep; k++) {
						values[k] = Math.min(
								Math.min(
										Math.min(preValues[k], preValues[k - 1] + values[0]),
										preTopValues[k]),
								preLeftValues[k]);
					}
				}
			}
		}
		int[] minValues = Arrays.copyOf(gridValue[R - 1][C - 1], deep);
		minValues[0] = 0;

		int minValue = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				Arrays.fill(gridValue[i][j], 1, deep, minValue);
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int[] values = gridValue[i][j];
				if (i == 0 || j == 0) {
					values[1] = values[0];
				}
				else {
					int[] preValues = gridValue[i - 1][j - 1];
					int[] preTopValues = gridValue[i - 1][j];
					int[] preLeftValues = gridValue[i][j - 1];

					values[1] = Math.max(
							Math.max(
									Math.max(preValues[1], values[0]),
									preTopValues[1]),
							preLeftValues[1]);
					for (int k = 2; k < deep; k++) {
						values[k] = Math.max(
								Math.max(
										Math.max(preValues[k], preValues[k - 1] + values[0]),
										preTopValues[k]),
								preLeftValues[k]);
					}
				}
			}
		}

		int[] maxValues = Arrays.copyOf(gridValue[R - 1][C - 1], deep);
		maxValues[0] = 0;

		int result = 0;
		for (int i = 0; i < deep / 2; i++) {
			int newValue = maxValues[i] - minValues[i * 2];
			if (newValue > result) {
				result = newValue;
			}
		}
		System.out.println(result);
	}
}
