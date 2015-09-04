import java.util.Scanner;

class EPICTURE2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			sc.nextLine();
			String[] lines = new String[N];
			for (int i = 0; i < N; i++) {
				lines[i] = sc.nextLine();
			}
			int top = N, left = M, bottom = 0, right = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (lines[i].charAt(j) == '*') {
						top = Math.min(i, top);
						left = Math.min(j, left);
						bottom = Math.max(i, bottom);
						right = Math.max(j, right);
					}
				}
			}
			StringBuilder builder = new StringBuilder();
			if (left <= right) {
				for (int i = top; i <= bottom; i++) {
					builder.append(lines[i].substring(left, right + 1) + "\r\n");
				}
			}
			System.out.println(builder.toString());
		}
	}

}
