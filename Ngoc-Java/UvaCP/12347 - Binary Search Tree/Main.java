import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 20);
		
		String inputLine = reader.readLine();
		while (inputLine != null && inputLine.length() > 0) {
			values.add(Integer.parseInt(inputLine));
			inputLine = reader.readLine();
		}
		N = values.size();

		parseChild(0, Integer.MAX_VALUE);
		System.out.print(outString.toString());
	}

	static List<Integer> values = new ArrayList<Integer>();
	static int N;
	static StringBuilder outString = new StringBuilder();

	static int parseChild(int position, int right) {
		int value = values.get(position);
		position++;
		if (position < N && values.get(position) < value) {
			position = parseChild(position, value);
		}
		if (position < N && values.get(position) < right) {
			position = parseChild(position, right);
		}
		outString.append(value + "\n");
		return position;
	}
}
