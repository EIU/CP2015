import java.io.*;
import java.util.*;

public class EIBIRTHDAY {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int n = reader.nextInt(), m = reader.nextInt(), d = reader.nextInt(), k = reader.nextInt();

		Vertex[] vertices = readGraph(n, m);

		for (Vertex vertex : vertices) {
			System.out.println(vertex.countBirthdays(d, k));
		}
	}

	public static Vertex[] readGraph(int nVertices, int nEdges) throws IOException {
		Vertex[] vertices = new Vertex[nVertices];
		for (int i = 0; i < nVertices; ++i) {
			vertices[i] = new Vertex(i, reader.nextInt());
		}
		for (int i = 0; i < nEdges; ++i) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			vertices[u].addNeighbor(vertices[v]);
			vertices[v].addNeighbor(vertices[u]);
		}
		return vertices;
	}

	static class Vertex {
		public int id;
		public int birthday;
		public List<Vertex> neighbors = new ArrayList<Vertex>();

		public Vertex(int id, int birthday) {
			this.id = id;
			this.birthday = birthday;
		}

		public void addNeighbor(Vertex child) {
			neighbors.add(child);
		}

		public int countBirthdays(int currentDay, int k) {
			int count = 0;
			for (Vertex neighbor : neighbors) {
			}
			return count;
		}
	}
}