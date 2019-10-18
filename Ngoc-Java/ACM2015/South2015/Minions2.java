import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Minions2 {
	static BufferedReader reader;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 21);

		String text = reader.readLine();
		int len = text.length();

		int count = 0;
		StringBuilder buffer = new StringBuilder();
		int i = 0, j = i + 1;
		for (; i < len; i = j) {
			for (j = i + 1; j < len; j++) {
				int cmp = text.charAt(i) - text.charAt(j);
				if (cmp > 0) {
					break;
				} else if (cmp < 0) {
					continue;
				}

				boolean suffix = false;
				for (int k = j + 1; k < len; k++) {
					cmp = text.charAt(k) - text.charAt(i + k - j);
					if (cmp > 0) {
						suffix = true;
						break;
					} else if (cmp < 0) {
						break;
					}
				}
				if (!suffix) {
					break;
				}
			}
			count++;
			if (buffer.length() > 0) {
				buffer.append(" ");
			}
			buffer.append(j - i);
		}

		if (j < len) {
			count++;
			if (buffer.length() > 0) {
				buffer.append(" ");
			}
			buffer.append(len - j);
		}

		System.out.println(count + " " + buffer.toString());
	}
}
