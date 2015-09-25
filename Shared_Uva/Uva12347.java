import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Task solver = new Task();
		solver.solve();
	}
}

class Task {
	Queue<Integer> preOrder;

	public void solve() throws IOException {
		preOrder = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			preOrder.add(sc.nextInt());
		}
		recur(10000000);
		sc.close();
	}

	private void recur(int parent) {
		int cur = preOrder.poll();
		if (!preOrder.isEmpty() && preOrder.peek() < cur) {
			recur(cur);
		}
		if (!preOrder.isEmpty() && preOrder.peek() < parent) {
			recur(parent);
		}
		System.out.println(cur);
	}
}
