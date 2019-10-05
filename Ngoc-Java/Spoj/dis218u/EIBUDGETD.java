import java.io.*;
import java.util.*;

class EIBUDGETD {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int n = reader.nextInt(), m = reader.nextInt();

		Project[] projects = readGraph(n);

		projects[0].calculateBudget(m);

		StringBuilder outBuffer = new StringBuilder();
		for (Project project : projects) {
			outBuffer.append(Math.round(project.budget) + "\n");
		}

		System.out.println(outBuffer);
	}

	public static Project[] readGraph(int nVertices) throws IOException {
		Project[] vertices = new Project[nVertices];
		vertices[0] = new Project(0, 100);
		for (int i = 1; i < nVertices; ++i) {
			vertices[i] = new Project(i, reader.nextDouble());
		}
		for (int i = 0; i < nVertices - 1; ++i) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			vertices[u].addSubproject(vertices[v]);
			vertices[v].addSubproject(vertices[u]);
		}
		return vertices;
	}

	static class Project {
		public int id;
		public double percentage;
		public double budget;
		public boolean visited = false;

		public List<Project> subprojects = new ArrayList<Project>();

		public Project(int id, double percentage) {
			this.id = id;
			this.percentage = percentage;
		}

		public void addSubproject(Project child) {
			subprojects.add(child);
		}

		public void calculateBudget(double parentBudget) {
			if (visited) {
				return;
			}
			visited = true;
			budget = parentBudget * percentage / 100;

			for (Project project : subprojects) {
				project.calculateBudget(budget);
			}
		}
	}
}