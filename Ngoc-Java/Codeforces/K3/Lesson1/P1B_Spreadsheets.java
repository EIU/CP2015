import java.util.Scanner;

public class P1B_Spreadsheets {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			String coordinate = scan.next();

			int indexR = coordinate.indexOf("R");
			int indexC = coordinate.indexOf('C');

			if (indexR == 0 && indexC > 1 && Character.isDigit(coordinate.charAt(1))) {
				int row = Integer.parseInt(coordinate.substring(1, indexC));
				int column = Integer.parseInt(coordinate.substring(indexC + 1));
				System.out.println(toAZCoordinate(column) + row);
			} else {
				int separator = 1;
				while (!Character.isDigit(coordinate.charAt(separator))) {
					separator++;
				}

				String azNumber = coordinate.substring(0, separator);
				String row = coordinate.substring(separator);

				System.out.println("R" + row + "C" + toRCCoordinate(azNumber));
			}
		}
	}

	static String toAZCoordinate(int number) {
		String result = "";
		while (number > 0) {
			result = (char) ((number - 1) % 26 + 'A') + result;
			number = (number - 1) / 26;
		}
		return result;
	}

	static int toRCCoordinate(String azNumber) {
		int column = 0;
		for (int j = 0; j < azNumber.length(); j++) {
			column *= 26;
			column += azNumber.charAt(j) - 'A' + 1;
		}
		return column;
	}
}