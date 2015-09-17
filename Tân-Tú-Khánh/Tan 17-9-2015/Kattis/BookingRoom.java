import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		//https://open.kattis.com/problems/bookingaroom
		
		int r = in.nextInt();
		int n = in.nextInt();
		boolean[] isBookedRoom = new boolean[r + 1];
		for (int i = 0; i < n; i++) {
			int a = in.nextInt();
			isBookedRoom[a] = true;
		}
		boolean isLate = true;
		for (int i = 1; i < isBookedRoom.length; i++) {
			if(!isBookedRoom[i]){
				System.out.println(i);
				isLate = false;
				break;
			}
		}
		if (isLate) {
			System.out.println("too late");
		}
	}
}

class InputReader {
	StringTokenizer tokenizer;
	BufferedReader reader;

	public InputReader(InputStream stream) {
		tokenizer = null;
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
	}

	public String next() {
		while ((tokenizer == null) || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public String nextLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
