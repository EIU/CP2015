import java.util.ArrayList;

public class TestArrayList {

	public static void main(String[] args) {
		int n = 10 * 100 * 1000;

		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			edges.add(new ArrayList<Integer>());
		}

		edges.get(randBetween(0, n)).add(randBetween(0, n));
	}

	static int randBetween(int l, int h) {
		if (h <= l) {
			h = l + 1;
		}

		return l + (int) Math.floor(Math.random() * (h - l) - 0.000000001);
	}

}
