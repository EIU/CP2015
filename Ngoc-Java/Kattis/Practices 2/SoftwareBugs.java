import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SoftwareBugs {

	static final int MAX = 200002;
	static final long BASE = 256;
	static final long MOD = 1000000000000223l; // 10^15

	static StringBuilder outBf = new StringBuilder();
	static char[] temp = new char[MAX];
	static long[] hashes = new long[MAX];

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 26);
		String firstLine;

		while ((firstLine = reader.readLine()) != null && firstLine.length() > 0) {
			String[] tokens = firstLine.split(" ");
			int nLine = Integer.parseInt(tokens[0]);

			char[] pattern = tokens[1].toCharArray();
			long maxPow = 1;
			for (int i = 0; i < pattern.length - 1; i++) {
				maxPow = (maxPow * BASE) % MOD;
			}

			long pHash = 0;
			for (int i = 0; i < pattern.length; i++) {
				pHash = (BASE * pHash + pattern[i]) % MOD;
			}

			for (int i = 0; i < nLine; i++) {
				String line = reader.readLine();
				process(line.toCharArray(), pattern.length, maxPow, pHash);
			}
		}
		System.out.println(outBf);
	}

	static void process(char[] text, int pLen, long maxPow, long pHash)
	{
		int tLen = text.length;
		if (pLen > tLen) {
			outBf.append(new String(text) + "\n");
			return;
		}

		long tHash = 0;
		for (int i = 0; i < pLen; i++) {
			hashes[i] = tHash = (BASE * tHash + text[i]) % MOD;
		}

		int iValid = pLen - 1;
		for (int i = 0; i <= tLen - pLen; i++) {
			if (tHash == pHash) {
				iValid -= pLen;
				tHash = iValid < 0 ? 0 : hashes[iValid];
			}

			if (i < tLen - pLen) {
				iValid++;
				text[iValid] = text[i + pLen];
				if (iValid < pLen) {
					hashes[iValid] = tHash = (BASE * tHash + text[iValid]) % MOD;
				} else {
					hashes[iValid] = tHash = (BASE * ((tHash + text[iValid - pLen] * MOD - text[iValid - pLen] * maxPow) % MOD) + text[iValid]) % MOD;
				}
			}
		}

		outBf.append(new String(text, 0, iValid + 1) + "\n");
	}
}
