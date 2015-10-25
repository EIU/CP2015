import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Minions {
	static BufferedReader reader;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 20);

		String text = reader.readLine();
		int len = text.length();

		int count = 0;
		StringBuilder buffer = new StringBuilder();
		int i = 0, j = i + 1, k = j, nexti = 0;
		for (; i < len; i = nexti) {
			for (j = i + 1; j < len;) {
				int cmp = text.charAt(i) - text.charAt(j);
				if (cmp > 0) {
					nexti = j;
					break;
				} else if (cmp < 0) {
					j++;
					nexti = j;
					continue;
				}

				boolean suffix = false;
				for (k = j + 1; k < len; k++) {
					cmp = text.charAt(k) - text.charAt(i + k - j);
					if (cmp > 0) {
						suffix = true;
						break;
					} else if (cmp < 0) {
						suffix = false;
						break;
					}
				}
				if (!suffix) {
					nexti = j;
					break;
				} else {
					j++;
				}
			}
			if (nexti <= i) {
				break;
			}
			count++;
			if (buffer.length() > 0) {
				buffer.append(" ");
			}
			buffer.append(nexti - i);
		}

		if (nexti < len) {
			count++;
			if (buffer.length() > 0) {
				buffer.append(" ");
			}
			buffer.append(len - nexti);
		}

		System.out.println(count + " " + buffer.toString());
	}
}
