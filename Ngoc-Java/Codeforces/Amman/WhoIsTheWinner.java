import java.util.*;

public class WhoIsTheWinner {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int T = reader.nextInt();
		for (int t = 0; t < T; t++) {
			int N = reader.nextInt();
			String maxName = "";
			int maxProblem = 0, minPenaty = 0;
			for (int i = 0; i < N; i++) {
				String name = reader.next();
				int problem = reader.nextInt();
				int penaty = reader.nextInt();
				if (problem > maxProblem || (problem == maxProblem && penaty < minPenaty)) {
					maxName = name;
					maxProblem = problem;
					minPenaty = penaty;
				}
			}
			System.out.println(maxName);
		}
	}
}


