import java.util.*;

public class Sample1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		List<Vertex> vertices = new ArrayList<Vertex>();

		for (int i = 0; i < n; i++) {
			vertices.add(new Vertex(i + 1));
		}

		for (int i = 0; i < m; i++) {
			Vertex u = vertices.get(sc.nextInt() - 1);
			Vertex v = vertices.get(sc.nextInt() - 1);
			int length = sc.nextInt();
			u.addAdjecent(v, length);
			v.addAdjecent(u, length);
		}

		for (int i = 0; i < n; i++) {
			System.out.println(vertices.get(i).toStringA(n));
		}
	}

	static class Vertex {
		public int id;
		public List<Vertex> adjacentVertices;
		public List<Integer> adjacentIndexes;
		public List<Integer> lengths;
		// public HashMap<Integer, Vertex> adjacentVertices;

		public Vertex(int id) {
			this.id = id;
			adjacentVertices = new ArrayList<Vertex>();
			adjacentIndexes = new ArrayList<Integer>();
			lengths = new ArrayList<Integer>();
		}

		public void addAdjecent(Vertex vertex, int length) {
			adjacentVertices.add(vertex);
			adjacentIndexes.add(vertex.id);
			lengths.add(length);
		}

		public String toStringA(int n) {
			int[] temp = new int[n];
			for (int i = 0; i < adjacentVertices.size(); i++) {
				temp[adjacentVertices.get(i).id - 1] = lengths.get(i);
			}

			StringBuilder outBuffer = new StringBuilder();
			for (int number : temp) {
				outBuffer.append(number);
			}
			return outBuffer.toString();
		}

		public String toString(int n) {
			String text = "";
			for (int i = 0; i < n; i++) {
				// int j = 0;
				// for (; j < adjacentVertices.size(); j++) {
				// if (adjacentVertices.get(j).id == i + 1)
				// break;
				// }
				int j = adjacentIndexes.indexOf(i + 1);
				if (j < 0) {
					text += " 0";
				} else {
					text += " " + lengths.get(j);
				}
			}
			return text;
		}

		@Override
		public String toString() {

			String text = "";

			for (int i = 0; i < adjacentVertices.size(); i++) {
				text += " " + adjacentVertices.get(i).id + "-" + lengths.get(i);
			}
			return text;
		}

	}
}
