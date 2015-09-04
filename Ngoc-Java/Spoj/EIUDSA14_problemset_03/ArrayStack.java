import java.util.Scanner;

class ArrayStack<T extends Number> {
	public static void main_s(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();

		ArrayStackSolution<Integer> stack = new ArrayStackSolution<Integer>(n);
		for (int i = 0; i < m; i++) {
			stack.push(sc.nextInt());
		}

		for (int i = 0; i < k; i++) {
			String command = sc.next();

			if (command.equals("push")) {
				int value = sc.nextInt();
				stack.push(value);
			}

			if (command.equals("pop")) {
				System.out.println(stack.pop());
			}

			if (command.equals("peek")) {
				System.out.println(stack.peek());
			}

			if (command.equals("sum")) {
				System.out.println(stack.sum());
			}

			if (command.equals("average")) {
				System.out.println(stack.average());
			}
		}
	}

	// Your code here
	Object[] data;
	int lastIndex = 0;

	public ArrayStack(int capacity) {
		data = new Object[capacity];
	}

	public int count() {
		// Your code here
		return 0;
	}

	public double sum() {
		// Your code here
		// T number;
		// number.doubleValue();
		return 0;
	}

	public double average() {
		// Your code here
		return 0;
	}

	/**
	 * @description: add item when stack is not full
	 */
	public void push(T item) {
		// Sample: data[lastIndex++] = item;
		// Your code here
	}

	/**
	 * @return: return and remove top item, or null when stack is empty
	 */
	public T pop() {
		// Your code here
		return null;
	}

	/**
	 * @return: return top item, or null when stack is empty
	 */
	@SuppressWarnings("unchecked")
	public T peek() {
		// Sample return (T) data[lastIndex - 1];
		// Your code here
		return null;
	}
}
