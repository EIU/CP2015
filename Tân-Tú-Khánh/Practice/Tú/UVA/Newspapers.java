package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Newspapers {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		int n = in.nextInt();
		for(int i = 0; i < n; i++){
			int k = in.nextInt();
			double res = 0;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int j = 0; j < k; j++){
				map.put(in.next(), in.nextInt());
			}
			int m = in.nextInt();
			for(int j = 0; j < m; j++){
				String s = in.nextLine();
				for(int l = 0; l < s.length(); l++){
					String a = "" + s.charAt(l);
					if(map.containsKey(a)){
						res += map.get(a);
					}
				}
			}
			DecimalFormat round = new DecimalFormat("0.00");
			System.out.println(round.format(res/100) +  "$");
		}
	}

	static class InputReader {
		StringTokenizer tokenizer;
		static BufferedReader reader;

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

		public String nextLine() throws IOException {
			return reader.readLine();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public double nextDouble() {
			return Double.parseDouble((next()));
		}
	}
}
