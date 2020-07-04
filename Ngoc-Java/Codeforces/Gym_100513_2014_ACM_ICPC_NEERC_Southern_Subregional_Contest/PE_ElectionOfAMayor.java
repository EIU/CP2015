import java.util.*;

public class PE_ElectionOfAMayor {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		int[] votes = new int[n + 1];
		int win = 0, notWin = 0;
		for (int i = 1; i <= n; i++) {
			votes[i] = reader.nextInt() - reader.nextInt();
			win += votes[i] > 0 ? 1 : 0;
		}
		notWin = n - win;

		int mergedFlag = Integer.MAX_VALUE;
		StringBuilder outBf = new StringBuilder();
		int i = 2;
		while (i <= n && win <= notWin) {
			if (votes[i - 1] != mergedFlag) {
				if ((votes[i] <= 0 && (votes[i - 1] + votes[i] > 0))
						|| (votes[i - 1] <= 0 && (votes[i - 1] + votes[i] > 0))
						|| (votes[i - 1] <= 0 && votes[i] <= 0)) {
					notWin--;
					votes[i] = mergedFlag;
					outBf.append((i - 1) + " " + i + "\r\n");
				}
			}
			i++;
		}
		System.out.println(win > notWin ? (n - (win + notWin) + "\r\n" + outBf) : -1);
	}
}
