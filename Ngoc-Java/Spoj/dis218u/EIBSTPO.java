import java.io.*;
import java.util.*;

class EIBSTPO {
	static Scanner reader = new Scanner(System.in);
	static int[] values;
	static int[] splitPositions;
	static int[] postOrders;

	public static void main(String[] args) throws IOException {
		int n = reader.nextInt();

		values = new int[n + 1];
		postOrders = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = reader.nextInt();
		}
		values[n] = Integer.MAX_VALUE;

		splitPositions = new int[n];
		Stack<Integer> currentMaxes = new Stack<Integer>();
		for (int i = 0; i <= n; i++) {
			int value = values[i];
			while (!currentMaxes.isEmpty() && values[currentMaxes.peek()] < value) {
				splitPositions[currentMaxes.pop()] = i;
			}
			currentMaxes.push(i);
		}

		postOrder(0, n, 0, n);

		StringBuilder outBuffer = new StringBuilder();
		for (int value : postOrders) {
			outBuffer.append(value + " ");
		}
		System.out.println(outBuffer);
	}

	static void postOrder(int left, int right, int postLeft, int postRight) {
		if (left >= right) {
			return;
		}
		postOrders[postRight - 1] = values[left];
		int midPos = splitPositions[left];
		int count = midPos - left - 1;
		postOrder(left + 1, midPos, postLeft, postLeft + count);
		postOrder(midPos, right, postLeft + count, postRight - 1);
	}
}