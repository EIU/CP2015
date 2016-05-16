import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProblemF {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		String[] days = new String[]{"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

		for (int t = 0; t < T; t++) {
			int m = sc.nextInt();
			int d = sc.nextInt();
			int y = sc.nextInt();
			int n = sc.nextInt();

			//SimpleDateFormat format = new SimpleDateFormat(", MM/dd/yyyy");
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.set(y, m - 1, d);
			calendar.add(Calendar.DATE, n);
			String result = days[calendar.get(Calendar.DAY_OF_WEEK)] + ", ";
			result += format(calendar.get(Calendar.MONTH) + 1) + "/";
			result += format(calendar.get(Calendar.DATE)) + "/";
			result += calendar.get(Calendar.YEAR);

			//System.out.println(calendar.getFirstDayOfWeek());
			System.out.println(result);

			// System.out.println(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US) + format.format(calendar.getTime()));
			// System.out.println(days[calendar.get(Calendar.DAY_OF_WEEK)] + format.format(calendar.getTime()));
		}
	}

	static String format(int i) {
		String s = "0" + i;
		return s.substring(s.length() - 2, s.length());
	}
}
