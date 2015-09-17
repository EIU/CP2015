import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class CodeExample {

	public static void main(String[] args) {

		// Sort Array
		Integer[] array = {3,1,4,2,4,5};
		Arrays.sort(array, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		System.out.println("Array:");
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
		
		
		// ArrayList:
		ArrayList<Date> list = new ArrayList<Date>();
		list.add(new Date(20, 5, 1994));
		list.add(new Date(16, 2, 1995));
		list.add(new Date(10, 3, 1992));
		
		// Sort ArrayList Option 1
		list.sort(new Comparator<Date>() {
			@Override
			public int compare(Date o1, Date o2) {
				return -(o1.year - o2.year);
			}
		});
		System.out.println("ArrayList Option 1:");
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		
		// Sort ArrayList Option 2
		Collections.sort(list);
		System.out.println("ArrayList Option 2:");
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}

		
		// PriorityQueue
		PriorityQueue<Date> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(new Date(20, 5, 1994));
		priorityQueue.add(new Date(16, 2, 1995));
		priorityQueue.add(new Date(10, 3, 1992));
		System.out.println("Priority Queue:");
		while (!priorityQueue.isEmpty()) {
			Date b = priorityQueue.poll();
			System.out.println(b);
		}

		
		// TreeSet:
		TreeSet<Date> tree = new TreeSet<>();
		tree.add(new Date(20, 5, 1994));
		tree.add(new Date(16, 2, 1995));
		tree.add(new Date(10, 3, 1992));
		System.out.println("TreeSet:");
		while (!tree.isEmpty()) {
			Date b = tree.pollFirst();
			System.out.println(b);
		}
	}
	
	static class Date implements Comparable<Date> {

		public int day;
		public int month;
		public int year;

		public Date(int day, int month, int year) {
			this.day = day;
			this.month = month;
			this.year = year;
		}
		public String toString() {
			return (day + " " + month + " " + year);
		}

		@Override
		public int compareTo(Date o) {
			if (year != o.year) {
				return year - o.year;
			} else {
				if (month != o.month) {
					return month - o.month;
				}
				return day - o.day;
			}
		}
	}
}
