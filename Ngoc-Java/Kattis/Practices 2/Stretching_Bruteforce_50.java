import java.io.IOException;
import java.util.*;

public class Stretching_Bruteforce_50 {

	static Node start;
	static Node end;

	public static void main(String[] args) throws IOException {
		byte[] buff = new byte[205];
		int len = System.in.read(buff);
		while (buff[len - 1] < 'a' || buff[len - 1] > 'z') {
			len--;
		}

		HashSet<String> checkedSet = new HashSet<String>();

		Node current = start = new Node((byte) '^');
		for (int i = 0; i < len; i++) {
			current = current.add(buff[i]);
		}
		end = current.add((byte) '$');

		for (int subLen = 1; subLen <= len; subLen++) {
			if (len % subLen != 0) {
				continue;
			}
			for (int i = 0; i <= len - subLen; i++) {
				byte[] subChars = Arrays.copyOfRange(buff, i, i + subLen);
				String subString = new String(subChars);
				if (!checkedSet.contains(subString)) {
					checkedSet.add(subString);
					if (process(subChars, start)) {
						System.out.println(new String(subChars));
						return;
					}
				}
			}
		}
	}
	
	static boolean process(byte[] subChars, Node fromNode) {
		if (start.next == end) {
			return true;
		}
		Node current = fromNode;
		while (current != null) {
			Node found = find(current, subChars);
			if (found == null) {
				return false;
			}
			found.previous.previous.next = found.next.next;
			found.next.next.previous = found.previous.previous;

			fromNode = found.previous.previous;
			for (int i = 0; i < subChars.length - 1 && fromNode != start; i++) {
				fromNode = fromNode.previous;
			}

			boolean subResult = process(subChars, fromNode);
			if (subResult) {
				return true;
			}
			found.previous.previous.next = found.previous;
			found.next.next.previous = found.next;
			current = found.previous.next;
		}
		return false;
	}

	static Node find(Node fromNode, byte[] subChars) {
		while (fromNode != null) {
			Node current = fromNode;
			int i = 0;
			for (; i < subChars.length && current != null; i++, current = current.next) {
				if (subChars[i] != current.letter) {
					break;
				}
			}
			if (i == subChars.length) {
				Node foundNode = new Node((byte) 0, fromNode, current.previous);
				return foundNode;
			}
			fromNode = fromNode.next;
		}
		return null;
	}

	static class Node {
		public byte letter;
		public Node previous;
		public Node next;

		public Node(byte letter) {
			this.letter = letter;
		}

		public Node(byte letter, Node pre, Node next) {
			this.letter = letter;
			this.previous = pre;
			this.next = next;
		}

		public Node add(byte letter) {
			next = new Node(letter);
			next.previous = this;
			return next;
		}

		@Override
		public String toString() {
			return new String(new byte[]{letter});
		}
	}
}

// p = text = 'hello'; for(i=0;i<3;i++) {rand = Math.round(Math.random()*text.length); text = text.substring(0,rand) + p + text.substring(rand,text.length);}
