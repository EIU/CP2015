import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CommonSubexpression {
	static InputStream is;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 26);

		int T = Integer.parseInt(reader.readLine());
		outBuffer = new StringBuilder();
		for (int t = 0; t < T; t++) {
			inputChars = reader.readLine().toCharArray();
			position = 0;
			id = 1;
			mapNode = new TreeMap<Long, Integer>();
			Node node = readTree();
			printTree(node);
			outBuffer.append("\n");
		}
		System.out.println(outBuffer);
	}

	static long MUL1 = 2038072819L; // 1301081
	static long MUL2 = 3121238891L;
	static long MOD = 32416190071L; // 22801763489L;
	static int id;
	static StringBuilder outBuffer;
	static int position;
	static char[] inputChars;
	static MessageDigest crypt;
	static TreeMap<Long, Integer> mapNode;

	static Node readTree() {
		Node node = new Node();
		node.id = id++;
		//String text = 
		node.value = nextName();
		long hashed = getHashed(node.value);
		if (position < inputChars.length && inputChars[position] == '(') {
			position++; // (
			node.left = readTree();
			position++; // ,
			node.right = readTree();
			position++; // )
			hashed = (hashed + node.left.hashed * MUL1 + node.right.hashed * MUL2) % MOD;
			// text += "" + node.left.hashed + node.right.hashed;
		}
		node.hashed = hashed; //getHashed(text);
		Integer refId = mapNode.get(node.hashed);
		if (refId == null) {
			mapNode.put(node.hashed, node.id);
		}
		else {
			node.value = refId + "";
			node.left = null;
			node.right = null;
			id = node.id;
			node.id = -1;
		}
		return node;
	}

	static void printTree(Node node) {
		outBuffer.append(node.value);
		if (node.left != null) {
			outBuffer.append("(");
			printTree(node.left);
			outBuffer.append(",");
			printTree(node.right);
			outBuffer.append(")");
		}
	}

	static {
		try {
			crypt = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	static long getHashed(String s) {
		crypt.reset();
		crypt.update(s.getBytes());
		return new BigInteger(1, crypt.digest()).longValue();
	}

	static String nextName() {
		StringBuffer bf = new StringBuffer();
		while (position < inputChars.length && isLetter(inputChars[position])) {
			bf.append(inputChars[position++]);
		}
		return bf.toString();
	}

	static boolean isLetter(char c) {
		return 'a' <= c && c <= 'z';
	}

	static class Node {
		public int id;
		public String value;
		public Node left;
		public Node right;
		public long hashed;
	}
}
