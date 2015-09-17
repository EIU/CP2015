package uva;

import java.util.Scanner;

public class RobotInstructions {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int i = 1; i <= test; i++) {
			int n = scan.nextInt();
			int result = 0;
			int[] action = new int[n + 1];
			String[] instructions = new String[n + 1];
			for (int j = 1; j <= n; j++) {
				instructions[j] = scan.next();
				if (instructions[j].toLowerCase().equals("left")
						|| instructions[j].toLowerCase().equals("right")) {
					result += compare(instructions[j]);
				} else {
					scan.next();
					action[j] = scan.nextInt();
					instructions[j] = instructions[action[j]];
					result += compare(instructions[j]);
				}
			}
			System.out.println(result);
		}
		scan.close();
	}

	private static int compare(String string) {
		int result = 0;
		if (string.toLowerCase().equals("left")) {
			result--;
		} else if (string.toLowerCase().equals("right")) {
			result++;
		}
		return result;
	}

}
