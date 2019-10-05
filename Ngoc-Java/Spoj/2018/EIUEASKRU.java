import java.io.*;
import java.util.*;

public class EIUEASKRU {
	static FastInputReader reader = new FastInputReader(System.in);

	public static void main(String[] args) throws IOException {
		int n = reader.nextInt();
		int m = reader.nextInt();

		Vertex[] vertices = readGraph(n, m);

		long maxTotalWeight = 0;
		for (Vertex vertex : vertices) {
			if (vertex.shortestWeight < Long.MAX_VALUE) {
				maxTotalWeight = Math.max(maxTotalWeight, MSTWeight(vertex));
			}
		}
		System.out.println(maxTotalWeight);
	}

	public static long MSTWeight(Vertex vertex) {
		long totalWeight = 0;
		TreeSet<Vertex> queue = new TreeSet<Vertex>();
		vertex.shortestWeight = 0;
		queue.add(vertex);
		while (!queue.isEmpty()) {
			Vertex minItem = queue.first();
			queue.remove(minItem);
			totalWeight += minItem.shortestWeight;
			minItem.shortestWeight = 0;
			for (int i = 0; i < minItem.neighbors.size(); i++) {
				Vertex neighbor = minItem.neighbors.get(i);
				if (neighbor.shortestWeight > 0 && neighbor.shortestWeight > minItem.neighborWeight.get(i)) {
					queue.remove(neighbor);
					neighbor.shortestWeight = minItem.neighborWeight.get(i);
					queue.add(neighbor);
				}
			}
		}
		return totalWeight;
	}

	public static Vertex[] readGraph(int nVertices, int nEdges) throws IOException {
		Vertex[] vertices = new Vertex[nVertices];
		for (int i = 0; i < nVertices; ++i) {
			vertices[i] = new Vertex(i);
		}
		for (int i = 0; i < nEdges; ++i) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			int w = reader.nextInt();
			vertices[u].addNeighbor(vertices[v], w);
			vertices[v].addNeighbor(vertices[u], w);
		}
		return vertices;
	}

	static class Vertex implements Comparable<Vertex> {
		public int id;
		public long shortestWeight = Long.MAX_VALUE;
		public List<Vertex> neighbors = new ArrayList<Vertex>();
		public List<Integer> neighborWeight = new ArrayList<Integer>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addNeighbor(Vertex child, int weight) {
			neighbors.add(child);
			neighborWeight.add(weight);
		}

		@Override
		public int compareTo(Vertex arg0) {
			return Long.compare(shortestWeight, arg0.shortestWeight);
		}
	}

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 25];
		int lenbuf = 0, ptrbuf = 0;
		InputStream is;

		public FastInputReader(InputStream stream) {
			is = stream;
		}

		int readByte() {
			if (lenbuf == -1)
				throw new InputMismatchException();
			if (ptrbuf >= lenbuf) {
				ptrbuf = 0;
				try {
					lenbuf = is.read(inbuf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return -1;
			}
			return inbuf[ptrbuf++];
		}

		public boolean hasNext() {
			return ptrbuf + 3 < lenbuf;
		}

		boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public char[] next(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return (char) skip();
		}

		public int nextInt() {
			int num = 0, b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}

		long nextLong() {
			long num = 0;
			int b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}
	}
}