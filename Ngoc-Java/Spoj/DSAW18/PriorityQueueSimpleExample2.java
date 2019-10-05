import java.util.*;

public class PriorityQueueSimpleExample2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Task> queue = new PriorityQueue<>();
		PriorityQueue<Task> maxQueue = new PriorityQueue<>((t1, t2) -> t2.priority - t1.priority);
		int taskId = 1;
		while (true) {
			String name = "Task " + taskId++;
			int priority = sc.nextInt();
			queue.add(new Task(name, priority));
			maxQueue.add(new Task(name, priority));
			System.out.println(queue.peek() + " | " + maxQueue.peek());
		}
	}

	static class Task implements Comparable<Task> {
		String name;
		int priority;

		public Task(String name, int priority) {
			this.name = name;
			this.priority = priority;
		}

		@Override
		public int compareTo(Task otherTask) {
			return priority - otherTask.priority;
		}

		@Override
		public String toString() {
			return name + " - " + priority;
		}
	}
}
