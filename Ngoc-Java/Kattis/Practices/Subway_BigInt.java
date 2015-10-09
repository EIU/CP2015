import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;


// Failed in any chosen primes!
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

	static BigInteger One = BigInteger.valueOf(0);
	static BigInteger NODEVALUE = BigInteger.valueOf(1000000241L);
	static BigInteger MUL = BigInteger.valueOf(2038072819L);
	static BigInteger MOD = BigInteger.valueOf(32416190071L);

	static BigInteger decodeAndHash() {
		BigInteger result = NODEVALUE;
		int t = 2;
		while (pos < len && tourLog.charAt(pos) == '0') {
			pos++;
			result = result.add(decodeAndHash()).multiply(BigInteger.valueOf(t));
		}
		pos++;
		return result.multiply(MUL).mod(MOD);
	}
}
