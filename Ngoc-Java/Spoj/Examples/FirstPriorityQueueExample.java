import java.util.*;

public class FirstPriorityQueueExample {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Integer> tasks = new PriorityQueue<>();

		while (true) {
			int number = sc.nextInt();
			tasks.add(number);

			int minValue = tasks.peek();

			System.out.println("Min Item: " + minValue);
		}
	}

}
