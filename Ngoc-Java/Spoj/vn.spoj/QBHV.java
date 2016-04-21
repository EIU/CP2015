import java.io.IOException;
import java.util.*;

class QBHV {

	static int nChar;
	static int nNode;
	static Node[] nodes;

	static int curPos;
	static char[] curString;

	static int countString = 0;

	static StringBuilder outBuffer = new StringBuilder();

	public static void main(String[] args) {

		readInput();

		curPos = 0;
		curString = new char[nChar + 1];
		curString[nChar] = '\n';

		generate();

		System.out.println(countString);

		String result = outBuffer.toString();
		long s = System.currentTimeMillis();
		System.out.print(result);
		System.out.println(System.currentTimeMillis() - s);
	}

	public static void generate() {
		for (int i = 0; i < nNode; i++) {
			Node node = nodes[i];
			if (node.count > 0) {
				node.count--;
				curString[curPos] = node.letter;
				if (curPos < nChar - 1) {
					curPos++;
					generate();
					curPos--;
				} else {
					outBuffer.append(new String(curString));
					countString++;
				}
				node.count++;
			}
		}
	}

	static void readInput() {
		byte[] buffer = new byte[20];
		nChar = 0;
		try {
			nChar = System.in.read(buffer);
			if (buffer[nChar - 1] == '\n') {
				nChar--;
			}
			if (buffer[nChar - 1] == '\r') {
				nChar--;
			}
		} catch (IOException e) {
		}
		buffer = Arrays.copyOfRange(buffer, 0, nChar);
		Arrays.sort(buffer);

		ArrayList<Node> nodeList = new ArrayList<Node>();

		nNode = 0;
		for (int i = 0; i < nChar; i++) {
			if (i == 0 || buffer[i] != buffer[i - 1]) {
				nodeList.add(new Node((char) buffer[i]));
				nNode++;
			}
			else {
				nodeList.get(nNode - 1).count++;
			}
		}

		nodes = new Node[nNode];
		nodeList.toArray(nodes);
	}

	static class Node {
		public char letter;
		public int count;

		public Node(char ch) {
			this.letter = ch;
			this.count = 1;
		}
	}

}
