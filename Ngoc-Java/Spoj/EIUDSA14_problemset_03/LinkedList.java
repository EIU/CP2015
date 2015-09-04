import java.util.Scanner;

class LinkedList<T extends Number> {

	static private class LinkedNode<U extends Number> {
		U number;
		LinkedNode<U> next;

		public LinkedNode(U number) {
			this.number = number;
		}
	}

	LinkedNode<T> head = null;

	private int compare(T n1, T n2) {
		long l1 = n1.longValue();
		long l2 = n2.longValue();
		if (l1 != l2) {
			return (l1 < l2 ? -1 : 1);
		}
		return Double.compare(n1.doubleValue(), n2.doubleValue());
	}

	public int size() {
		// Your code here
		return 0;
	}

	public void add(T number) {
		LinkedNode<T> newNode = new LinkedNode<T>(number);
		// Your code here
	}

	/**
	 * @return -1 if number is not in list
	 */
	public int firstIndexOf(T number) {
		// Your code here
		return -1;
	}

	/**
	 * @return -1 if number is not in list
	 */
	public int lastIndexOf(T number) {
		// Your code here
		return -1;
	}

	public void removeFirst(T number) {
		// Your code here
	}

	public void removeAt(int index) {
		// Your code here
	}

	public void insertAt(int index, T number) {
		// Your code here
	}

	/**
	 * @return null if index is out of range
	 */
	public T getAt(int index) {
		// Your code here
		return null;
	}

	public double sum() {
		// Your code here
		return 0;
	}

	public double average() {
		// Your code here
		return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		// You code here
	}
}
