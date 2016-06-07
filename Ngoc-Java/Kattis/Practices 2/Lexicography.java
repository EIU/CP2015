import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lexicography {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 22);

		StringBuilder outBf = new StringBuilder();
		String line;
		while (!(line = reader.readLine()).startsWith("#")) {
			String[] tokens = line.split(" ");
			char[] chars = tokens[0].toCharArray();
			long K = Long.parseLong(tokens[1]);

			int[] charInstances = new int['Z' - 'A' + 1];
			for (int i = 0; i < chars.length; i++) {
				charInstances[chars[i] - 'A']++;
			}
			process(charInstances, K, outBf);
			outBf.append("\n");
		}
		System.out.println(outBf);
	}

	private static void process(int[] charInstances, long K, StringBuilder outBf) {

		long totalInstance = 1;
		int count = 0;
		for (int i = 0; i < 26; i++) {
			totalInstance *= permutations[count + charInstances[i]] / permutations[count] / permutations[charInstances[i]];
			count += charInstances[i];
		}

		if (count == 0) {
			return;
		}

		int i = 0;
		long start = 0;
		long newValue = 0;
		while (i < 26 && K > start + (newValue = totalInstance * charInstances[i] / count)) {
			start += newValue;
			i++;
		}
		outBf.append((char) (i + 'A'));
		charInstances[i]--;
		process(charInstances, K - start, outBf);
	}

	static long[] permutations = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880,
			3628800, 39916800, 479001600, 6227020800l, 87178291200l, 1307674368000l, 20922789888000l
	};
}
