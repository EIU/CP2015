import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Island {
	static BufferedReader reader;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 20);

		lines = new ArrayList<String>();
		int t = 1;
		do {
			lines.clear();
			String inputLine = reader.readLine();
			while (inputLine != null && inputLine.length() > 0) {
				lines.add(inputLine);
				inputLine = reader.readLine();
			}
			if (lines.size() > 0) {
				if (t > 1) {
					System.out.println();
				}
				System.out.println("Map " + t);
				solve();
				t++;
			}

		} while (lines.size() > 0);

	}

	static int nRow = 0;
	static int nColumn = 0;
	static boolean[][] visiteds;
	static ArrayList<String> lines;

	static void solve() {
		nRow = lines.size();
		nColumn = lines.get(0).length();

		visiteds = new boolean[nRow][nColumn];
		int nIsland = 0;
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nColumn; j++) {
				nIsland += checkIslands(i, j);
			}
		}
		System.out.println("islands: " + nIsland);

		clearVisisted();
		int nBridge = 0;
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nColumn; j++) {
				nBridge += checkBridges(i, j);
			}
		}
		System.out.println("bridges: " + nBridge);

		clearVisisted();
		int nBus = 0;
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nColumn; j++) {
				nBus += checkBuses(i, j, '0');
			}
		}
		System.out.println("buses needed: " + nBus);
	}

	static void clearVisisted() {
		for (boolean[] line : visiteds) {
			Arrays.fill(line, false);
		}
	}

	static int checkIslands(int i, int j) {
		if (i < 0 || i >= nRow || j < 0 || j >= nColumn || visiteds[i][j]) {
			return 0;
		}
		char c = lines.get(i).charAt(j);
		if (c != 'X' && c != '#') {
			return 0;
		}
		visiteds[i][j] = true;
		checkIslands(i + 1, j);
		checkIslands(i - 1, j);
		checkIslands(i, j + 1);
		checkIslands(i, j - 1);
		return 1;
	}

	// ..#XBBBBX#
	// #XBBBBX#..
	// => Should be wrong. Testcases are week or problem statement misses something?
	static int checkBridges(int i, int j) {
		if (i < 0 || i >= nRow || j < 0 || j >= nColumn || visiteds[i][j]) {
			return 0;
		}
		char c = lines.get(i).charAt(j);
		if (c != 'B') {
			return 0;
		}
		visiteds[i][j] = true;
		checkBridges(i + 1, j);
		checkBridges(i - 1, j);
		checkBridges(i, j + 1);
		checkBridges(i, j - 1);
		return 1;
	}

	static int checkBuses(int i, int j, char pre) {
		if (i < 0 || i >= nRow || j < 0 || j >= nColumn || visiteds[i][j]) {
			return 0;
		}
		char c = lines.get(i).charAt(j);
		if (c == '.' || pre + c == '#' + 'B') {
			return 0;
		}
		visiteds[i][j] = true;
		checkBuses(i + 1, j, c);
		checkBuses(i - 1, j, c);
		checkBuses(i, j + 1, c);
		checkBuses(i, j - 1, c);
		return 1;
	}
}
