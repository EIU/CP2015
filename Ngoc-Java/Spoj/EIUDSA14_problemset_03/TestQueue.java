import java.util.Scanner;

class TestQueue {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(n);
		for (int i = 0; i < n; i++) {
			String command = sc.next();
			if (command.equals("add")) {
				// Your code here
			}

			if (command.equals("peek")) {
				// Your code here
			}

			if (command.equals("poll")) {
				// Your code here
			}

			if (command.equals("isEmpty")) {
				// Your code here
			}
		}
	}
}

class ArrayQueue<T> {

	public ArrayQueue(int capacity) {
	}

	public boolean isEmpty() {
		// Your code here
		return false;
	}
	
	public int countItem() {
		// Your code here
		return 0;
	}

	/**
	 * @return: return and remove top item, or null when queue is empty
	 */
	public T poll() {
		// Your code here
		return null;
	}

	/**
	 * @description: add item when queue is not full
	 */
	public void add(T item) {
		// Your code here
	}

	/**
	 * @return: return top item, or null when queue is empty
	 */
	public T peek() {
		// Your code here
		return null;
	}
}
