package uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IDCodes {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		String s = null;
		while(true){
			s = in.next();
			if(s.equals("#")){
				break;
			}
			int a[] = new int[127];
			for(int i = 0; i < s.length(); i++){
				a[s.charAt(i)]++;
			}
			int max = Integer.MIN_VALUE;
			int sum = 0;
			int pos = -1;
			String res = "";
			for(int i = 0; i < 127; i++){
				if(max < a[i]){
					max = a[i];
					pos = i;
				}
				sum += a[i];
			}
			if(max != sum-max){
				System.out.println("No Successor");
			}
			else{
				for(int i = 0; i < 127; i++){
					if(a[i] != 0 && i != pos){
						for(int j = 0; j < a[i]; j++){
							res += (char) pos;
							res += (char) i;
						}
					}
				}
				System.out.println(res);
			}
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
