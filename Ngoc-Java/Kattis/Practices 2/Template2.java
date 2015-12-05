import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Template2 {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 22);
		int T = Integer.parseInt(reader.readLine());
		
		for (int t = 0; t < T; t++) {
			solve();
		}
	}

	static void solve() {

	}
}
