import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Subway {

	static String tourLog;
	static int len;
	static int pos;
	static long hashed1;
	static long hashed2;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 20);

		int T = Integer.parseInt(reader.readLine());
		StringBuffer bf = new StringBuffer();
		for (int t = 0; t < T; t++) {
			tourLog = reader.readLine();
			len = tourLog.length();
			pos = 0;
			hashed1 = decodeAndHash();

			tourLog = reader.readLine();
			len = tourLog.length();
			pos = 0;
			hashed2 = decodeAndHash();

			if (bf.length() != 0) {
				bf.append("\n");
			}
			bf.append(hashed1 == hashed2 ? "same" : "different");
		}
		System.out.println(bf.toString());
	}

	static final long NODEVALUE = 1000000241L;
	static final long MUL = 2038072819L;
	static final long MOD = 32416190071L;

	static long decodeAndHash() {
		long result = NODEVALUE;
		while (pos < len && tourLog.charAt(pos) == '0') {
			pos++;
			result += decodeAndHash();
		}
		pos++;
		return (result * MUL) % MOD;
	}
}
