import java.util.*;

public class PriorityQueueSimpleExample {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		while (true) {
			queue.add(sc.nextInt());
			System.out.println(queue.peek());
		}
	}
}
