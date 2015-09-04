import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class ArrayStackSolution<T extends Number> {
	public static void main_s(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();

		ArrayStackSolution<Integer> stack = new ArrayStackSolution<Integer>(n);
		for (int i = 0; i < m; i++) {
			if (i < m - n) {
				sc.nextInt();
			}
			else {
				stack.push(sc.nextInt());
			}
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

	public static void main(String[] args) throws IOException {
		// genTest(0, 1000, 0, 1, "peek");
		// genTest(1, 1000, 50, 1, "peek");
		// genTest(2, 1000, 500, 1, "peek");
		// genTest(3, 1000, 0, 1, "pop");
		// genTest(4, 1000, 50, 5, "pop");
		// genTest(5, 1000, 500, 10, "pop");
		// genTest(6, 1000, 1, 1, "average");
		// genTest(7, 1000, 50, 1, "average");
		// genTest(8, 100000, 90000, 90000, "average");
		// genTest(9, 1000, 0, 1, "sum");
		// genTest(10, 1000, 50, 1, "sum");
		// genTest(11, 100000, 90000, 90000, "sum");
		//
		// genTest(12, 1000, 500, 500, "push push push push peek pop average sum");
		// genTest(13, 1000, 500, 500, "push push push push peek pop average sum");
		// genTest(14, 1000, 500, 500, "push push push push peek pop average sum");
		// genTest(15, 1000, 500, 500, "push push push push peek pop average sum");
		// genTest(16, 1000, 500, 500, "push push push push peek pop average sum");
		// genTest(17, 200000, 50000, 50000, "push peek pop average sum average sum");
		// genTest(18, 200000, 100000, 50000, "push peek pop average sum average sum");
		// genTest(19, 200000, 50000, 100000, "push peek pop average sum average sum");
		// genTest(20, 200000, 100000, 100000, "push peek pop average sum average sum");
		//
		genTest(21, 1, 50, 50, "push push push push peek pop average sum");
		genTest(22, 1, 50, 50, "push push push push peek pop average sum");
		genTest(23, 5, 50, 50, "push push push push peek pop average sum");
		genTest(24, 5, 50, 50, "push push push push peek pop average sum");
		genTest(25, 10, 50, 50, "push push push push peek pop average sum");
		//
		genTest(31, 500, 50, 1000, "push push push push peek pop average sum");
		genTest(32, 500, 500, 2000, "peek pop pop pop pop pop average sum");
		genTest(33, 500, 5000, 1000, "push push push push peek pop average sum");

		genTest(34, 500, 10000, 1000, "push push push push peek pop average sum");
		genTest(35, 500, 500, 1000, "push push push push peek pop average sum");

		genTest(26, 100000, 100000, 30000, "push push push push peek pop average sum");
		genTest(27, 100000, 100000, 30000, "push push push push peek pop average sum");
		genTest(28, 100000, 100000, 30000, "push push push push peek pop average sum");
		// genTest(29, 200000, 100000, 100000, "push peek pop average sum");
		// genTest(30, 200000, 100000, 100000, "push peek pop average sum average");
		// genTest(29, 200000, 100000, 100000, "push peek pop average sum");
		// genTest(36, 20000, 10000, 10000, "push pop pop peek average sum");
		// genTest(37, 20000, 10000, 10000, "push pop pop peek average sum");
		// genTest(38, 20000, 10000, 10000, "push pop pop peek average sum");
		// genTest(39, 20000, 10000, 10000, "push pop pop peek average sum");

		genTest(100, 10, 5, 10, "push push push push peek pop average sum");
	}

	static String basePath = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\EIUDSA14_problemset_03\\EISTACK\\";

	public static int randBetween(int start, int end) {
		if (start == end) {
			end++;
		}
		return start + (int) Math.floor(Math.random() * (end - start - 0.000001));
	}

	public static void genTest(int id, int capacity, int nInit, int nCommand, String commandString) throws IOException {

		int minRange = -1000;
		int maxRange = 1000;

		String[] commands = commandString.split(" ");

		FileWriter in = new FileWriter(basePath + id + ".in");
		FileWriter out = new FileWriter(basePath + id + ".out");

		// in.write(capacity + " " + nInit + " " + nCommand + "\r\n");
		StringBuffer inBuffer = new StringBuffer();
		StringBuffer outBuffer = new StringBuffer();
		inBuffer.append(capacity + " " + nInit + " " + nCommand + "\r\n");
		int[] initValues = new int[nInit];
		for (int i = 0; i < nInit; i++) {
			initValues[i] = randBetween(minRange, maxRange);
			inBuffer.append(initValues[i] + " ");
		}
		// in.write(inBuffer.toString() + "\r\n");
		inBuffer.append("\r\n");

		ArrayStackSolution<Integer> stack = new ArrayStackSolution<Integer>(capacity);
		for (int i = Math.max(0, nInit - capacity); i < nInit; i++) {
			stack.push(initValues[i]);
		}

		int nCommandType = commands.length;
		for (int i = 0; i < nCommand; i++) {
			String command = commands[randBetween(0, nCommandType)];
			if (command.equals("push")) {
				int value = randBetween(minRange, maxRange);
				stack.push(value);
				inBuffer.append("push " + value + "\r\n");
			}

			if (command.equals("peek")) {
				inBuffer.append("peek\r\n");
				outBuffer.append(stack.peek() + "\r\n");
			}

			if (command.equals("pop")) {
				inBuffer.append("pop\r\n");
				outBuffer.append(stack.pop() + "\r\n");
			}

			if (command.equals("sum")) {
				inBuffer.append("sum\r\n");
				outBuffer.append(stack.sum() + "\r\n");
			}

			if (command.equals("average")) {
				inBuffer.append("average\r\n");
				outBuffer.append(stack.average() + "\r\n");
			}
		}

		in.write(inBuffer.toString() + "\r\n");
		out.write(outBuffer.toString() + "\r\n");
		in.close();
		out.close();
	}

	// Your code here
	Object[] data;
	int firstIndex = -1;
	int lastIndex = 0;
	double sum = 0;

	public ArrayStackSolution(int capacity) {
		if (capacity < 1) {
			capacity = 1;
		}
		data = new Object[capacity];
	}

	public int count() {
		int length = data.length;
		if (firstIndex < 0) {
			return 0;
		}
		if (firstIndex == lastIndex) {
			return length;
		}
		return (lastIndex + length - firstIndex) % length;
	}

	public double sum() {
		return sum;
	}

	public double average() {
		int count = count();
		return count == 0 ? 0 : sum() / count;
	}

	private void internalAdd(T number) {
		sum += number.doubleValue();
	}

	private void internalRemove(T number) {
		sum -= number.doubleValue();
	}

	/**
	 * @description: add item when stack is not full
	 */
	@SuppressWarnings("unchecked")
	public void push(T number) {
		int length = data.length;
		if (firstIndex < 0) {
			lastIndex = 0;
			firstIndex = 0;
		}
		else if (lastIndex == firstIndex) {
			T oldest = (T) data[firstIndex];
			internalRemove(oldest);
			firstIndex = (firstIndex + 1) % length;
		}
		data[lastIndex] = number;
		lastIndex = (lastIndex + 1) % length;
		internalAdd(number);
	}

	/**
	 * @return: return and remove top item, or null when stack is empty
	 */
	@SuppressWarnings("unchecked")
	public T pop() {
		if (firstIndex < 0) {
			return null;
		}
		int length = data.length;
		if (length == 1 || firstIndex + 1 == lastIndex || (firstIndex == length - 1 && lastIndex == 0)) {
			T number = (T) data[firstIndex];
			internalRemove(number);
			firstIndex = -1;
			lastIndex = 0;
			return number;
		}
		lastIndex = (lastIndex + length - 1) % length;
		T number = (T) data[lastIndex];
		internalRemove(number);
		return number;
	}

	/**
	 * @return: return top item, or null when stack is empty
	 */
	@SuppressWarnings("unchecked")
	public T peek() {
		if (firstIndex < 0) {
			return null;
		}
		int length = data.length;
		return (T) data[(lastIndex - 1 + length) % length];
	}
}
