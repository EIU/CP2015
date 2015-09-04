import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ProblemETest {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			System.out.println("Testcase: " + (t + 1));
			test();
			System.out.println();
			System.out.println();
		}
	}

	static void test() {
		int[] types = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt(),
				sc.nextInt() };
		int n = sc.nextInt();
		int[][][] inputs = new int[][][] { get(types[0]), get(types[1]),
				get(types[2]), get(types[3]) };

		System.out.println(types[0] + " \t " + types[1] + " \t " + types[2]
				+ " \t " + types[3] + " \t " + n);
		System.out.println("Type0 \t Type1 \t Type2 \t Type3");

		int nRow = Math.max(
				Math.max(Math.max(inputs[0].length, inputs[1].length),
						inputs[2].length), inputs[3].length);
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < inputs.length; j++) {
				if (i < inputs[j].length) {
					System.out.printf("%2d %3d\t\t", inputs[j][i][0],
							inputs[j][i][1]);
				}
				else{
					System.out.print("      \t");
				}
			}
			System.out.println();
		}
	}

	static int[][] get(int ni) {
		int[][] ins = new int[ni][2];
		for (int i = 0; i < ni; i++) {
			ins[i][0] = sc.nextInt();
			ins[i][1] = sc.nextInt();
		}

		Arrays.sort(ins, new Comparator<int[]>() {
			public int compare(int[] arg0, int[] arg1) {
				int cmp = arg0[1] - arg1[1];
				if (cmp != 0) {
					return cmp;
				}
				return arg0[0] - arg1[0];
			}
		});
		return ins;
	}

}
