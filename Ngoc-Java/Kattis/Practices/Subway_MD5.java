import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Subway_MD5 {

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

	static MessageDigest crypt;
	static {
		try {
			crypt = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	static BigInteger NODEVALUE = BigInteger.valueOf(1);

	static BigInteger decodeAndHash() {
		BigInteger result = NODEVALUE;
		while (pos < len && tourLog.charAt(pos) == '0') {
			pos++;
			result = result.add(decodeAndHash());
		}
		pos++;
		crypt.reset();
		crypt.update(result.toByteArray());
		return new BigInteger(1, crypt.digest());
	}
}
