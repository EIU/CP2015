import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();
			if (n == 0) {
				break;
			}
			int preDay = -2;
			int preAmount = -1;
			int totalDay = 0;
			int totalAmount = 0;
			for (int i = 0; i < n; i++) {
				int day = getDay(sc.nextInt(), sc.nextInt(), sc.nextInt());
				int amount = sc.nextInt();
				if (day == preDay + 1) {
					totalDay++;
					totalAmount += (amount - preAmount);
				}
				preDay = day;
				preAmount = amount;
			}
			System.out.println(totalDay + " " + totalAmount);
		}
	}

	static boolean isLeap(int year) {
		return (year % 4 == 0) && (year % 100 != 0) || year % 400 == 0;
	}

	static final int[] dayInMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	static int getDay(int day, int month, int year) {
		int preYear = year - 1;
		int result = preYear * 365 + preYear / 4 - preYear / 100 + preYear / 400;

		for (int i = 1; i < month; i++) {
			result += dayInMonth[i];
		}
		result += day;
		if (month > 2 && isLeap(year)) {
			result += 1;
		}
		return result;
	}
}
