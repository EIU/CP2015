import java.io.FileWriter;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Scanner;

class LinkedListSolution<T extends Number> {

	static int randBetween(int start, int end) {
		if (start == end) {
			end++;
		}
		return start + (int) Math.floor(Math.random() * (end - start - 0.000001));
	}

	static String arrayToString(int[] array) {
		StringBuilder result = new StringBuilder();
		for (int i : array) {
			result.append(i + " ");
		}
		return result.toString();
	}

	static String basePath = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\EIUDSA14_problemset_03\\EILINKED\\";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		LinkedListSolution<Integer> list = new LinkedListSolution<Integer>();

		// for (int i = 0; i < n; i++) {
		// list.add(sc.nextInt());
		// }
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

			if (command.equals("firstIndexOf")) {
				int value = sc.nextInt();
				System.out.println(list.firstIndexOf(value));
			}

			if (command.equals("lastIndexOf")) {
				int value = sc.nextInt();
				System.out.println(list.lastIndexOf(value));
			}

			if (command.equals("sum")) {
				System.out.println(list.sum());
			}

			if (command.equals("average")) {
				System.out.println(list.average());
			}

			if (command.equals("size")) {
				System.out.println(list.size());
			}

			if (command.equals("insertAt")) {
				int index = sc.nextInt();
				int value = sc.nextInt();
				list.insertAt(index, value);
			}

			if (command.equals("removeAt")) {
				int index = sc.nextInt();
				list.removeAt(index);
			}

			if (command.equals("removeFirst")) {
				int value = sc.nextInt();
				list.removeFirst(value);
			}
		}
	}

	public static void main_gen(String[] args) throws IOException {
		// generate(13, 10, "");
		// generate(14, 10, "");
		// generate(15, 10, "");
		// generate(16, 10, "");
		generate(0, 10, "");
		generate(1, 10, "removeAt");
		generate(2, 10, "insertAt");
		generate(3, 10, "removeFirst");
		generate(4, 10, "insertAt removeAt");
		generate(5, 10, "insertAt removeFirst");
		generate(6, 20, "insertAt removeAt removeFirst");
		generate(7, 1000, "insertAt removeAt removeFirst");
		generate(8, 1000, "insertAt removeAt removeFirst");
		generate(9, 1000, "insertAt removeAt removeFirst");
		generate(10, 100000, "insertAt removeAt removeFirst");
		generate(11, 100000, "insertAt removeAt removeFirst");
		generate(12, 100000, "insertAt removeAt removeFirst");
	}

	public static void main_gen2(String[] args) throws IOException {
		generate_simple(0, 0, 10, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
		generate_simple(1, 0, 10, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
		generate_simple(2, 0, 100, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
		generate_simple(3, 0, 100, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
		generate_simple(4, 0, 1000, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
		generate_simple(5, 0, 1000, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
		generate_simple(6, 0, 10000, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
		generate_simple(7, 0, 10000, "insertAt insertAt insertAt insertAt insertAt insertAt getAt");
	}

	public static void generate(int id, int n, String mode) throws IOException {

		FileWriter in = new FileWriter(basePath + id + ".in");
		FileWriter out = new FileWriter(basePath + id + ".out");
		int m = mode.length() > 0 ? 1 : 0;
		for (int i = 0; i < mode.length(); i++) {
			if (mode.charAt(i) == ' ') {
				m++;
			}
		}

		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = randBetween(-n / 2, n / 2);
		}

		LinkedListSolution<Integer> list = new LinkedListSolution<Integer>();
		for (int i : array) {
			list.add(i);
		}
		in.write(n + " " + (15 + m * 6) + " \r\n");
		list.print(in);
		// writeCheck(list, in, out);

		int r1 = randBetween(0, n), r2 = randBetween(0, n - 1), r3 = randBetween(0, n - 2);
		if (mode.indexOf("removeAt") >= 0) {
			n = list.size();
			list.removeAt(n - 1);
			list.removeAt(0);
			list.removeAt(r1);
			list.removeAt(r2);
			list.removeAt(r3);
			list.removeAt(r1);
			// writeCheck(list, in, out);
			in.write(String.format("removeAt %d%nremoveAt %d%nremoveAt %d%nremoveAt %d%nremoveAt %d%nremoveAt %d%n",
					n - 1, 0, r1, r2, r3, r1));
			// list.print(in);
		}

		int i1 = randBetween(0, n), i2 = randBetween(0, n), i3 = randBetween(0, n);
		if (mode.indexOf("insertAt") >= 0) {
			n = list.size();
			list.insertAt(0, n - 1);
			list.insertAt(n - 1, 0);
			list.insertAt(n, r1 / 2);
			list.insertAt(i1, r1 / 2);
			list.insertAt(i2, r2 / 2);
			list.insertAt(i3, r3 / 2);
			// writeCheck(list, in, out);
			in.write(String.format("insertAt %d %d%ninsertAt %d %d%ninsertAt %d %d%ninsertAt %d %d%ninsertAt %d %d%ninsertAt %d %d%n",
					0, n - 1, n - 1, 0, n, r1 / 2, i1, r1 / 2, i2, r2 / 2, i3, r3 / 2));
			// list.print(in);
		}

		if (mode.indexOf("removeFirst") >= 0) {
			n = list.size();
			int value0 = list.getAt(0);
			list.removeFirst(value0);
			int value1 = list.getAt(5);
			list.removeFirst(value1);
			int value2 = list.getAt(list.size() - 1);
			list.removeFirst(value2);
			list.removeFirst(r1);
			list.removeFirst(r2);
			list.removeFirst(r3);
			in.write(String.format("removeFirst %d%nremoveFirst %d%nremoveFirst %d%nremoveFirst %d%nremoveFirst %d%nremoveFirst %d%n",
					value0, value1, value2, r1, r2, r3));
			// list.print(in);
		}

		writeCheck(list, in, out);
		// list.print(in);
		in.close();
		out.close();
	}

	public static void generate_simple(int id, int init, int nCommand, String commandString) throws IOException {
		FileWriter in = new FileWriter(basePath + id + ".in");
		FileWriter out = new FileWriter(basePath + id + ".out");
		StringBuffer inBuffer = new StringBuffer();
		StringBuffer outBuffer = new StringBuffer();

		int minRange = -1000;
		int maxRange = 1000;

		String[] commands = commandString.split(" ");
		inBuffer.append("0 " + nCommand + "\r\n");

		LinkedListSolution<Integer> list = new LinkedListSolution<Integer>();

		int nCommandType = commands.length;
		for (int i = 0; i < nCommand; i++) {
			String command = commands[randBetween(0, nCommandType)];

			if (command.equals("insertAt")) {
				int number = randBetween(minRange, maxRange);
				inBuffer.append("insertAt 0 " + number + "\r\n");
				list.insertAt(0, number);
			}

			if (command.equals("getAt")) {
				int index = randBetween(0, list.size);
				inBuffer.append("getAt " + index + "\r\n");
				outBuffer.append(list.getAt(index) + "\r\n");
			}
		}

		in.write(inBuffer.toString() + "\r\n");
		out.write(outBuffer.toString() + "\r\n");
		in.close();
		out.close();
	}

	static void writeCheck(LinkedListSolution<Integer> list, FileWriter in, FileWriter out) throws IOException {
		int n = list.size();
		int rand1 = randBetween(0, n / 2);
		int rand2 = randBetween(0, n / 2);
		int rand3 = randBetween(0, n / 2);
		int at1 = randBetween(0, n), at2 = randBetween(0, n), at3 = randBetween(0, n);
		in.write(String.format("getAt %d%ngetAt %d%ngetAt %d%ngetAt %d%ngetAt %d%ngetAt %d%ngetAt %d%n" +
				"firstIndexOf %d%nlastIndexOf %d%nfirstIndexOf %d%nlastIndexOf %d%nfirstIndexOf %d%nlastIndexOf %d%n"
				+ "sum%naverage%n",
				at1, at2, at3, -1, n, 0, n - 1,
				rand1, rand1, rand2, rand2, rand3, rand3));

		out.write(list.getAt(at1) + "\r\n");
		out.write(list.getAt(at2) + "\r\n");
		out.write(list.getAt(at3) + "\r\n");
		out.write(list.getAt(-1) + "\r\n");
		out.write(list.getAt(n) + "\r\n");
		out.write(list.getAt(0) + "\r\n");
		out.write(list.getAt(n - 1) + "\r\n");

		out.write(list.firstIndexOf(rand1) + "\r\n");
		out.write(list.lastIndexOf(rand1) + "\r\n");
		out.write(list.firstIndexOf(rand2) + "\r\n");
		out.write(list.lastIndexOf(rand2) + "\r\n");
		out.write(list.firstIndexOf(rand3) + "\r\n");
		out.write(list.lastIndexOf(rand3) + "\r\n");

		out.write(list.sum() + "\r\n");
		out.write(list.average() + "\r\n");
	}

	public void print(FileWriter out) throws IOException {
		LinkedNode<T> current = head;
		while (current != null) {
			out.write(current.number + " ");
			current = current.next;
		}
		out.write("\r\n");
	}

	// ///////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////

	static private class LinkedNode<U extends Number> {
		U number;
		LinkedNode<U> next;

		public LinkedNode(U number) {
			this.number = number;
		}
	}

	LinkedNode<T> head = null;
	int size = 0;
	double sum = 0;

	private int compare(T n1, T n2) {
		long l1 = n1.longValue();
		long l2 = n2.longValue();
		if (l1 != l2) {
			return (l1 < l2 ? -1 : 1);
		}
		return Double.compare(n1.doubleValue(), n2.doubleValue());
	}

	private void internalAdd(T number) {
		size++;
		sum += number.doubleValue();
	}

	private void internalRemove(T number) {
		size--;
		sum -= number.doubleValue();
	}

	public int size() {
		return size;
	}

	public void add(T number) {
		LinkedNode<T> newNode = new LinkedNode<T>(number);
		if (head == null) {
			head = newNode;
			internalAdd(number);
		}
		else {
			LinkedNode<T> current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
			internalAdd(number);
		}
	}

	public int firstIndexOf(T number) {
		int index = -1;
		LinkedNode<T> current = head;
		while (current != null) {
			index++;
			if (compare(current.number, number) == 0) {
				return index;
			}
			current = current.next;
		}
		return -1;
	}

	public int lastIndexOf(T number) {
		int index = -1;
		LinkedNode<T> current = head;
		int lastIndex = -1;
		while (current != null) {
			index++;
			if (compare(current.number, number) == 0) {
				lastIndex = index;
			}
			current = current.next;
		}
		return lastIndex;
	}

	public void removeFirst(T number) {
		if (head == null) {
			return;
		}
		if (compare(head.number, number) == 0) {
			head = head.next;
			internalRemove(number);
			return;
		}
		LinkedNode<T> current = head;
		while (current.next != null) {
			if (compare(current.next.number, number) == 0) {
				current.next = current.next.next;
				internalRemove(number);
				return;
			}
			current = current.next;
		}
	}

	public void removeAt(int index) {
		if (index >= size || index < 0) {
			return;
		}
		if (index == 0) {
			T oldNumber = head.number;
			head = head.next;
			internalRemove(oldNumber);
			return;
		}

		LinkedNode<T> current = head;
		while (index > 1) {
			index--;
			current = current.next;
		}
		T oldNumber = current.next.number;
		current.next = current.next.next;
		internalRemove(oldNumber);
	}

	public void insertAt(int index, T number) {
		if (index > size || index < 0) {
			return;
		}
		LinkedNode<T> newNode = new LinkedNode<T>(number);
		if (index == 0) {
			newNode.next = head;
			head = newNode;
			internalAdd(number);
			return;
		}
		LinkedNode<T> current = head;
		while (index > 1) {
			index--;
			current = current.next;
		}

		newNode.next = current.next;
		current.next = newNode;
		internalAdd(number);
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

	public double sum() {
		return sum;
	}

	public double average() {
		return size == 0 ? 0 : sum / size;
	}
}
