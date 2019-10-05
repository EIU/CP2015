import java.util.*;

public class SecondPriorityQueueExample {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Request> requests = new PriorityQueue<>();
		PriorityQueue<Request> requests2 = new PriorityQueue<Request>((rq1, rq2) -> {
			int cmp = rq1.priority - rq2.priority;
			if (cmp == 0) {
				cmp = Long.compare(rq1.time, rq2.time);
			}
			return cmp;
		});

		int id = 1;
		while (true) {
			String name = "Task " + id++;
			int priority = sc.nextInt();
			Request request = new Request(name, priority);

			requests.add(request);
			requests2.add(request);

			System.out.println(requests.peek());
			System.out.println(requests2.peek());
		}
	}

	static class Request implements Comparable<Request> {
		public String name;
		public long time;
		int priority;

		public Request(String name, int priority) {
			this.name = name;
			time = System.currentTimeMillis();
			this.priority = priority;
		}

		@Override
		public int compareTo(Request otherRequest) {
			return priority - otherRequest.priority;
		}

		@Override
		public String toString() {
			return name + " - " + priority;
		}
	}
}
