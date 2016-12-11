import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PowerStrings {

	static final long BASE = 256;
	static final long BASEBIT = 8;
	static final long MOD = 1000000000039L; //1000000000000223l; // 10^15
	static final long MOD2 = 5724673202014573l; // 10^15

	static final int MAXLEN = 2000001;
	static long[] basePowers = new long[MAXLEN];

	static {
		long mod = 1;
		for (int i = 0; i < MAXLEN; i++) {
			basePowers[i] = mod;
			mod = ((mod << 8) % MOD);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 25);

		String text = ".";
		while (!(text = reader.readLine()).equals(".")) {
			int textLen = text.length();
			long hashText = getHash(text, 0, textLen);
			long curHash = 0;
			for (int i = 1; i <= textLen; i++) {
				if (textLen % i == 0) {
					curHash = (BASE * curHash + text.charAt(i - 1)) % MOD;
					long hashPower = getHashPower(curHash, i, textLen / i);
					if (hashPower == hashText) {
						System.out.println(textLen / i);
						break;
					}
				}
			}
		}
	}

	static long getHashPower(long hash, int len, int n) {
		long base = basePowers[len];
		for (int i = 0; i < n - 1; i++) {
			hash = (hash * base + hash) % MOD;
		}
		return hash;
	}

	static long getHash(String text, int from, int to) {
		long hash = 0;
		for (; from < to; from++) {
			hash = (BASE * hash + text.charAt(from)) % MOD;
		}
		return hash;
	}
}
