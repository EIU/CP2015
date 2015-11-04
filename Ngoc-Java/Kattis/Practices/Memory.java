import java.io.IOException;

public class Memory {

	final static int MOD = 1000000009;
	public static void main(String[] args) throws IOException {
		byte[] buff = new byte[10002];
		int len = System.in.read(buff);
		while (buff[len - 1] != '0' && buff[len - 1] != '1') {
			len--;
		}

		long result = 1;
		int count0 = 0;
		int count1 = 0;
		long pre0 = 0;
		for (int i = len - 1; i >= -1; i--) {
			if (i == -1 || buff[i] == '0') {
				if (count1 > 0) {
					long temp = pre0;
					pre0 = (count0 * result + pre0) % MOD;
					result = ((count1 * count0 + 1) * result + temp * count1) % MOD;
					count0 = count1 = 0;
				}
				count0++;
			} else {
				count1++;
			}
		}

		System.out.println(result);
	}
}

/*
 * @.10110010
 * 
 * @.......10
 * 
 * @.......02
 * 
 * @...1100
 * 
 * @...1020
 * 
 * @...1012
 * 
 * @...0220
 * 
 * @...0212
 * 
 * @.10
 * 
 * @.02
 */
