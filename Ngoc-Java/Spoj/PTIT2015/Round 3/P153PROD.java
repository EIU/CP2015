import java.util.Arrays;
import java.util.Scanner;

public class P153PROD {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] matrix = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}

		/**
		 * @Complexity: O(n^3) - It is simple. There must be O(n^2 * log(n)) solution, but it will be quite complex
		 * 
		 */
		int[] parents = new int[n + 1];
		int[] levels = new int[n + 1];
		Arrays.fill(parents, 1000);
		Arrays.fill(levels, 1000);
		int count = 0;
		for (int level = 0; level < n && count < n; level++) {
			for (int i = 1; i <= n; i++) {
				if (parents[i] != 1000) {
					continue;
				}
				int countChild = 0;
				int nodeInBranch = 0;
				int parent = 1000;
				for (int j = 1; j <= n; j++) {
					if (levels[matrix[i][j]] < level) {
						continue;
					}
					else {
						nodeInBranch++;
					}
					if (levels[matrix[i][j]] == level) {
						parent = matrix[i][j];
					}
					if (matrix[i][j] == i) {
						countChild++;
					}
				}

				if (nodeInBranch == countChild) {
					levels[i] = level + 1;
					parents[i] = parent;
					count++;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.print(parents[i] + " ");
		}
	}
}
