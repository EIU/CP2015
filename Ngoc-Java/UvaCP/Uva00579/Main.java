import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String time = sc.nextLine();
			if (time == "" || time == "0:00") {
				break;
			}

			StringTokenizer tokens = new StringTokenizer(time, ":");
			int H = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());

			if (H == 0 && M == 0) {
				break;
			}

			double angle1 = M * 6;
			double angle2 = (double) H * 30 + (double) M / 2;

			double angle = Math.abs(angle1 - angle2);
			if (angle > 180) {
				angle = 360 - angle;
			}

			System.out.printf("%.3f\n", angle);
		}
	}
}
