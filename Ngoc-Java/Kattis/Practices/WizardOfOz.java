import java.util.*;

public class WizardOfOz {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextLong();
		final int MAX = 400;
		long[][][] arrayTypes = new long[MAX][4][4];
		long grandSum = 0;

		arrayTypes[0][0][0] = 1;

		int i;
		for (i = 1; i < MAX; i++) {
			long[][] preTypes = arrayTypes[i - 1];
			long[][] types = arrayTypes[i];
			long sumo = 0;
			long sumz = 0;
			for (int o = 0; o < 4; o++) {
				for (int z = 0; z < 4; z++) {
					if (o < 3) {
						types[o + 1][z > 0 ? z - 1 : 0] += preTypes[o][z];
						sumo += preTypes[o][z];
					}
					if (z < 3) {
						types[o > 0 ? o - 1 : 0][z + 1] += preTypes[o][z];
						sumz += preTypes[o][z];
					}
				}
			}

			if (grandSum + sumo + sumz >= n) {
				break;
			}
			grandSum += sumo + sumz;
		}

		StringBuilder result = new StringBuilder();
		n -= grandSum;
		int co = 0;
		int cz = 0;
		for (; i > 0; i--) {
			long sumo = 0;
			long[][] preTypes = arrayTypes[i - 1];
			for (int o = 0; o < 4; o++) {
				for (int z = 0; z < 4; z++) {
					if (o + co < 3 && z + cz < 5) {
						sumo += preTypes[o][z];
					}
				}
			}

			if (n <= sumo) {
				result.append("o");
				co++;
				cz = cz > 0 ? cz - 1 : 0;
			} else {
				result.append("z");
				n -= sumo;
				cz++;
				co = co > 0 ? co - 1 : 0;
			}
		}
		System.out.println(result.toString());
	}
}
