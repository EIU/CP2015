import java.io.*;
import java.util.*;

class EIELEC {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int n = reader.nextInt(), root = reader.nextInt();

		ElectricNode[] nodes = readGraph(n, root);

		nodes[root].calculateUsage();
		nodes[root].testUsage(false);

		StringBuilder outBuffer = new StringBuilder();
		for (ElectricNode node : nodes) {
			if (node.isFamily && node.hasRisk) {
				outBuffer.append(node.id + " ");
			}
		}

		System.out.println(outBuffer);
	}

	public static ElectricNode[] readGraph(int nVertices, int root) throws IOException {
		ElectricNode[] vertices = new ElectricNode[nVertices];
		for (int i = 0; i < nVertices; ++i) {
			vertices[i] = new ElectricNode(i);
		}
		for (int i = 0; i < nVertices - 1; ++i) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			int weight = reader.nextInt();
			vertices[u].addSubnode(vertices[v], weight);
			vertices[v].addSubnode(vertices[u], weight);
		}
		int countFamily = 0;
		for (ElectricNode node : vertices) {
			if (node.id != root && node.subNodes.size() == 1) {
				countFamily++;
				node.isFamily = true;
			}
		}
		for (int i = 0; i < countFamily; i++) {
			vertices[reader.nextInt()].usage = reader.nextInt();
		}
		return vertices;
	}

	static class ElectricNode {
		public int id;
		public double usage;
		public boolean isFamily = false;
		public boolean isCalculated = false;
		public boolean isTested = false;
		public boolean hasRisk = false;

		public List<ElectricNode> subNodes = new ArrayList<ElectricNode>();
		public List<Long> weights = new ArrayList<Long>();

		public ElectricNode(int id) {
			this.id = id;
		}

		public void addSubnode(ElectricNode child, long weight) {
			subNodes.add(child);
			weights.add(weight);
		}

		public double calculateUsage() {
			if (isCalculated) {
				return 0;
			}
			isCalculated = true;

			for (ElectricNode node : subNodes) {
				usage += node.calculateUsage();
			}
			return usage;
		}

		public void testUsage(boolean hasRisk) {
			isTested = true;
			for (int i = 0; i < subNodes.size(); i++) {
				ElectricNode subNode = subNodes.get(i);
				if (!subNode.isTested) {
					subNode.hasRisk = hasRisk;
					if (subNodes.get(i).usage * 11 / 10 > weights.get(i)) {
						subNode.hasRisk = true;
					}
					subNode.testUsage(subNode.hasRisk);
				}
			}

		}
	}
}
