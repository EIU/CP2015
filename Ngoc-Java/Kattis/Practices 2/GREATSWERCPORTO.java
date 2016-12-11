import java.io.*;
import java.util.*;

public class GREATSWERCPORTO {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 10);
		int N = Integer.parseInt(reader.readLine());

		HashMap<Character, Node> digits = new HashMap<Character, Node>();

		for (int i = 0; i < N; i++) {
			char[] chars = reader.readLine().toCharArray();
			int pow10 = i == N - 1 ? -1 : 1;
			for (int j = chars.length - 1; j >= 0; j--) {
				Node node = digits.get(chars[j]);
				if (node == null) {
					node = new Node();
					digits.put(chars[j], node);
				}
				node.base += pow10;
				pow10 *= 10;
			}
			digits.get(chars[0]).isFirst = true;
		}

		// long s = System.currentTimeMillis();

		nodes = new Node[digits.values().size()];
		digits.values().toArray(nodes);
		Arrays.sort(nodes, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return Math.abs(n1.base) - Math.abs(n2.base);
			}
		});

		int len = nodes.length;
		sumPossitives = new int[len];
		sumNagatives = new int[len];
		for (int i = 1; i < len; i++) {
			if (nodes[i - 1].base > 0) {
				sumPossitives[i] = sumPossitives[i - 1] + nodes[i - 1].base;
				sumNagatives[i] = sumNagatives[i - 1];
			} else {
				sumPossitives[i] = sumPossitives[i - 1];
				sumNagatives[i] = sumNagatives[i - 1] + nodes[i - 1].base;
			}
		}

		test(0, len - 1);
		System.out.println(count);

		// System.out.println((System.currentTimeMillis() - s) + " ms");
	}

	static int count = 0;
	static Node[] nodes;
	static int[] sumPossitives;
	static int[] sumNagatives;
	static boolean[] usedDigits = new boolean[10];

	static void test(int sum, int level) {
		int base = nodes[level].base;

		int maxDigit = 9;
		while (maxDigit >= 0 && usedDigits[maxDigit]) {
			maxDigit--;
		}

		int end = 10;
		for (int i = nodes[level].isFirst ? 1 : 0; i < end; i++) {
			if (usedDigits[i]) {
				continue;
			}

			usedDigits[i] = true;
			int newSum = sum + i * base;
			if (level == 0 && newSum == 0) {
				count++;
			} else if (level > 0) {
				if (maxDigit == i) {
					while (maxDigit >= 0 && usedDigits[maxDigit]) {
						maxDigit--;
					}
					end = 0;
				}
				if (newSum + maxDigit * sumPossitives[level] >= 0
						&& newSum + maxDigit * sumNagatives[level] <= 0) {
					test(newSum, level - 1);
				}
			}
			usedDigits[i] = false;
		}
	}
	static class Node {
		public int base;
		public boolean isFirst;
	}
}
