import java.io.FileWriter;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Scanner;

class LinkedListSimpleSolution<T extends Number> {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		LinkedListSimpleSolution<Integer> list = new LinkedListSimpleSolution<Integer>();

		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}

		for (int i = n - 1; i >= 0; i--) {
			list.insertAt(0, array[i]);
		}

		for (int i = 0; i < m; i++) {
			String command = sc.next();

			if (command.equals("getAt")) {
				int index = sc.nextInt();
				System.out.println(list.getAt(index));
			}

			if (command.equals("insertAt")) {
				int index = sc.nextInt();
				int value = sc.nextInt();
				list.insertAt(index, value);
			}
		}
	}

	static private class LinkedNode<U extends Number> {
		U number;
		LinkedNode<U> next;

		public LinkedNode(U number) {
			this.number = number;
		}
	}

	LinkedNode<T> head = null;
	int size = 0;

	private void internalAdd(T number) {
		size++;
	}

	public int size() {
		return size;
	}

	public void insertAt(int index, T number) {
		LinkedNode<T> newNode = new LinkedNode<T>(number);
		if (index == 0) {
			newNode.next = head;
			head = newNode;
			internalAdd(number);
			return;
		}
	}

	/**
	 * @return null if index is out of range
	 */
	public T getAt(int index) {
		if (index < 0 || index >= size) {
			return null;
		}

		LinkedNode<T> current = head;
		while (index > 0) {
			index--;
			current = current.next;
		}
		return current.number;
	}
}
