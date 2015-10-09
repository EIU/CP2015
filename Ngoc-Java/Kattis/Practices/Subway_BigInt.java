import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Subway_BigInt {

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
			hashed1 = decodeAndHash().longValue();

			tourLog = reader.readLine();
			len = tourLog.length();
			pos = 0;
			hashed2 = decodeAndHash().longValue();

			if (bf.length() != 0) {
				bf.append("\n");
			}
			bf.append(hashed1 == hashed2 ? "same" : "different");
		}
		System.out.println(bf.toString());
	}

	static BigInteger NODEVALUE = BigInteger.valueOf(3425679203044211L);
	static BigInteger MUL = BigInteger.valueOf(1000000000000223L);
	static BigInteger MOD = BigInteger.valueOf(5724673202014703L);

	static BigInteger decodeAndHash() {
		BigInteger result = NODEVALUE;
		int t = 1;
		while (pos < len && tourLog.charAt(pos) == '0') {
			pos++;
			result = result.add(decodeAndHash());
			t++;
		}
		pos++;
		return result.multiply(MUL).multiply(BigInteger.valueOf(t)).mod(MOD);
	}
}
