import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[][] rules = {
				{ "+x", "+y", "+y" },
				{ "+x", "-y", "-y" },
				{ "-x", "+y", "-y" },
				{ "-x", "-y", "+y" },
				{ "+y", "+y", "-x" },
				{ "+y", "-y", "+x" },
				{ "-y", "+y", "+x" },
				{ "-y", "-y", "-x" },

				{ "+x", "+z", "+z" },
				{ "+x", "-z", "-z" },
				{ "-x", "+z", "-z" },
				{ "-x", "-z", "+z" },
				{ "+z", "+z", "-x" },
				{ "+z", "-z", "+x" },
				{ "-z", "+z", "+x" },
				{ "-z", "-z", "-x" }
		};
		int ruleLen = rules.length;
		while (sc.hasNext()) {
			int L = sc.nextInt();
			if (L == 0) {
				break;
			}
			String v = "+x";
			for (int i = 0; i < L - 1; i++) {
				String bend = sc.next();
				for (int j = 0; j < ruleLen; j++) {
					String[] rule = rules[j];
					if (rule[0].equals(v) && rule[1].equals(bend)) {
						v = rule[2];
						break;
					}
				}
			}
			System.out.println(v);
		}
	}
}
