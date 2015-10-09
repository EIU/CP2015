import java.util.*;

public class BusySchedule {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while (n > 0) {
			Time[] list = new Time[n];
			for (int i = 0; i < n; i++) {
				String[] time = sc.next().split(":");
				int hour = Integer.parseInt(time[0]);
				int minute = Integer.parseInt(time[1]);
				String z = sc.next();
				list[i] = new Time(hour, minute, z);
			}
			Arrays.sort(list);
			for (Time time : list) {
				System.out.println(time);
			}
			n = sc.nextInt();
			System.out.println();
		}
	}

	static class Time implements Comparable<Time> {
		int hour;
		int minute;
		String z;

		int time;

		public Time(int hour, int minute, String z) {
			this.hour = hour;
			this.minute = minute;
			this.z = z;

			if (hour == 12) {
				hour -= 12;
			}
			if (z.charAt(0) == 'p') {
				hour += 12;
			}
			time = hour * 60 + minute;
		}
		@Override
		public int compareTo(Time arg0) {
			return time - arg0.time;
		}

		@Override
		public String toString() {
			String result = "";
			result += hour + ":";
			if (minute < 10) {
				result += "0";
			}
			result += minute + " " + z;
			return result;
		}
	}
}
