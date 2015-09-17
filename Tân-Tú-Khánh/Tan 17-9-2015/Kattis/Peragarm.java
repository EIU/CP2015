import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//https://open.kattis.com/problems/peragrams
public class Main {

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		String s = in.next();
		int[] a = new int[300];
		int length = s.length();
		for (int i = 0; i < length; i++) {
			a[s.charAt(i)]++;
		}
		boolean isOdd = false;
		int count =0;
		for (int i = (int)'a'; i <= (int)'z' ; i++) {
			if(a[i] % 2 == 1 ){
				if(!isOdd){
					isOdd = true;
				} else{
					count ++;
				}
			}
		}
		System.out.println(count);

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
